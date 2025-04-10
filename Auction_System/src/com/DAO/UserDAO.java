package com.DAO;

import com.Database.DBConnector;
import com.Entities.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static void createUser(User user) throws SQLException{
        int i = 0;

        String sql = "INSERT INTO user (userName, userEmail, password) VALUES (?, ?, ?)";

        try {
            // Establish connection to the DB
            Connection con = DBConnector.getConnection();

            // Create a Prepared statement for adding data into table
            PreparedStatement pstat = con.prepareStatement(sql);

            pstat.setString(1, user.getName());
            pstat.setString(2, user.getEmail());
            pstat.setString(3, user.getPassword());

            i = pstat.executeUpdate();
            System.out.println(i + " User successfully added to the table");

        } catch (Exception e) {
            System.err.println("Error creating user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updateUser(User user) throws SQLException{
        int i = 0;

        String sql = "UPDATE User set name= ?, email = ?, password = ? WHERE userid = ?";

        try {
            // Establish connection to the DB
            Connection con = DBConnector.getConnection();

            // Create a Prepared statement for adding data into table
            PreparedStatement pstat = con.prepareStatement(sql);

            pstat.setString(1, user.getName());
            pstat.setString(2, user.getEmail());
            pstat.setString(3, user.getPassword());
            pstat.setInt(4, user.getUserId());  // primary key used to access and indetify record


            i = pstat.executeUpdate();
            System.out.println(i + " User successfully updated in the table.");

        } catch (Exception e) {
            System.err.println("Error updating user: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user";
        PreparedStatement pstat = null;
        Connection con = null;
        ResultSet rs = null;
        
        try {
            // Creates a connection to DB
            con = DBConnector.getConnection();
            // Prepared Statement for retrieving data
            pstat = con.prepareStatement(sql);

            // Execute query
            rs = pstat.executeQuery();

            while (rs.next()) {
                    int userId = rs.getInt("UserID");
                    String name = rs.getString("userName");
                    String email = rs.getString("userEmail");
                    String password = rs.getString("password");
                    User user = new User(userId, name, email, password);
                    userList.add(user);
                }
        } catch (SQLException sqlException) {
            System.err.println("Error retrieving user: " + sqlException.getMessage());
            sqlException.printStackTrace();
        }
        finally {
            try {
                if(rs != null) rs.close();
                if(pstat != null) pstat.close();
                if(con != null) con.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    
        return userList;
        
    }



    public static void deleteUser(int userId) throws SQLException{
        int i =0;
        String sql = "DELETE FROM user WHERE UserID = ?";

        Connection con = null;
        PreparedStatement pstat = null;

        try {
            con = DBConnector.getConnection();

            pstat = con.prepareStatement(sql);
            pstat.setInt(1, userId);

            i = pstat.executeUpdate();
            System.out.println(i + " user successfully deleted form the table.");

        } catch (SQLException sqle) {
            System.err.println("Error deleting user: " + sqle.getMessage());
            sqle.printStackTrace();
        }

        finally {
            try {
                if(pstat != null) pstat.close();
                if(con != null)con.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM user WHERE userEmail = ?";
        Connection con = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        User user = null;

        try {
            con = DBConnector.getConnection();
            pstat = con.prepareStatement(sql);
            pstat.setString(1, email);
            rs = pstat.executeQuery();

            if (rs.next()) {
                user = new User(
                    rs.getInt("UserID"),
                    rs.getString("userName"),
                    rs.getString("userEmail"),
                    rs.getString("userPassword")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user by email: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstat != null) pstat.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        return user;
    }
 
    // Method to get user by ID
    public static User getUserByID(int userId) throws SQLException { // Renamed to match the usage in VisitProfileGUI
        String sql = "SELECT * FROM user WHERE UserID = ?";
        Connection con = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        User user = null;

        try {
            con = DBConnector.getConnection();
            pstat = con.prepareStatement(sql);
            pstat.setInt(1, userId);
            rs = pstat.executeQuery();

            // Use if instead of while since only one user is expected
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("UserID"));
                user.setName(rs.getString("userName"));
                user.setEmail(rs.getString("userEmail"));
                user.setPassword(rs.getString("userPassword")); 
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user by ID: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstat != null) pstat.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        return user;
    }
}