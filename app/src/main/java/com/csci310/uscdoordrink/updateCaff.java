package com.csci310.uscdoordrink;

import static com.csci310.uscdoordrink.establishConnection.CONN;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class updateCaff {
    public void updateCaffIntake(Order order){
        String userName = order.getDeliveryRoute().getCustomerUsrName();
        int totalIntake = order.getTotalCaffeine();

        try
        {
            Connection con= CONN();
            //check username duplicate
            // create the java mysql update preparedstatement
            String query = "update CUSTOMER = CaffeineIntake  = ? WHERE UserName = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt   (1, totalIntake);
            preparedStmt.setString(2, userName);
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            con.close();
        }
        catch (SQLException se)
        {
            Log.e("ERROR", se.getMessage());
        }
    }

}
