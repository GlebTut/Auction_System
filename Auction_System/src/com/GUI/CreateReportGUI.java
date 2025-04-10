package com.GUI;

import javax.swing.*;
import java.awt.*;
import com.Entities.Report;
import com.DAO.reportDAO;
import com.Utilities.GUIUtils;

public class CreateReportGUI extends BaseGUI {
    // GUI components
    private JTextField txtReportTitle;
    private JTextArea txtReportDescription;
    private JButton btnSubmitReport;

    public CreateReportGUI(int userID) {
        super(userID, "Create Report", false);
        
        // Add the combined form panel
        add(createReportFormPanel(), BorderLayout.CENTER);

        // Center the frame
        setLocationRelativeTo(null);
    }

    private JPanel createReportFormPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240));

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Increased spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Report Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(GUIUtils.createStyledLabel("Report Title:", true), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        txtReportTitle = new JTextField(25); // Larger field for short text
        txtReportTitle.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger font
        formPanel.add(txtReportTitle, gbc);

        // Report Description
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(GUIUtils.createStyledLabel("Report Description:", true), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        txtReportDescription = new JTextArea(6, 25); // Multi-line text area for long descriptions
        txtReportDescription.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger font
        txtReportDescription.setLineWrap(true);
        txtReportDescription.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtReportDescription);
        formPanel.add(scrollPane, gbc);

        panel.add(formPanel, BorderLayout.CENTER);

        // Submit Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 240, 240));

        btnSubmitReport = GUIUtils.createStyledButton("Submit Report");
        btnSubmitReport.addActionListener(e -> {
            if (validateReportDetails()) {
                submitReport();
            }
        });
        
        buttonPanel.add(btnSubmitReport);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // Validate report details before submission
    private boolean validateReportDetails() {
        String reportTitle = txtReportTitle.getText().trim();
        String reportDescription = txtReportDescription.getText().trim();

        if (reportTitle.isEmpty()) {
            setErrorMessage("Report Title cannot be empty.");
            return false;
        }

        if (reportDescription.isEmpty()) {
            setErrorMessage("Report Description cannot be empty.");
            return false;
        }

        clearErrorMessage(); // Clear error message
        return true;
    }

    // Submit the report details
    private void submitReport() {
        try {
            // Get report details
            String reportTitle = txtReportTitle.getText().trim();
            String reportDescription = txtReportDescription.getText().trim();

            // Create and save Report
            Report report = new Report();
            report.setType(reportTitle);
            report.setContent(reportDescription);
            report.setUserId(getUserID());
            reportDAO.createReport(report); // Save report to database
            
            JOptionPane.showMessageDialog(this, "Report submitted successfully!");
            navigateToMainMenu();
        } catch (Exception ex) {
            setErrorMessage("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}