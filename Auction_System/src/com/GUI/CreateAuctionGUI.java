package com.GUI;

import com.DAO.AuctionDAO;
import com.DAO.ItemDAO;
import com.Entities.Auction;
import com.Entities.Item;
import com.Utilities.GUIUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class CreateAuctionGUI extends BaseGUI {
    // GUI components
    private JTextField txtItemName, txtStartPrice;
    private JTextArea txtItemDesc;
    private JButton btnChooseImage, btnSubmitAuction;
    private byte[] itemImage;
    private JSpinner spinnerEnd;
    private JLabel imageStatusLabel;

    public CreateAuctionGUI(int userID) {
        super(userID, "Create Auction", true);
        
        // Add the combined form panel
        add(createAuctionFormPanel(), BorderLayout.CENTER);
        
        // Center the frame
        setLocationRelativeTo(null);
    }

    private JPanel createAuctionFormPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240));

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Item Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(GUIUtils.createStyledLabel("Item Name:", true), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        txtItemName = new JTextField(25);
        txtItemName.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(txtItemName, gbc);

        // Item Description
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(GUIUtils.createStyledLabel("Item Description:", true), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        txtItemDesc = new JTextArea(6, 25);
        txtItemDesc.setFont(new Font("Arial", Font.PLAIN, 16));
        txtItemDesc.setLineWrap(true);
        txtItemDesc.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtItemDesc);
        formPanel.add(scrollPane, gbc);

        // Starting Price
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(GUIUtils.createStyledLabel("Starting Price:", true), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        txtStartPrice = new JTextField(15);
        txtStartPrice.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(txtStartPrice, gbc);

        // Choose Image
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(GUIUtils.createStyledLabel("Image:", true), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        btnChooseImage = new JButton("Choose");
        btnChooseImage.setFont(new Font("Arial", Font.PLAIN, 14));
        btnChooseImage.addActionListener(e -> chooseImage());
        formPanel.add(btnChooseImage, gbc);

        // Image Status Label
        imageStatusLabel = new JLabel("No image selected");
        imageStatusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        imageStatusLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(imageStatusLabel, gbc);

        // Auction End Time
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(GUIUtils.createStyledLabel("Auction End Time:", true), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        spinnerEnd = createDateTimeSpinner();
        formPanel.add(spinnerEnd, gbc);

        panel.add(formPanel, BorderLayout.CENTER);

        // Submit Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 240, 240));

        btnSubmitAuction = GUIUtils.createStyledButton("Make Auction");
        btnSubmitAuction.addActionListener(e -> {
            if (validateItemDetails()) {
                submitAuction();
            }
        });
        
        buttonPanel.add(btnSubmitAuction);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // Create a date-time spinner with a specific format
    private JSpinner createDateTimeSpinner() {
        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(model);
        spinner.setEditor(new JSpinner.DateEditor(spinner, "yyyy-MM-dd HH:mm"));
        // Set default value to tomorrow
        Date tomorrow = new Date(System.currentTimeMillis() + 86400000);
        spinner.setValue(tomorrow);
        return spinner;
    }

    // Choose an image file and load it into the itemImage byte array
    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                // Check file size before loading
                if (selectedFile.length() > 10_000_000) { // 10MB limit
                    setErrorMessage("Image too large (max 10MB)");
                    imageStatusLabel.setText("Image too large (max 10MB)");
                    imageStatusLabel.setForeground(Color.RED);
                    return;
                }
                
                itemImage = Files.readAllBytes(selectedFile.toPath());
                imageStatusLabel.setText("Image selected: " + selectedFile.getName());
                imageStatusLabel.setForeground(new Color(0, 128, 0)); // Dark green
            } catch (IOException e) {
                setErrorMessage("Failed to load image: " + e.getMessage());
                imageStatusLabel.setText("Failed to load image");
                imageStatusLabel.setForeground(Color.RED);
            }
        } else {
            imageStatusLabel.setText("No image selected");
            imageStatusLabel.setForeground(Color.RED);
        }
    }

    // Validate item details before submission
    private boolean validateItemDetails() {
        String itemName = txtItemName.getText().trim();
        String itemDesc = txtItemDesc.getText().trim();
        String startPriceText = txtStartPrice.getText().trim();

        if (itemName.isEmpty()) {
            setErrorMessage("Item Name cannot be empty.");
            return false;
        }

        if (itemDesc.isEmpty()) {
            setErrorMessage("Item Description cannot be empty.");
            return false;
        }

        try {
            double startPrice = Double.parseDouble(startPriceText);
            if (startPrice <= 0) {
                setErrorMessage("Starting Price must be greater than 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            setErrorMessage("Starting Price must be a valid number.");
            return false;
        }

        if (itemImage == null) {
            setErrorMessage("Please select an image for the item.");
            return false;
        }

        // Validate auction end time
        LocalDateTime endTime = getDateTimeFromSpinner(spinnerEnd);
        LocalDateTime currentTime = LocalDateTime.now();
        if (!endTime.isAfter(currentTime)) {
            setErrorMessage("Auction End Time must be later than the current time.");
            return false;
        }

        clearErrorMessage(); // Clear error message
        return true;
    }

    // Submit the auction and item details to the database
    private void submitAuction() {
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            // Parse Item Details
            String itemName = txtItemName.getText().trim();
            String itemDesc = txtItemDesc.getText().trim();
            double startPrice = Double.parseDouble(txtStartPrice.getText().trim());

            // Create and save Item
            Item item = new Item();
            item.setItemName(itemName);
            item.setItemDescription(itemDesc);
            item.setItemStartingPrice(startPrice);
            item.setItemImage(itemImage);
            
            int itemID = ItemDAO.createItem(item);

            // Set default values for Auction Details
            LocalDateTime start = LocalDateTime.now();
            LocalDateTime end = getDateTimeFromSpinner(spinnerEnd);
            
            // Create and save Auction
            Auction auction = new Auction();
            auction.setAuctionStartTime(start);
            auction.setAuctionEndTime(end);
            auction.setAuctionCurrentHighestBid(0.0);
            auction.setAuctionStatus("STARTED");
            auction.setItemID(itemID);
            auction.setSellerID(getUserID());
            auction.setBuyerID(0);

            AuctionDAO.createAuction(auction);

            JOptionPane.showMessageDialog(this, "Auction and Item created successfully!");
            navigateToMainMenu();
        } catch (Exception ex) {
            setErrorMessage("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    // Get LocalDateTime from JSpinner
    private LocalDateTime getDateTimeFromSpinner(JSpinner spinner) {
        Date date = (Date) spinner.getValue();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}