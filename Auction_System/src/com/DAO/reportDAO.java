package com.DAO;

import com.Database.DBConnector;
import com.Entities.Report;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class reportDAO {



    public static void createReport(Report report) throws SQLException{
        int i = 0;
        Connection con = null;
        PreparedStatement pstat = null;
        String sql = "INSERT INTO Report (reportType, reportContent, userID) VALUES (?, ?, ?)";

        try {
            // Establish connection to the DB
            con = DBConnector.getConnection();

            // Create a Prepared statement for adding data into table
            pstat = con.prepareStatement(sql);

            pstat.setString(1, report.getType());
            pstat.setString(2, report.getContent());
            pstat.setInt(3, report.getUserId());

            i = pstat.executeUpdate();
            System.out.println(i + " Report successfully added to the table");

        } catch (Exception e) {
            System.err.println("Error creating report: " + e.getMessage());
            e.printStackTrace();
        }
        finally {
            try {
                pstat.close();
                con.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public static void updateReport(Report report) throws SQLException{
        int i = 0;
        Connection con = null;
        PreparedStatement pstat = null;
        String sql = "UPDATE Report set type= ?, content= ? WHERE reportid = ?";

        try {
            // Establish connection to the DB
            con = DBConnector.getConnection();

            // Create a Prepared statement for adding data into table
            pstat = con.prepareStatement(sql);

            pstat.setString(1, report.getType());
            pstat.setString(2, report.getContent());
            pstat.setInt(3, report.getReportID());
            // primary key used to access and indetify record


            i = pstat.executeUpdate();
            System.out.println(i + " Report successfully updated in the table.");

        } catch (Exception e) {
            System.err.println("Error updating report: " + e.getMessage());
            e.printStackTrace();
        }
        finally {
            try {
                pstat.close();
                con.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static List<Report> getAllreports() throws SQLException {
        List<Report> reports = new ArrayList<>();
        String sql = "SELECT * FROM Report";
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
                    int reportId = rs.getInt("reportID");
                    String reportType = rs.getString("reportType");
                    String reportContent = rs.getString("reportContent");
                    int userId = rs.getInt("userID");
        
                    Report report = new Report(reportId, reportType, reportContent, userId);
                    reports.add(report);
                }
        } catch (SQLException sqlException) {
            System.err.println("Error retrieving report: " + sqlException.getMessage());
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

    
        return reports;
        
    }

    public static void deleteReport(int reportID) throws SQLException{
        int i =0;
        String sql = "DELETE FROM Report WHERE reportid = ?";

        Connection con = null;
        PreparedStatement pstat = null;

        try {
            con = DBConnector.getConnection();

            pstat = con.prepareStatement(sql);
            pstat.setInt(1, reportID);

            i = pstat.executeUpdate();
            System.out.println(i + " report successfully deleted form the table.");

        } catch (SQLException sqle) {
            System.err.println("Error deleting report: " + sqle.getMessage());
            sqle.printStackTrace();
        }

        finally {
            try {
                pstat.close();
                con.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}