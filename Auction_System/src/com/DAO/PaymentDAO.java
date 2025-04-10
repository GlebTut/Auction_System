package com.DAO;

import com.Database.DBConnector;
import com.Entities.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {
    // Create a new payment
    public static void createPayment(Payment payment) throws SQLException {
        int i = 0;

        String sql = "INSERT INTO payment (paymentAmount, paymentStatus, auctionID, buyerID, sellerID) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection con = DBConnector.getConnection();
            PreparedStatement pstat = con.prepareStatement(sql);

            pstat.setDouble(1, payment.getPaymentAmount());
            pstat.setString(2, payment.getPaymentStatus());
            pstat.setInt(3, payment.getAuctionID());
            pstat.setInt(4, payment.getBuyerID());
            pstat.setInt(5, payment.getSellerID());

            i = pstat.executeUpdate();
            System.out.println(i + " Payment successfully added to the table");
        } catch (Exception e) {
            System.err.println("Error creating payment: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Update an existing payment
    public static void updatePayment(Payment payment) throws SQLException {
        int i = 0;

        String sql = "UPDATE payment SET paymentAmount = ?, paymentStatus = ?, auctionID = ?, buyerID = ?, sellerID = ? WHERE paymentID = ?";

        try {
            Connection con = DBConnector.getConnection();
            PreparedStatement pstat = con.prepareStatement(sql);

            pstat.setDouble(1, payment.getPaymentAmount());
            pstat.setString(2, payment.getPaymentStatus());
            pstat.setInt(3, payment.getAuctionID());
            pstat.setInt(4, payment.getBuyerID());
            pstat.setInt(5, payment.getSellerID());
            pstat.setInt(6, payment.getPaymentID());

            i = pstat.executeUpdate();
            System.out.println(i + " Payment successfully updated in the table.");
        } catch (Exception e) {
            System.err.println("Error updating payment: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Retrieve a payment by its ID
    public static Payment getPaymentByID(int paymentID) throws SQLException {
        Payment payment = null;

        String sql = "SELECT * FROM payment WHERE paymentID = ?";

        try {
            Connection con = DBConnector.getConnection();
            PreparedStatement pstat = con.prepareStatement(sql);
            pstat.setInt(1, paymentID);

            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                payment = new Payment();
                payment.setPaymentID(rs.getInt("paymentID"));
                payment.setPaymentAmount(rs.getDouble("paymentAmount"));
                payment.setPaymentStatus(rs.getString("paymentStatus"));
                payment.setAuctionID(rs.getInt("auctionID"));
                payment.setBuyerID(rs.getInt("buyerID"));
                payment.setSellerID(rs.getInt("sellerID"));
            }
        } catch (Exception e) {
            System.err.println("Error retrieving payment: " + e.getMessage());
            e.printStackTrace();
        }
        return payment;
    }

    // Delete a payment by its ID
    public static void deletePayment(int paymentID) throws SQLException {
        int i = 0;
        String sql = "DELETE FROM payment WHERE paymentID = ?";

        Connection con = null;
        PreparedStatement pstat = null;

        try {
            con = DBConnector.getConnection();
            pstat = con.prepareStatement(sql);
            pstat.setInt(1, paymentID);

            i = pstat.executeUpdate();
            System.out.println(i + " Payment successfully deleted from the table.");
        } catch (Exception e) {
            System.err.println("Error deleting payment: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (pstat != null) pstat.close();
            if (con != null) con.close();
        }
    }

    // Retrieve all payments
    public static List<Payment> getAllPayments() throws SQLException {
        List<Payment> payments = new ArrayList<>();

        String sql = "SELECT * FROM payment";

        try {
            Connection con = DBConnector.getConnection();
            PreparedStatement pstat = con.prepareStatement(sql);

            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentID(rs.getInt("paymentID"));
                payment.setPaymentAmount(rs.getDouble("paymentAmount"));
                payment.setPaymentStatus(rs.getString("paymentStatus"));
                payment.setAuctionID(rs.getInt("auctionID"));
                payment.setBuyerID(rs.getInt("buyerID"));
                payment.setSellerID(rs.getInt("sellerID"));

                payments.add(payment);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving all payments: " + e.getMessage());
            e.printStackTrace();
        }
        return payments;
    }
}
