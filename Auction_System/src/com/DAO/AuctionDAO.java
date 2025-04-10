package com.DAO;

import com.Database.DBConnector;
import com.Entities.Auction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuctionDAO {
    // Create a new auction
    public static void createAuction(Auction auction) throws SQLException {
        Connection con = null;
        PreparedStatement pstat = null;
        String sql = "INSERT INTO auction (auctionStartTime, auctionEndTime, auctionCurrentHighestBid, auctionStatus, itemID, sellerID, buyerID) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            con = DBConnector.getConnection();
            pstat = con.prepareStatement(sql);

            pstat.setTimestamp(1, Timestamp.valueOf(auction.getAuctionStartTime()));
            pstat.setTimestamp(2, Timestamp.valueOf(auction.getAuctionEndTime()));
            pstat.setDouble(3, auction.getAuctionCurrentHighestBid());
            pstat.setString(4, auction.getAuctionStatus());
            pstat.setInt(5, auction.getItemID());
            pstat.setInt(6, auction.getSellerID());
            pstat.setInt(7, auction.getBuyerID());

            int i = pstat.executeUpdate();
            System.out.println(i + " Auction successfully added to the table");
        } catch (Exception e) {
            System.err.println("Error creating auction: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pstat != null) pstat.close();
                //if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    // Update an existing auction
    public static void updateAuction(Auction auction) throws SQLException {
        Connection con = null;
        PreparedStatement pstat = null;
        String sql = "UPDATE auction SET auctionStartTime = ?, auctionEndTime = ?, auctionCurrentHighestBid = ?, auctionStatus = ?, itemID = ?, sellerID = ?, buyerID = ? WHERE auctionID = ?";

        try {
            con = DBConnector.getConnection();
            pstat = con.prepareStatement(sql);

            pstat.setTimestamp(1, Timestamp.valueOf(auction.getAuctionStartTime()));
            pstat.setTimestamp(2, Timestamp.valueOf(auction.getAuctionEndTime()));
            pstat.setDouble(3, auction.getAuctionCurrentHighestBid());
            pstat.setString(4, auction.getAuctionStatus());
            pstat.setInt(5, auction.getItemID());
            pstat.setInt(6, auction.getSellerID());
            pstat.setInt(7, auction.getBuyerID());
            pstat.setInt(8, auction.getAuctionID());

            int i = pstat.executeUpdate();
            System.out.println(i + " Auction successfully updated in the table.");
        } catch (Exception e) {
            System.err.println("Error updating auction: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pstat != null) pstat.close();
                //if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    // Retrieve an auction by its ID
    public static Auction getAuctionByID(int auctionID) throws SQLException {
        Auction auction = null;
        Connection con = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM auction WHERE auctionID = ?";

        try {
            con = DBConnector.getConnection();
            pstat = con.prepareStatement(sql);
            pstat.setInt(1, auctionID);

            rs = pstat.executeQuery();
            while (rs.next()) {
                auction = new Auction();
                auction.setAuctionID(rs.getInt("auctionID"));
                auction.setAuctionStartTime(rs.getTimestamp("auctionStartTime").toLocalDateTime());
                auction.setAuctionEndTime(rs.getTimestamp("auctionEndTime").toLocalDateTime());
                auction.setAuctionCurrentHighestBid(rs.getDouble("auctionCurrentHighestBid"));
                auction.setAuctionStatus(rs.getString("auctionStatus"));
                auction.setItemID(rs.getInt("itemID"));
                auction.setSellerID(rs.getInt("sellerID"));
                auction.setBuyerID(rs.getInt("buyerID"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving auction: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstat != null) pstat.close();
                //if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return auction;
    }

    // Delete an auction by its ID
    public static void deleteAuction(int auctionID) throws SQLException {
        Connection con = null;
        PreparedStatement pstat = null;
        String sql = "DELETE FROM auction WHERE auctionID = ?";

        try {
            con = DBConnector.getConnection();
            pstat = con.prepareStatement(sql);
            pstat.setInt(1, auctionID);

            int i = pstat.executeUpdate();
            System.out.println(i + " Auction successfully deleted from the table.");
        } catch (SQLException e) {
            System.err.println("Error deleting auction: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pstat != null) pstat.close();
                //if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    // Retrieve all auctions
    public static List<Auction> getAllAuctions() throws SQLException {
        Connection con = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<Auction> auctions = new ArrayList<>();
        String sql = "SELECT * FROM auction";

        try {
            con = DBConnector.getConnection();
            pstat = con.prepareStatement(sql);
            rs = pstat.executeQuery();

            while (rs.next()) {
                Auction auction = new Auction();
                auction.setAuctionID(rs.getInt("auctionID"));
                auction.setAuctionStartTime(rs.getTimestamp("auctionStartTime").toLocalDateTime());
                auction.setAuctionEndTime(rs.getTimestamp("auctionEndTime").toLocalDateTime());
                auction.setAuctionCurrentHighestBid(rs.getDouble("auctionCurrentHighestBid"));
                auction.setAuctionStatus(rs.getString("auctionStatus"));
                auction.setItemID(rs.getInt("itemID"));
                auction.setSellerID(rs.getInt("sellerID"));
                auction.setBuyerID(rs.getInt("buyerID"));
                auctions.add(auction);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving all auctions: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstat != null) pstat.close();
                //if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }

        return auctions;
    }

    // Retrieve all started auctions
    public static List<Auction> getAllStartedAuctions() throws SQLException {
        Connection con = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<Auction> auctions = new ArrayList<>();
        String sql = "SELECT * FROM auction WHERE auctionStatus = 'STARTED'";

        try {
            con = DBConnector.getConnection();
            pstat = con.prepareStatement(sql);
            rs = pstat.executeQuery();

            while (rs.next()) {
                Auction auction = new Auction();
                auction.setAuctionID(rs.getInt("auctionID"));
                auction.setAuctionStartTime(rs.getTimestamp("auctionStartTime").toLocalDateTime());
                auction.setAuctionEndTime(rs.getTimestamp("auctionEndTime").toLocalDateTime());
                auction.setAuctionCurrentHighestBid(rs.getDouble("auctionCurrentHighestBid"));
                auction.setAuctionStatus(rs.getString("auctionStatus"));
                auction.setItemID(rs.getInt("itemID"));
                auction.setSellerID(rs.getInt("sellerID"));
                auction.setBuyerID(rs.getInt("buyerID"));
                auctions.add(auction);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving all auctions: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstat != null) pstat.close();
                //if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }

        return auctions;
    }

    // Retrieve all finished auctions
    public static List<Auction> getAllFinishedAuctions() throws SQLException {
        Connection con = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<Auction> auctions = new ArrayList<>();
        String sql = "SELECT * FROM auction WHERE auctionStatus != 'STARTED'";

        try {
            con = DBConnector.getConnection();
            pstat = con.prepareStatement(sql);
            rs = pstat.executeQuery();

            while (rs.next()) {
                Auction auction = new Auction();
                auction.setAuctionID(rs.getInt("auctionID"));
                auction.setAuctionStartTime(rs.getTimestamp("auctionStartTime").toLocalDateTime());
                auction.setAuctionEndTime(rs.getTimestamp("auctionEndTime").toLocalDateTime());
                auction.setAuctionCurrentHighestBid(rs.getDouble("auctionCurrentHighestBid"));
                auction.setAuctionStatus(rs.getString("auctionStatus"));
                auction.setItemID(rs.getInt("itemID"));
                auction.setSellerID(rs.getInt("sellerID"));
                auction.setBuyerID(rs.getInt("buyerID"));
                auctions.add(auction);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving all auctions: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstat != null) pstat.close();
                //if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }

        return auctions;
    }
}
