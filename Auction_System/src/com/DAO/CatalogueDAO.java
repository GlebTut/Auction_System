package com.DAO;

import com.Database.DBConnector;
import com.Entities.Catalogue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogueDAO {
    // Create a new catalogue
    public static void createCatalogue(Catalogue catalogue) throws SQLException {
        int i = 0;

        String sql = "INSERT INTO catalogue (catalogueTitle, auctionsList) VALUES (?, ?)";

        try (Connection con = DBConnector.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {

            pstat.setString(1, catalogue.getCatalogueTitle());
            Array sqlArray = con.createArrayOf("VARCHAR", catalogue.getAuctionsList().toArray());
            pstat.setArray(2, sqlArray);

            i = pstat.executeUpdate();
            System.out.println(i + " Catalogue successfully added to the table");
        } catch (Exception e) {
            System.err.println("Error creating catalogue: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Update an existing catalogue
    public static void updateCatalogue(Catalogue catalogue) throws SQLException {
        int i = 0;

        String sql = "UPDATE catalogue SET catalogueTitle = ?, auctionsList = ? WHERE catalogueID = ?";

        try (Connection con = DBConnector.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {

            pstat.setString(1, catalogue.getCatalogueTitle());
            Array sqlArray = con.createArrayOf("VARCHAR", catalogue.getAuctionsList().toArray());
            pstat.setArray(2, sqlArray);
            pstat.setInt(3, catalogue.getCatalogueID());

            i = pstat.executeUpdate();
            System.out.println(i + " Catalogue successfully updated in the table.");
        } catch (Exception e) {
            System.err.println("Error updating catalogue: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Retrieve a catalogue by its ID
    public static Catalogue getCatalogueByID(int catalogueID) throws SQLException {
        Catalogue catalogue = null;

        String sql = "SELECT * FROM catalogue WHERE catalogueID = ?";

        try (Connection con = DBConnector.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {

            pstat.setInt(1, catalogueID);
            ResultSet rs = pstat.executeQuery();

            if (rs.next()) {
                catalogue = new Catalogue();
                catalogue.setCatalogueID(rs.getInt("catalogueID"));
                catalogue.setCatalogueTitle(rs.getString("catalogueTitle"));
                Array sqlArray = rs.getArray("auctionsList");
                String[] auctionsList = (String[]) sqlArray.getArray();
                catalogue.setAuctionsList(List.of(auctionsList));
            }
        } catch (Exception e) {
            System.err.println("Error retrieving catalogue: " + e.getMessage());
            e.printStackTrace();
        }

        return catalogue;
    }

    // Retrieve all catalogues
    public static List<Catalogue> getAllCatalogues() throws SQLException {
        List<Catalogue> catalogues = new ArrayList<>();

        String sql = "SELECT * FROM catalogue";

        try (Connection con = DBConnector.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);
             ResultSet rs = pstat.executeQuery()) {

            while (rs.next()) {
                Catalogue catalogue = new Catalogue();
                catalogue.setCatalogueID(rs.getInt("catalogueID"));
                catalogue.setCatalogueTitle(rs.getString("catalogueTitle"));
                Array sqlArray = rs.getArray("auctionsList");
                String[] auctionsList = (String[]) sqlArray.getArray();
                catalogue.setAuctionsList(List.of(auctionsList));
                catalogues.add(catalogue);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving all catalogues: " + e.getMessage());
            e.printStackTrace();
        }

        return catalogues;
    }

    // Delete a catalogue by its ID
    public static void deleteCatalogue(int catalogueID) throws SQLException {
        int i = 0;

        String sql = "DELETE FROM catalogue WHERE catalogueID = ?";

        try (Connection con = DBConnector.getConnection();
             PreparedStatement pstat = con.prepareStatement(sql)) {

            pstat.setInt(1, catalogueID);

            i = pstat.executeUpdate();
            System.out.println(i + " Catalogue successfully deleted from the table.");
        } catch (Exception e) {
            System.err.println("Error deleting catalogue: " + e.getMessage());
            e.printStackTrace();
        }
    }
}