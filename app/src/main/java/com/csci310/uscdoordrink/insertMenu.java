package com.csci310.uscdoordrink;


import android.util.Log;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class insertMenu {
    public static void InsertMenu(Item i, String UserName)
    {
        try
        {
            Connection conn= establishConnection.CONN();
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO MENU (UserName, ItemName, ItemPrice, ItemCaffeineAmount)" + "VALUES ('"+UserName+"','"+ i.getItemName() +"','"+ i.getItemPrice() +"','"+i.getItemCaffeine()+"')";
            stmt.executeUpdate(query);

        }
        catch (SQLException se)
        {
            Log.e("ERROR", se.getMessage());
        }
    }
}
