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


    public ArrayList<Item> getMenuFromDatabase(Merchant m)
    {
        ArrayList<Item> items = new ArrayList<>();
        try
        {
            Connection con= CONN();
//          connection =conStr.CONN();
            String query = "SELECT * FROM MENU WHERE UserName = 'OhYeah'";
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

    public Boolean checkCaffeineUpdate(String date, Customer customer) {
        Boolean returnValue;
        try
        {
            Connection con = CONN();
            String query = "SELECT * FROM ORDER WHERE UserName_Customer = '" + customer.getUsrName() + "' AND CreatedDate = '" + date + "'";
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
