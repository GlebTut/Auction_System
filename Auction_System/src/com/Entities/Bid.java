package com.Entities;

import java.time.LocalDateTime;

public class Bid {
    // Attributes
    private int bidID;
    private double bidAmount;
    private LocalDateTime bidTime;
    private int auctionID;
    private int buyerID;

    // Constructor
    public Bid(int bidID, double bidAmount, LocalDateTime bidTime, int auctionID, int buyerID) {
        this.bidID = bidID;
        this.bidAmount = bidAmount;
        this.bidTime = bidTime;
        this.auctionID = auctionID;
        this.buyerID = buyerID;
    }
    // Default constructor
    public Bid() {
        this.bidID = 0;
        this.bidAmount = 0.0;
        this.bidTime = LocalDateTime.now();
        this.auctionID = 0;
        this.buyerID = 0;
    }

    // Getters and Setters
    public int getBidID() {
        return bidID;
    }
    public void setBidID(int bidID) {
        this.bidID = bidID;
    }
    public double getBidAmount() {
        return bidAmount;
    }
    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }
    public LocalDateTime getBidTime() {
        return bidTime;
    }
    public void setBidTime(LocalDateTime bidTime) {
        this.bidTime = bidTime;
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
}
