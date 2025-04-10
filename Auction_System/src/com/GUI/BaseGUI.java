package com.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import com.Utilities.GUIUtils;

/**
 * Base class for GUI forms to reduce code duplication and 
 * standardize common UI elements across the application.
 */
public abstract class BaseGUI extends JFrame {
    private final int userID;
    private JLabel dateTimeLabel;
    private Timer dateTimeTimer;
    private JLabel errorMessageLabel;
    
    /**
     * Constructor for BaseGUI
     * 
     * @param userID The ID of the current user
     * @param title The title for the frame
     * @param useDispose Whether to use DISPOSE_ON_CLOSE or EXIT_ON_CLOSE
     */
    public BaseGUI(int userID, String title, boolean useDispose) {
        this.userID = userID;
        
        // Set up the frame
        setTitle(title);
        setSize(900, 700);
        setDefaultCloseOperation(useDispose ? JFrame.DISPOSE_ON_CLOSE : JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set background color
        getContentPane().setBackground(new Color(240, 240, 240));

        // Set up common components
        setupHeader(title);
        setupMenuBar();
        setupErrorLabel();
        
        // Add window listener to handle cleanup
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                cleanup();
            }
        });
    }
    
    /**
     * Get the user ID
     * @return The user ID
     */
    protected int getUserID() {
        return userID;
    }
    
    /**
     * Set up the header panel with title and date/time
     * @param title The title to display
     */
    private void setupHeader(String title) {
        // Create the header panel
        JPanel headerPanel = GUIUtils.createHeaderPanel(title);
        
        // Add the date and time label below the title
        dateTimeLabel = new JLabel();
        dateTimeLabel.setForeground(Color.WHITE);
        dateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dateTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(dateTimeLabel);

        // Add the header panel to the frame
        add(headerPanel, BorderLayout.NORTH);

        // Start the timer to update the date and time
        dateTimeTimer = GUIUtils.createDateTimeTimer(dateTimeLabel);
        dateTimeTimer.start();
    }
    
    /**
     * Set up the menu bar with back button
     */
    private void setupMenuBar() {
        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(70, 130, 180));
        menuBar.setBorder(new EmptyBorder(5, 5, 5, 5));

        JMenu backMenu = GUIUtils.createMenu("Back to Main Menu", Color.WHITE, 16);

        // Add action listener to "Main Menu" menu using lambda for cleaner code
        backMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                navigateToMainMenu();
            }
        });

        menuBar.add(backMenu);
        setJMenuBar(menuBar);
    }
    
    /**
     * Set up the error label
     */
    private void setupErrorLabel() {
        // Error Message Label (at the top of the form)
        errorMessageLabel = new JLabel();
        errorMessageLabel.setForeground(Color.RED);
        errorMessageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        errorMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorMessageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(errorMessageLabel, BorderLayout.NORTH);
    }
    
    /**
     * Navigate back to the main menu
     */
    protected void navigateToMainMenu() {
        SwingUtilities.invokeLater(() -> {
            cleanup();
            MainMenuGUI mainMenuGUI = new MainMenuGUI(userID);
            mainMenuGUI.setVisible(true);
            dispose();
        });
    }
    
    /**
     * Set an error message to display
     * @param message The error message
     */
    protected void setErrorMessage(String message) {
        errorMessageLabel.setText(message);
    }
    
    /**
     * Clear the error message
     */
    protected void clearErrorMessage() {
        errorMessageLabel.setText("");
    }
    
    /**
     * Clean up resources when the frame is closed
     */
    protected void cleanup() {
        if (dateTimeTimer != null) {
            dateTimeTimer.stop();
        }
    }
}