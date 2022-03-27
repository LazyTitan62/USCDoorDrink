package com.csci310.uscdoordrink;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.lang.Throwable;
import java.lang.Class;
import java.util.concurrent.Executors;

import android.util.Log;
import com.mysql.fabric.xmlrpc.base.Params;
import javax.xml.transform.Result;
public class ConnectionClass extends Throwable{
    static String url = "jdbc:mysql://uscdoordrink-2.cpcgefjranje.us-west-1.rds.amazonaws.com/sys";
    static String username = "admin";
    static String password = "12345678";
    @SuppressLint("NewApi")
    public static Connection CONN() {
        Connection connection=null;
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
                if (connection == null) {
                    System.out.println("no!");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}

