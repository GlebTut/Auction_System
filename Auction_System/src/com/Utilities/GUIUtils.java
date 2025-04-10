package com.Utilities;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import java.awt.*;

/**
 * Utility class for common GUI operations
 * This implements reusable GUI components to maintain consistency across the application
 */
public class GUIUtils {
    
    /**
     * Creates a standard header panel with a title
     * 
     * @param title The title to display in the header
     * @return The configured header panel
     */
    public static JPanel createHeaderPanel(String title) {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel blue
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Create the title label and align it to the center
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        headerPanel.add(titleLabel);
        
        return headerPanel;
    }
    
    /**
     * Creates a standardized menu
     * 
     * @param text The text of the menu
     * @param foreground The foreground color
     * @param fontSize The font size
     * @return The configured menu
     */
    public static JMenu createMenu(String text, Color foreground, int fontSize) {
        JMenu menu = new JMenu(text);
        menu.setForeground(foreground);
        menu.setFont(new Font("Arial", Font.PLAIN, fontSize));
        return menu;
    }
    
    /**
     * Creates a standardized button with consistent styling
     * 
     * @param text The button text
     * @return The configured button
     */
    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180)); // Steel blue
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }
    
    /**
     * Creates a standardized label with consistent styling
     * 
     * @param text The label text
     * @param isBold Whether the text should be bold
     * @return The configured label
     */
    public static JLabel createStyledLabel(String text, boolean isBold) {
        JLabel label = new JLabel(text);
        int fontStyle = isBold ? Font.BOLD : Font.PLAIN;
        label.setFont(new Font("Arial", fontStyle, 16));
        return label;
    }
    
    /**
     * Creates a standardized table with consistent styling
     * 
     * @param model The table model
     * @return The configured table
     */
    public static JTable createStyledTable(DefaultListModel<String> model) {
        JTable table = new JTable();
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setBackground(Color.WHITE);
        table.setGridColor(new Color(200, 200, 200));
        
        // Style the table header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        
        return table;
    }
    
    /**
     * Creates a standard date-time updater timer
     * 
     * @param label The label to update with the current date and time
     * @return The configured timer
     */
    public static Timer createDateTimeTimer(JLabel label) {
        Timer timer = new Timer(1000, e -> {
            if (label != null && label.isDisplayable()) {
                String currentDateTime = new java.text.SimpleDateFormat("HH:mm:ss | dd-MM-yyyy").format(new java.util.Date());
                label.setText(currentDateTime);
            }
        });
        timer.setInitialDelay(0);
        return timer;
    }
}