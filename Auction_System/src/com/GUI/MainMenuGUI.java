package com.GUI;

import com.DAO.AuctionDAO;
import com.DAO.ItemDAO;
import com.Entities.Auction;
import com.Entities.Item;
import com.Utilities.GUIUtils;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MainMenuGUI extends JFrame {

    private JLabel noAuctionsLabel;
    private JLabel dateTimeLabel;
    // Explicitly initialize timers to avoid "cannot be resolved" errors
    private Timer refreshTimer = null;
    private Timer dateTimeTimer = null;
    private final DefaultTableModel tableModel;
    private final Map<Integer, ImageIcon> imageCache = new ConcurrentHashMap<>(); // Cache for images
    private Set<Integer> currentAuctionIds = new HashSet<>();
    private final int userID;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public MainMenuGUI(int userID) {
        this.userID = userID;
        
        setTitle("Auction System - Main Page");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set background color
        getContentPane().setBackground(new Color(240, 240, 240));

        // Setup header and menu bar
        setupHeader();
        setupMenuBar();

        // Create table model with non-editable cells
        String[] columnNames = {"Picture", "Item Name", "Starting Price", "Current Bid", "Start Time", "Finish Time", "Status", "Auction ID"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? ImageIcon.class : String.class;
            }
        };

        // Create and setup the table
        JTable auctionTable = setupAuctionTable();
        
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(auctionTable);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Add a label below the table for the "no auctions" message
        noAuctionsLabel = new JLabel("No auctions available at the moment.", SwingConstants.CENTER);
        noAuctionsLabel.setForeground(Color.RED);
        noAuctionsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(noAuctionsLabel, BorderLayout.SOUTH);

        // Initial population
        boolean hasAuctions = populateAuctionTable();
        noAuctionsLabel.setVisible(!hasAuctions);

        // Start the refresh timer
        startRefreshTimer();

        // Center the frame on the screen
        setLocationRelativeTo(null);
        
        // Add window listener for cleanup
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                cleanup();
            }
        });
    }

    private void setupHeader() {
        // Create the header panel using utility method
        JPanel headerPanel = GUIUtils.createHeaderPanel("Auction System");
        
        // Add the date and time label below the title
        dateTimeLabel = new JLabel();
        dateTimeLabel.setForeground(Color.WHITE);
        dateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dateTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Initialize with current time before adding to panel
        dateTimeLabel.setText(new SimpleDateFormat("HH:mm:ss | dd-MM-yyyy").format(new Date()));
        
        headerPanel.add(dateTimeLabel);

        // Add the header panel to the frame
        add(headerPanel, BorderLayout.NORTH);

        // Start the timer to update the date and time AFTER adding to UI
        startDateTimeUpdater();
    }
    
    private void setupMenuBar() {
        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(70, 130, 180));
        menuBar.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Create menus using utility method
        JMenu profileMenu = GUIUtils.createMenu("Profile", Color.WHITE, 16);
        JMenu auctionsMenu = GUIUtils.createMenu("Create Auction", Color.WHITE, 16);
        JMenu reportMenu = GUIUtils.createMenu("Report", Color.WHITE, 16);
        JMenu paymentMenu = GUIUtils.createMenu("Payments", Color.WHITE, 16);

        // Add mouse listeners using lambda for cleaner code
        profileMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                navigateTo(VisitProfileGUI.class);
            }
        });

        auctionsMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                navigateTo(CreateAuctionGUI.class);
            }
        });

        reportMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                navigateTo(CreateReportGUI.class);
            }
        });

        paymentMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                navigateTo(MakePaymentGUI.class);
            }
        });
        
        menuBar.add(profileMenu);
        menuBar.add(auctionsMenu);
        menuBar.add(reportMenu);
        menuBar.add(paymentMenu);
        setJMenuBar(menuBar);
    }

    private JTable setupAuctionTable() {
        JTable auctionTable = new JTable(tableModel);
        auctionTable.setRowHeight(100);
        auctionTable.setFont(new Font("Arial", Font.PLAIN, 14));
        auctionTable.setBackground(Color.WHITE);
        auctionTable.setGridColor(new Color(200, 200, 200));

        // Style the table header
        JTableHeader tableHeader = auctionTable.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 16));
        tableHeader.setBackground(new Color(70, 130, 180));
        tableHeader.setForeground(Color.WHITE);

        // Center align table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 1; i < auctionTable.getColumnCount(); i++) {
            auctionTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Hide the Auction ID column
        auctionTable.getColumnModel().getColumn(7).setMinWidth(0);
        auctionTable.getColumnModel().getColumn(7).setMaxWidth(0);
        auctionTable.getColumnModel().getColumn(7).setWidth(0);

        // Add a mouse listener to the auction table
        auctionTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int selectedRow = auctionTable.getSelectedRow();
                if (selectedRow != -1) {
                    int auctionID = (int) tableModel.getValueAt(selectedRow, 7);
                    openMonitorAuctionGUI(auctionID);
                }
            }
        });
        
        return auctionTable;
    }

    private void startDateTimeUpdater() {
        // Make sure we don't have multiple timers running
        if (this.dateTimeTimer != null) {
            this.dateTimeTimer.stop();
        }
        
        this.dateTimeTimer = new Timer(1000, e -> {
            if (dateTimeLabel != null && dateTimeLabel.isDisplayable()) {
                SwingUtilities.invokeLater(() -> {
                    String currentDateTime = new SimpleDateFormat("HH:mm:ss | dd-MM-yyyy").format(new Date());
                    dateTimeLabel.setText(currentDateTime);
                });
            }
        });
        this.dateTimeTimer.setInitialDelay(0); // Start immediately
        this.dateTimeTimer.start();
    }

    private void startRefreshTimer() {
        // Make sure we don't have multiple timers running
        if (this.refreshTimer != null) {
            this.refreshTimer.stop();
        }
        
        // Refresh every 5 seconds instead of every second for better performance
        this.refreshTimer = new Timer(5000, e -> {
            boolean hasAuctions = populateAuctionTable();
            noAuctionsLabel.setVisible(!hasAuctions);
        });
        this.refreshTimer.setInitialDelay(0); // Start immediately
        this.refreshTimer.start();
    }

    private boolean populateAuctionTable() {
        Set<Integer> newAuctionIds = new HashSet<>();
        boolean hasAuctions = false;
        
        try {
            List<Auction> auctions = AuctionDAO.getAllStartedAuctions();
            hasAuctions = !auctions.isEmpty();
            
            // First pass: update auctions and collect IDs
            for (Auction auction : auctions) {
                int auctionID = auction.getAuctionID();
                newAuctionIds.add(auctionID);
                
                // Check if auction should be finished
                if (auction.getAuctionEndTime().isBefore(LocalDateTime.now()) || 
                    auction.getAuctionEndTime().isEqual(LocalDateTime.now())) {
                    if (!auction.getAuctionStatus().equalsIgnoreCase("FINISHED")) {
                        auction.setAuctionStatus("FINISHED");
                        AuctionDAO.updateAuction(auction);
                    }
                }
            }
            
            // Find auctions to remove (in current but not in new)
            Set<Integer> auctionsToRemove = new HashSet<>(currentAuctionIds);
            auctionsToRemove.removeAll(newAuctionIds);
            
            // Find auctions to add (in new but not in current)
            Set<Integer> auctionsToAdd = new HashSet<>(newAuctionIds);
            auctionsToAdd.removeAll(currentAuctionIds);
            
            // Find auctions to update (in both current and new)
            Set<Integer> auctionsToUpdate = new HashSet<>(newAuctionIds);
            auctionsToUpdate.retainAll(currentAuctionIds);
            
            // Remove rows for auctions that no longer exist
            for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
                int rowAuctionId = (int) tableModel.getValueAt(i, 7);
                if (auctionsToRemove.contains(rowAuctionId)) {
                    tableModel.removeRow(i);
                }
            }
            
            // Process auctions
            for (Auction auction : auctions) {
                int auctionID = auction.getAuctionID();
                
                // Skip if not in add or update sets
                if (!auctionsToAdd.contains(auctionID) && !auctionsToUpdate.contains(auctionID)) {
                    continue;
                }
                
                // Get item info
                Item item = ItemDAO.getItemByID(auction.getItemID());
                if (item == null) continue;
                
                // Get or load image
                ImageIcon imageIcon = getItemImage(item);
                
                // Collect row data
                String itemName = item.getItemName();
                String startingPrice = String.format("%.2f", item.getItemStartingPrice());
                String currentBid = String.format("%.2f", auction.getAuctionCurrentHighestBid());
                String startTime = auction.getAuctionStartTime().format(formatter);
                String finishTime = auction.getAuctionEndTime().format(formatter);
                String status = auction.getAuctionStatus();
                
                // Find existing row or add new one
                boolean rowFound = false;
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    if ((int)tableModel.getValueAt(i, 7) == auctionID) {
                        // Update existing row
                        updateTableRow(i, imageIcon, itemName, startingPrice, currentBid, 
                                      startTime, finishTime, status, auctionID);
                        rowFound = true;
                        break;
                    }
                }
                
                if (!rowFound) {
                    // Add new row
                    tableModel.addRow(new Object[]{
                        imageIcon, itemName, startingPrice, currentBid, 
                        startTime, finishTime, status, auctionID
                    });
                }
            }
            
            // Update current auction IDs
            currentAuctionIds = newAuctionIds;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading auctions: " + e.getMessage(), 
                                         "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        return hasAuctions;
    }
    
    private void updateTableRow(int rowIndex, ImageIcon imageIcon, String itemName, 
                              String startingPrice, String currentBid, String startTime, 
                              String finishTime, String status, int auctionID) {
        tableModel.setValueAt(imageIcon, rowIndex, 0);
        tableModel.setValueAt(itemName, rowIndex, 1);
        tableModel.setValueAt(startingPrice, rowIndex, 2);
        tableModel.setValueAt(currentBid, rowIndex, 3);
        tableModel.setValueAt(startTime, rowIndex, 4);
        tableModel.setValueAt(finishTime, rowIndex, 5);
        tableModel.setValueAt(status, rowIndex, 6);
        tableModel.setValueAt(auctionID, rowIndex, 7);
    }
    
    private ImageIcon getItemImage(Item item) {
        int itemID = item.getItemID();
        
        // Check cache first
        if (imageCache.containsKey(itemID)) {
            return imageCache.get(itemID);
        }
        
        // Create and cache the image
        ImageIcon imageIcon;
        if (item.getItemImage() != null) {
            // Scale image efficiently
            ImageIcon originalIcon = new ImageIcon(item.getItemImage());
            Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(scaledImage);
        } else {
            // Empty image placeholder
            imageIcon = new ImageIcon(new byte[0]);
        }
        
        imageCache.put(itemID, imageIcon);
        return imageIcon;
    }
    
    // Generic method to navigate to different screens
    private <T extends JFrame> void navigateTo(Class<T> destinationClass) {
        SwingUtilities.invokeLater(() -> {
            try {
                T destination = destinationClass.getConstructor(int.class).newInstance(userID);
                destination.setVisible(true);
                cleanup();
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error navigating to " + destinationClass.getSimpleName() + ": " + ex.getMessage(), 
                    "Navigation Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }
    
    // Special case for MonitorAuctionGUI which needs auctionID
    private void openMonitorAuctionGUI(int auctionID) {
        SwingUtilities.invokeLater(() -> {
            MonitorAuctionGUI monitorAuctionGUI = new MonitorAuctionGUI(auctionID, userID);
            monitorAuctionGUI.setVisible(true);
            cleanup();
            dispose();
        });
    }
    
    // Clean up resources
    private void cleanup() {
        if (this.refreshTimer != null) {
            this.refreshTimer.stop();
            this.refreshTimer = null; // Allow for garbage collection
        }
        if (this.dateTimeTimer != null) {
            this.dateTimeTimer.stop();
            this.dateTimeTimer = null; // Allow for garbage collection
        }
        // Clear image cache
        imageCache.clear();
    }
}