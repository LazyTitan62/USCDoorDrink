package com.csci310.uscdoordrink;

import static com.csci310.uscdoordrink.establishConnection.CONN;

import android.util.Log;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class insertCustomer {
    public void insertCus(String customerName)
    {
        try
        {
            Connection con= CONN();
            //insert into table
            String query = "INSERT INTO CUSTOMER (UserName, CaffeineIntake)" + "VALUES ('"+customerName+"','0')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }
        catch (SQLException se)
        {
            Log.e("ERROR", se.getMessage());
        }
    }
}
