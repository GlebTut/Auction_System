package com.Entities;

public class Payment {
    //Attributes
    private int paymentID;
    private double paymentAmount;
    private String paymentStatus;
    private int auctionID;
    private int buyerID;
    private int sellerID;

    //Constructor
    public Payment(double paymentAmount, String paymentStatus, int auctionID, int buyerID, int sellerID) {
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
        this.auctionID = auctionID;
        this.buyerID = buyerID;
        this.sellerID = sellerID;
    }
    //Default constructor
    public Payment() {
        this.paymentAmount = 0.0;
        this.paymentStatus = "WAITING";
        this.auctionID = 0;
        this.buyerID = 0;
        this.sellerID = 0;
    }
    
    //Getters and Setters
    public int getPaymentID() {
        return paymentID;
    }
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }
    public double getPaymentAmount() {
        return paymentAmount;
    }
    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public int getAuctionID() {
        return auctionID;
    }
    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }
    public int getBuyerID() {
        return buyerID;
    }
    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }
    public int getSellerID() {
        return sellerID;
    }
    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }
}