package com.Entities;

import java.time.LocalDateTime;

public class Auction {
    // Attributes
    private int auctionID;
    private LocalDateTime auctionStartTime;
    private LocalDateTime auctionEndTime; 
    private double auctionCurrentHighestBid;
    private String auctionStatus;
    private int itemID;
    private int sellerID;
    private int buyerID;

    // Constructor
    public Auction(int auctionID, LocalDateTime auctionStartTime, LocalDateTime auctionEndTime, double auctionCurrentHighestBid, String auctionStatus, int itemID, int sellerID, int buyerID) {
        this.auctionID = auctionID;
        this.auctionStartTime = auctionStartTime;
        this.auctionEndTime = auctionEndTime;
        this.auctionCurrentHighestBid = auctionCurrentHighestBid;
        this.auctionStatus = auctionStatus;
        this.itemID = itemID;
        this.sellerID = sellerID;
        this.buyerID = buyerID;
    }
    // Default constructor
    public Auction() {
        this.auctionID = 0;
        this.auctionStartTime = LocalDateTime.now();
        this.auctionEndTime = LocalDateTime.now().plusHours(1);
        this.auctionCurrentHighestBid = 0.0;
        this.auctionStatus = "Open";
        this.itemID = 0;
        this.sellerID = 0;
        this.buyerID = 0;
    }

    // Getters and Setters
    public int getAuctionID() {
        return auctionID;
    }
    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }
    public LocalDateTime getAuctionStartTime() {
        return auctionStartTime;
    }
    public void setAuctionStartTime(LocalDateTime auctionStartTime) {
        this.auctionStartTime = auctionStartTime;
    }
    public LocalDateTime getAuctionEndTime() {
        return auctionEndTime;
    }
    public void setAuctionEndTime(LocalDateTime auctionEndTime) {
        this.auctionEndTime = auctionEndTime;
    }
    public double getAuctionCurrentHighestBid() {
        return auctionCurrentHighestBid;
    }
    public void setAuctionCurrentHighestBid(double auctionCurrentHighestBid) {
        this.auctionCurrentHighestBid = auctionCurrentHighestBid;
    }
    public String getAuctionStatus() {
        return auctionStatus;
    }
    public void setAuctionStatus(String auctionStatus) {
        this.auctionStatus = auctionStatus;
    }
    public int getItemID() {
        return itemID;
    }
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
    public int getSellerID() {
        return sellerID;
    }
    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }
    public int getBuyerID() {
        return buyerID;
    }
    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }
}
