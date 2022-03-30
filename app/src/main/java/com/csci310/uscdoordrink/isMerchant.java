package com.csci310.uscdoordrink;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class isMerchant {
    public static int checkMerchant(String userName)
    {
        try
        {
            Connection con= ConnectionClass22.CONN();
            String query = "SELECT * FROM USER WHERE UserName = '"+userName + "' AND IsMerchant = '1'";
            //String query = "SELECT * FROM USER WHERE UserName = 'Leo' and UserPassword = '12345678' ";
            Statement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            //the combo exists
            if (rs.next()) return 1;

        }
        catch (SQLException se)
        {
            Log.e("ERROR", se.getMessage());
        }
        return 0;
    }
}
