package com.GUI;

import com.Entities.User;
import com.Services.UserService;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

    // LoginPage class creates a simple login window for the Auction System.
    // This class uses JFrame to create a window and uses swing components to build 
    // a user-friendly login form.

public class LoginPage extends JFrame {
    
    // GUI components
    private JTextField emailField;  // Used for entering email
    private JPasswordField passwordField; 
    private JButton loginButton;
    private JButton registerButton;
    private JLabel messageLabel;    // Label for displaying a message(error or success messages)


    // Constructor for the login page 
    // Sets up the fram, adds different components, etc
    public LoginPage() {

        // Title for the window
        super("Auction System - Login");
        // Set frame layout using flowlayout placing components left to right
        setLayout(new FlowLayout());

        //--------MAIN_PANEL-----------
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(new Color(250, 250, 250));
        main.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        //Add Header, Form, and footerto main painel
        main.add(createHeaderPanel());
        main.add(Box.createRigidArea(new Dimension(0, 20)));

        main.add(createFormPanel());
        main.add(Box.createRigidArea(new Dimension(0, 20)));

        main.add(createFooterPanel());

        add(main, BorderLayout.CENTER);
        
       
        // To exit the application when the window closes, use default close operation(inherited from class JFrame)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        // Set default window size (width and height) and then centrer it on the screen
        setSize(600, 500);
        setLocationRelativeTo(null);  // This(null) centers the window on the screen

        // Register an ActionListener(javax.awt.event) for the Login button.
        // When button is clicked, the performLogin method will be called.
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrationPage().setVisible(true);
            }
        });
    }

    // Create the header panel with title and a subheading
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(250, 250,250));

        JLabel headerLabel = new JLabel("Login to Auction Systme", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(new Color(63, 81, 181)); // Blue
        headerLabel.setAlignmentX(CENTER_ALIGNMENT);
        headerPanel.add(headerLabel);

        JLabel subHeader = new JLabel("Welcome back to the Auction", SwingConstants.CENTER);
        subHeader.setFont(new Font("Arial", Font.PLAIN, 20));
        subHeader.setForeground(new Color(100,100,100));
        subHeader.setAlignmentX(CENTER_ALIGNMENT);
        
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headerPanel.add(subHeader);

        return headerPanel;
    }

    // Create a form with email and password fields to be filled
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(255,255,255));
        formPanel.setPreferredSize(new Dimension(400, 200));
        formPanel.setMaximumSize(new Dimension(500, 300));
        formPanel.setBorder(new EmptyBorder(20,50,20,50));

        // Add email row
        formPanel.add(createInputRow("Email Address:", 20, "Enter your email address", emailField = new JTextField()));
        formPanel.add(Box.createRigidArea(new Dimension(0,20)));

        // Add password row
        formPanel.add(createInputRow("Password:", 20, "Enter your Password", passwordField = new JPasswordField()));
        formPanel.add(Box.createRigidArea(new Dimension(0,20)));
        
        return formPanel;

    }

    private JPanel createInputRow(String labelText, int fieldsColumns, String toolTip, JTextField field) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
        row.setBackground(new Color(255,255,255));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setPreferredSize(new Dimension(120, 30));
        row.add(label);
        row.add(Box.createRigidArea(new Dimension(5,0)));

        field.setColumns(fieldsColumns);
        field.setToolTipText(toolTip);
        field.setFont(new Font("Arial", Font.PLAIN, 16));
        field.setMaximumSize(new Dimension(220, 30));
        row.add(field);
        row.setAlignmentX(CENTER_ALIGNMENT);
        
        return row;
    }

    private JPanel createFooterPanel() {
        JPanel footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setBackground(new Color(250,250,250));
        footer.setBorder(new EmptyBorder(10,20,10,20));

        JPanel button = new JPanel();
        button.setLayout(new BoxLayout(button, BoxLayout.X_AXIS));
        button.setBackground(new Color(250,250,250));

        // Add a login button
        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(32,178,170)); // sea green light
        loginButton.setForeground(new Color(255,255,255));
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Create register button
        registerButton = new JButton("Register");
        registerButton.setBackground(new Color(233, 30, 99));
        registerButton.setForeground(new Color(255,255,255));
        registerButton.setFont(new Font("Arial", Font.BOLD, 18));
        registerButton.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        button.add(Box.createHorizontalGlue());
        button.add(loginButton);
        button.add(Box.createRigidArea(new Dimension(20, 0)));
        button.add(registerButton);
        button.add(Box.createHorizontalGlue());

        footer.add(button);
        footer.add(Box.createRigidArea(new Dimension(0,10)));

        messageLabel = new JLabel("Please enter your Auction login credentials", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        messageLabel.setForeground(new Color(117,117,117)); // grey
        messageLabel.setAlignmentX(CENTER_ALIGNMENT);

        footer.add(messageLabel);


        return footer;
    }
    

    private void performLogin() {
        // Get email and password from the input fields
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            messageLabel.setText("All fields are required");
            messageLabel.setForeground(new Color(244, 67, 54)); // Red color for error
            return;
        }

        try {
            // Call the login method from the service layer
            User user = UserService.loginUser(email, password);
            if (user != null) {
                messageLabel.setText("Login Successful");
                messageLabel.setForeground(new Color(76, 175, 80)); // Green color for success

                // Transition to the MainMenuGUI with the current user's ID
                SwingUtilities.invokeLater(() -> {
                    MainMenuGUI mainMenu = new MainMenuGUI(user.getUserId());
                    mainMenu.setVisible(true);
                    dispose(); // Close the LoginPage
                });
            } else {
                messageLabel.setText("Invalid credentials");
                messageLabel.setForeground(new Color(244, 67, 54)); // Red color for error
            }
        } catch (Exception e) {
            messageLabel.setText("Login failed: " + e.getMessage());
            messageLabel.setForeground(new Color(244, 67, 54)); // Red color for error
            e.printStackTrace();
        }
    }    
}