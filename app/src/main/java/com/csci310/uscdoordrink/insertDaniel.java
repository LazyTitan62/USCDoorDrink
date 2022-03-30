package com.csci310.uscdoordrink;

import static com.csci310.uscdoordrink.ConnectionClass22.CONN;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class insertDaniel {
//    Connection connection;




//        try
//        {
//            Connection con= CONN();
////            connection =conStr.CONN();
//            String query = "INSERT INTO MERCHANT (UserName,Location_Latitude,Location_Longtitude)" + "VALUES ('lyon', 34.021,-118.286)";
//            Statement stmt = con.createStatement();
//            stmt.executeUpdate(query);
//        }
//        catch (SQLException se)
//        {
//            Log.e("ERROR", se.getMessage());
//        }
        public List<HashMap<String,String>> doInBackground()
        {
            //String s=null;
            List<HashMap<String,String>> allResult=new ArrayList<HashMap<String,String>>();
            try
            {
                Connection con= CONN();
//            connection =conStr.CONN();
                String query = "SELECT * from MERCHANT";
                Statement stmt = con.createStatement();
                ResultSet rs=stmt.executeQuery(query);
                while (rs.next()) {
                    HashMap<String,String> eachResult=new HashMap<String,String>();
                    eachResult.put("username",rs.getString("UserName"));
                    eachResult.put("latitude",rs.getString("Location_Latitude"));
                    eachResult.put( "lon",rs.getString("Location_Longtitude"));
                    allResult.add(eachResult);
                }


            }
            catch (SQLException se)
            {
                Log.e("ERROR", se.getMessage());
            }
            //s is Daniel
            return allResult;
        }

}
