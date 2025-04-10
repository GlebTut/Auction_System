package com.DAO;

import com.Database.DBConnector;
import com.Entities.Bid;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BidDAO {
    // Create a new bid
    public static void createBid(Bid bid) throws SQLException{
        Connection con = null;
        PreparedStatement pstat = null;
        String sql = "INSERT INTO bid (bidAmount, bidTime, auctionID, buyerID) VALUES (?, ?, ?, ?)";

        try {
            con = DBConnector.getConnection();
            pstat = con.prepareStatement(sql);

            pstat.setDouble(1, bid.getBidAmount());
            pstat.setTimestamp(2, Timestamp.valueOf(bid.getBidTime()));
            pstat.setInt(3, bid.getAuctionID());
            pstat.setInt(4, bid.getBuyerID());

            int i = pstat.executeUpdate();
            System.out.println(i + " Bid successfully added to the table");
        } catch (Exception e) {
            System.err.println("Error creating bid: " + e.getMessage());
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

    // Update an existing bid
    public static void updateBid(Bid bid) throws SQLException{
        Connection con = null;
        PreparedStatement pstat = null;
        String sql = "UPDATE bid SET bidAmount = ?, bidTime = ?, auctionID = ?, buyerID = ? WHERE bidID = ?";

        try {
            con = DBConnector.getConnection();
            pstat = con.prepareStatement(sql);

            pstat.setDouble(1, bid.getBidAmount());
            pstat.setTimestamp(2, Timestamp.valueOf(bid.getBidTime()));
            pstat.setInt(3, bid.getAuctionID());
            pstat.setInt(4, bid.getBuyerID());
            pstat.setInt(5, bid.getBidID());
            
            int i = pstat.executeUpdate();
            System.out.println(i + " Bid successfully updated in the table.");
        } catch (Exception e) {
            System.err.println("Error updating bid: " + e.getMessage());
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

    // Retrieve a bid by its ID
    public static Bid getBidByID(int bidID) throws SQLException {
        Bid bid = null;
        Connection con = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM bid WHERE bidID = ?";

        try {
            con = DBConnector.getConnection();
            pstat = con.prepareStatement(sql);
            pstat.setInt(1, bidID);
            rs = pstat.executeQuery();

            if (rs.next()) {
                bid = new Bid();
                bid.setBidID(rs.getInt("bidID"));
                bid.setBidAmount(rs.getDouble("bidAmount"));
                bid.setBidTime(rs.getTimestamp("bidTime").toLocalDateTime());
                bid.setAuctionID(rs.getInt("auctionID"));
                bid.setBuyerID(rs.getInt("buyerID"));
            }
        } catch (Exception e) {
            System.err.println("Error retrieving bid: " + e.getMessage());
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
        return bid;
    }

    // Delete a bid by its ID
    public static void deleteBid(int bidID) throws SQLException {
        Connection con = null;
        PreparedStatement pstat = null;
        String sql = "DELETE FROM bid WHERE bidID = ?";

        try {
            con = DBConnector.getConnection();
            pstat = con.prepareStatement(sql);
            pstat.setInt(1, bidID);
            int i = pstat.executeUpdate();
            System.out.println(i + " Bid successfully deleted from the table.");
        } catch (Exception e) {
            System.err.println("Error deleting bid: " + e.getMessage());
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

    // Retrieve all bids
    public List<Bid> findAll() throws SQLException {
        Connection con = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<Bid> bids = new ArrayList<>();
        String sql = "SELECT * FROM bid";

        try {
            con = DBConnector.getConnection();
            pstat = con.prepareStatement(sql);
            rs = pstat.executeQuery();

            while (rs.next()) {
                Bid bid = new Bid();
                bid.setBidID(rs.getInt("bidID"));
                bid.setBidAmount(rs.getDouble("bidAmount"));
                bid.setBidTime(rs.getTimestamp("bidTime").toLocalDateTime());
                bid.setAuctionID(rs.getInt("auctionID"));
                bid.setBuyerID(rs.getInt("buyerID"));
                bids.add(bid);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving bids: " + e.getMessage());
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
        return bids;
    }

    // Retrieve all bids for a specific auction
    public static List<Bid> getAllBidsByAuctionID(int auctionID) throws SQLException {
        Connection con = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<Bid> bids = new ArrayList<>();
        String sql = "SELECT * FROM bid WHERE auctionID = ?";

        try {
            con = DBConnector.getConnection();
            pstat = con.prepareStatement(sql);
            pstat.setInt(1, auctionID);
            rs = pstat.executeQuery();

            while (rs.next()) {
                Bid bid = new Bid();
                bid.setBidID(rs.getInt("bidID"));
                bid.setBidAmount(rs.getDouble("bidAmount"));
                bid.setBidTime(rs.getTimestamp("bidTime").toLocalDateTime());
                bid.setAuctionID(rs.getInt("auctionID"));
                bid.setBuyerID(rs.getInt("buyerID"));
                bids.add(bid);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving bids for auction: " + e.getMessage());
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
        return bids;
    }
}
