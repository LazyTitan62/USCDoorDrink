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
}
