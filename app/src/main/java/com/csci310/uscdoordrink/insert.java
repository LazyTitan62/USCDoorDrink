package com.csci310.uscdoordrink;

import static com.csci310.uscdoordrink.ConnectionClass.CONN;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Executors;

public class insert{
//    Connection connection;


    public void doInBackground()
    {

        try
        {
            Connection con= CONN();
//            connection =conStr.CONN();
            String query = "INSERT INTO InsertDemo (Name, Age)" + "VALUES ('William', 200)";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }
        catch (SQLException se)
        {
            Log.e("ERROR", se.getMessage());
        }
    }
}
