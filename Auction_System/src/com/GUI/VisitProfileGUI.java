package com.GUI;

import com.DAO.UserDAO;
import com.Entities.User;
import com.Utilities.GUIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VisitProfileGUI extends BaseGUI {

    public VisitProfileGUI(int userID) {
        super(userID, "User Profile", false);
        
        // Create the profile details panel
        add(createProfileDetailsPanel(userID), BorderLayout.CENTER);

        // Center the frame
        setLocationRelativeTo(null);
    }

    private JPanel createProfileDetailsPanel(int userID) {
        JPanel profilePanel = new JPanel(new GridBagLayout());
        profilePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        profilePanel.setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fetch user details from the database
        User user = null;
        try {
            user = UserDAO.getUserByID(userID);
        } catch (Exception e) {
            setErrorMessage("Error fetching user details: " + e.getMessage());
            e.printStackTrace();
            return profilePanel;
        }

        // If user is null, show an error message
        if (user == null) {
            setErrorMessage("User not found.");
            return profilePanel;
        }

        // User Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        profilePanel.add(GUIUtils.createStyledLabel("Name:", true), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        profilePanel.add(createStyledValueLabel(user.getName()), gbc);

        // User Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        profilePanel.add(GUIUtils.createStyledLabel("Email:", true), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        profilePanel.add(createStyledValueLabel(user.getEmail()), gbc);

        return profilePanel;
    }

    private JLabel createStyledValueLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        return label;
    }
}