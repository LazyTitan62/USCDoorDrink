package com.csci310.uscdoordrink;

import static com.csci310.uscdoordrink.establishConnection.CONN;

import android.util.Log;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class insertLocation {
    public boolean insertIntoSql(String merchantName, float latitude, float longitude)
    {
        try
        {
            Connection con= CONN();
            //insert into table
            String query = "INSERT INTO MERCHANT (UserName, Location_Latitude, Location_Longtitude)" + "VALUES ('"+merchantName+"','"+latitude+"','"+longitude+"')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }
        catch (SQLException se)
        {
            Log.e("ERROR", se.getMessage());
        }
        return true;
    }
}
