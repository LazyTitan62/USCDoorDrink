package com.csci310.uscdoordrink;

import static com.csci310.uscdoordrink.ConnectionClass.CONN;

import android.util.Log;

import com.csci310.uscdoordrink.Customer;
import com.csci310.uscdoordrink.Item;
import com.csci310.uscdoordrink.Merchant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class Query {
//    Connection connection;


    public ArrayList<Item> getMenuFromDatabase(String merchantName)
    {
        ArrayList<Item> items = new ArrayList<>();
        try
        {
            Connection con= CONN();
//          connection =conStr.CONN();
            String query = "SELECT * FROM MENU WHERE UserName = '" + merchantName + "'";
            Statement stmt = con.prepareStatement(query);
            ResultSet result = stmt.executeQuery(query);
            while (result.next()){
                String itemName = result.getString("ItemName");
                Float itemPrice = result.getFloat("ItemPrice");
                Integer itemCaffeineAmount = result.getInt("ItemCaffeineAmount");
                Item i = new Item(itemName,itemPrice,itemCaffeineAmount);
                items.add(i);
            }
        }
        catch (SQLException se)
        {
            Log.e("ERROR", se.getMessage());
        }
        return items;
    }

    public ArrayList<Order> getOrderFromDatabase(String userName, Boolean isMerchant)
    {
        ArrayList<Integer> orderIDList = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        try
        {
            Connection con= CONN();
//          connection =conStr.CONN();
            String query;
            if (isMerchant){
                query = "SELECT * FROM sys.ORDER WHERE UserName_Merchant = '" + userName + "' ORDER BY CreatedDate DESC";
            }
            else{
                query = "SELECT * FROM sys.ORDER WHERE UserName_Customer = '" + userName + "' ORDER BY CreatedDate DESC";
            }
            Statement stmt = con.prepareStatement(query);
            ResultSet result = stmt.executeQuery(query);
            while (result.next()){
                String customerName = result.getString("UserName_Customer");
                String merchantName = result.getString("UserName_Merchant");
                String createdDate = result.getString("CreatedDate");
                String createdTime = result.getString("CreatedTime");
                String deliveredDate = result.getString("DeliveredDate");
                String deliveredTime = result.getString("DeliveredTime");
                DeliveryRoute route = new DeliveryRoute(merchantName,customerName,createdDate,createdTime,deliveredDate,deliveredTime);
                ArrayList<Item> orderedItems = new ArrayList<>();
                Order o = new Order(orderedItems,route);
                orders.add(o);
                orderIDList.add(result.getInt("OrderID"));
            }

            for (int i = 0; i < orderIDList.size(); i++){
                String query1;
                if (isMerchant){
                    query1 = "SELECT O.OrderID, O.UserName_Customer, O.UserName_Merchant, O.CreatedDate, " +
                            "O.CreatedTime, O.DeliveredDate, O.DeliveredTime, M.ItemName, M.ItemPrice, " +
                            "I.Quantity, M.ItemCaffeineAmount FROM sys.ORDER O, ITEMS_IN_ORDER I, MENU M " +
                            "WHERE O.OrderID = I.OrderID AND M.ItemID = I.ItemID AND UserName_Merchant = '" +
                            userName + "' AND O.OrderID = " + orderIDList.get(i);
                }
                else{
                    query1 = "SELECT O.OrderID, O.UserName_Customer, O.UserName_Merchant, O.CreatedDate, " +
                            "O.CreatedTime, O.DeliveredDate, O.DeliveredTime, M.ItemName, M.ItemPrice, " +
                            "I.Quantity, M.ItemCaffeineAmount FROM sys.ORDER O, ITEMS_IN_ORDER I, MENU M " +
                            "WHERE O.OrderID = I.OrderID AND M.ItemID = I.ItemID AND UserName_Customer = '" +
                            userName + "' AND O.OrderID = " + orderIDList.get(i);
                }
                Statement stmt1 = con.prepareStatement(query1);
                ResultSet result1 = stmt.executeQuery(query1);
                while (result1.next()){
                    String itemName = result1.getString("ItemName");
                    Float itemPrice = result1.getFloat("ItemPrice");
                    Integer itemQty = result1.getInt("Quantity");
                    Integer itemCaffeine = result1.getInt("ItemCaffeineAmount");
                    Item item = new Item(itemName, itemPrice, itemQty, itemCaffeine);
                    orders.get(i).addOrderItem(item);
                }
            }

        }
        catch (SQLException se)
        {
            Log.e("ERROR", se.getMessage());
        }
        return orders;
    }

    public Boolean checkCaffeineUpdate(String date, Customer customer) {
        Boolean returnValue;
        try
        {
            Connection con = CONN();
            String query = "SELECT * FROM sys.ORDER WHERE UserName_Customer = '" + customer.getUsrName() + "' AND CreatedDate = '" + date + "'";
            Statement stmt = con.prepareStatement(query);
            ResultSet result = stmt.executeQuery(query);
            if (result.next()) {
                return false;
            }
        }
        catch (SQLException se)
        {
            Log.e("ERROR", se.getMessage());
        }
        return true;
    }

    public HashMap<String,Float> getDailyAnalysis(String userName, String date) {
        HashMap<String,Float> hm = new HashMap<>();
        try
        {
            Connection con = CONN();

            String query = "SELECT UserName_Merchant, ROUND(SUM(totalPrice), 2) AS Sum FROM sys.ORDER WHERE UserName_Customer = '" + userName + "' AND CreatedDate = '" + date + "' GROUP BY UserName_Merchant";
            Statement stmt = con.prepareStatement(query);
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                hm.put(result.getString("UserName_Merchant"), result.getFloat("Sum"));
            }
        }
        catch (SQLException se)
        {
            Log.e("ERROR", se.getMessage());
        }
        return hm;
    }

    public LinkedHashMap<String,Float> getWeeklyAnalysis(String userName, String date) {
        LinkedHashMap<String,Float> lhm = new LinkedHashMap<>();
        try
        {
            Connection con = CONN();
            String query = "SELECT CreatedDate, ROUND(SUM(totalPrice), 2) AS Sum FROM sys.ORDER WHERE " +
                    "UserName_Customer = '" + userName + "' AND CreatedDate >= '" + date + "' - " +
                    "INTERVAL 7 DAY GROUP BY CreatedDate ORDER BY CreatedDate ASC";
            Statement stmt = con.prepareStatement(query);
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                lhm.put(result.getString("CreatedDate"), result.getFloat("Sum"));
            }
        }
        catch (SQLException se)
        {
            Log.e("ERROR", se.getMessage());
        }
        return lhm;
    }

    public LinkedHashMap<String,Float> getMonthlyAnalysis(String userName, String date) {
        LinkedHashMap<String,Float> lhm = new LinkedHashMap<>();
        try
        {
            Connection con = CONN();
            String query = "SELECT CASE\n" +
                    "WHEN CreatedDate >= ('2022-3-28' - INTERVAL 1 WEEK) THEN concat('2022-3-28' - INTERVAL 1 WEEK, ' - 2022-3-28')\n" +
                    "WHEN CreatedDate >= ('2022-3-28' - INTERVAL 2 WEEK) AND CreatedDate < ('2022-3-28' - INTERVAL 1 WEEK)  THEN concat('2022-3-28' - INTERVAL 2 WEEK,' - ', '2022-3-28' - INTERVAL 1 WEEK - INTERVAL 1 DAY)\n" +
                    "WHEN CreatedDate >= ('2022-3-28' - INTERVAL 3 WEEK) AND CreatedDate < ('2022-3-28' - INTERVAL 2 WEEK)  THEN concat('2022-3-28' - INTERVAL 3 WEEK,' - ', '2022-3-28' - INTERVAL 2 WEEK - INTERVAL 1 DAY)\n" +
                    "WHEN CreatedDate >= ('2022-3-28' - INTERVAL 4 WEEK) AND CreatedDate < ('2022-3-28' - INTERVAL 3 WEEK)  THEN concat('2022-3-28' - INTERVAL 4 WEEK,' - ', '2022-3-28' - INTERVAL 3 WEEK - INTERVAL 1 DAY)\n" +
                    "\tEND AS Period, ROUND(SUM(totalPrice), 2) AS Sum\n" +
                    "FROM sys.ORDER O";

//            String query = "SELECT R.period AS Period, R.sum AS Sum " +
//                    "FROM ( " +
//                    "SELECT CASE " +
//                    "WHEN CreatedDate >= ('" + date + "' - INTERVAL 1 WEEK) THEN concat('" + date + "' - INTERVAL 1 WEEK, ' - " + date + "') " +
//                    "WHEN CreatedDate >= ('" + date + "' - INTERVAL 2 WEEK) AND CreatedDate < ('" + date + "' - INTERVAL 1 WEEK)  THEN concat('" + date + "' - INTERVAL 2 WEEK,' - ', '" + date + "' - INTERVAL 1 WEEK - INTERVAL 1 DAY) " +
//                    "WHEN CreatedDate >= ('" + date + "' - INTERVAL 3 WEEK) AND CreatedDate < ('" + date + "' - INTERVAL 2 WEEK)  THEN concat('" + date + "' - INTERVAL 3 WEEK,' - ', '" + date + "' - INTERVAL 2 WEEK - INTERVAL 1 DAY) " +
//                    "ELSE concat('" + date + "' - INTERVAL 4 WEEK, ' - ','" + date + "' - INTERVAL 3 WEEK- INTERVAL 1 DAY) " +
//                    "END AS period, ROUND(SUM(totalPrice), 2) AS sum FROM sys.ORDER " +
//                    "WHERE CreatedDate >= ('" + date + "' - INTERVAL 4 WEEK) AND UserName_Customer = '" + userName + "' " +
//                    "GROUP BY period ORDER BY period ASC) AS  R";
            Statement stmt = con.prepareStatement(query);
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                lhm.put(result.getString("CreatedDate"), result.getFloat("Sum"));
            }
        }
        catch (SQLException se)
        {
            Log.e("ERROR", se.getMessage());
        }
        return lhm;
    }
}
