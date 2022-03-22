//package com.csci310.uscdoordrink;
//
//import java.sql.*;
//
//
///**
//* A Java MySQL SELECT statement example.
//* Demonstrates the use of a SQL SELECT statement against a
//* MySQL database, called from a Java program.
//*
//* Created by Alvin Alexander, http://alvinalexander.com
//*/
//public class mysql_test
//{
//
//   public static void main(String[] args) throws SQLException {
//       String url = "jdbc:mysql://uscdoordrink-2.cpcgefjranje.us-west-1.rds.amazonaws.com/sys";
//       String username = "admin";
//       String password = "12345678";
//
//       System.out.println("Loading driver...");
//
//       try {
//           Class.forName("com.mysql.cj.jdbc.Driver");
//           System.out.println("Driver loaded!");
//       } catch (ClassNotFoundException e) {
//           throw new IllegalStateException("Cannot find the driver in the classpath!", e);
//       }
//       System.out.println("Connecting database...");
//
//
//       try {
//           //connect to online table
//           Connection connection = DriverManager.getConnection(url, username, password);
//           if(connection==null){
//               System.out.println("Connection goes wrong");
//               return;
//           }
//           System.out.println("Database connected!");
//           Statement stmt = (Statement) connection.createStatement();
//
//           //insert into table
//           String query1 = "INSERT INTO InsertDemo (Name, Age)" + "VALUES ('Jessie', 22)";
//           stmt.executeUpdate(query1);
//           System.out.println("Record is inserted in the table successfully..................");
//
//           //update inserted information in the table based on ID
//           PreparedStatement ps = connection.prepareStatement("update InsertDemo set Name=? where Id=?");
//           ps.setString(1, "Leo");
//           ps.setInt(2, 1);
//           ps.executeUpdate();
//
//       } catch (SQLException e) {
//           throw new IllegalStateException("Cannot connect the database!", e);
//       }
//   }
//}
//
