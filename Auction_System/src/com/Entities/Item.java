package com.Entities;

public class Item {
    // Attributes
    private int itemID;
    private String itemName;
    private String itemDescription;
    private double itemStartingPrice;
    private byte[] itemImage; // Assuming image is stored as a byte array
    private int auctionID;

    // Constructor 
    public Item(int itemID, String itemName, String itemDescription, double itemStartingPrice, byte[] itemImage, int auctionID) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemStartingPrice = itemStartingPrice;
        this.itemImage = itemImage;
        this.auctionID = auctionID;
    }
    // Default constructor
    public Item() {
        this.itemID = 0;
        this.itemName = null;
        this.itemDescription = null;
        this.itemStartingPrice = 0.0;
        this.itemImage = null;
        this.auctionID = 0;
    }

    // Getters and Setters
    public int getItemID() {
        return itemID;
    }
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getItemDescription() {
        return itemDescription;
    }
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    public double getItemStartingPrice() {
        return itemStartingPrice;
    }
    public void setItemStartingPrice(double itemStartingPrice) {
        this.itemStartingPrice = itemStartingPrice;
    }
    public byte[] getItemImage() {
        return itemImage;
    }
    public void setItemImage(byte[] itemImage) {
        this.itemImage = itemImage;
    }
    public int getAuctionID() {
        return auctionID;
    }
    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }
}
