package com.Entities;

import java.util.List;
import java.util.ArrayList;

public class Catalogue {
    // Attributes
    private int catalogueID;
    private String catalogueTitle;
    private List<String> auctionsList; // Changed to List<String> to store auction IDs

    // Constructor
    public Catalogue(int catalogueID, String catalogueTitle) {
        this.catalogueID = catalogueID;
        this.catalogueTitle = catalogueTitle;
        this.auctionsList = new ArrayList<>();
    }

    // Default constructor
    public Catalogue() {
        this.catalogueID = 0;
        this.catalogueTitle = null;
        this.auctionsList = new ArrayList<>();
    }

    // Getters and Setters
    public int getCatalogueID() {
        return catalogueID;
    }

    public void setCatalogueID(int catalogueID) {
        this.catalogueID = catalogueID;
    }

    public String getCatalogueTitle() {
        return catalogueTitle;
    }

    public void setCatalogueTitle(String catalogueTitle) {
        this.catalogueTitle = catalogueTitle;
    }

    public List<String> getAuctionsList() {
        return auctionsList;
    }

    public void setAuctionsList(List<String> auctionsList) {
        this.auctionsList = auctionsList;
    }
}
