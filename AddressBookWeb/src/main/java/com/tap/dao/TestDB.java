package com.tap.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDB {
    public static void main(String[] args) {
        try {
            // Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully!");
            
            // Test connection
            String url = "jdbc:mysql://localhost:3306/addressbook";
            String username = "root";
            String password = "Avi@143563";
            
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully!");
            
            conn.close();
            System.out.println("Connection closed successfully!");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}