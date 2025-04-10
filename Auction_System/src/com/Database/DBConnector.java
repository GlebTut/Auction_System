package com.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnector {

    // DB connection values match to MYSQL DB
    private static final String USER = "root";
    private static final String PASSWORD= "TeaAndHoney12!";
    private static final String URL= "jdbc:mysql://localhost:3306/auctionsystem";
    // private static final String DRIVER_CONNECTION = "com.mysql.cj.jdbc.Driver";
    
    private static Connection con;


    // Create a connection to DB
    public static void createConnection() throws SQLException {
        // Not required for JDBC 4.0+, but if problem use the code commented below
        // try {
        //     Class.forName("DRIVER_CONNECTION");  //Loads MYSQL driver to the program 
        //     con = DriverManager.getConnection("URL", "USER", "PASSWORD");
        // } catch (ClassNotFoundException e) {

        // }

        con = DriverManager.getConnection(URL, USER, PASSWORD); // Establishes connects to DB 

    }

    public static Connection getConnection() throws SQLException {
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection(URL, USER, PASSWORD); // Establishes connects to DB 
        }
        return con;
        
    }

    public static void main(String[] args) {
        // Test databse connection using a try catch
        try {
            createConnection();
            if (con != null) {
                System.out.println("Successfully connected to the DB");
            }
        } catch (SQLException e) {
            System.err.println("Failed to coonect to DB");
            e.printStackTrace();
        }

        finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}