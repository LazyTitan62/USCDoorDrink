package com.csci310.uscdoordrink;

import static com.csci310.uscdoordrink.establishConnection.CONN;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class insertLeo {

    public boolean insertIntoSql(String userName, String passWord, int isMerchant)
    {
        try
        {
            Connection con= CONN();

            //check username duplicate
            String q = "SELECT * FROM USER WHERE UserName = '"+userName + "'";
            Statement st = con.prepareStatement(q);
            ResultSet r = st.executeQuery(q);
            if (r.next()) return false;
            //insert into table
            String query = "INSERT INTO USER (UserName, UserPassword, IsMerchant)" + "VALUES ('"+userName+"','"+passWord+"','"+isMerchant+"')";
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
