package com.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import com.DAO.UserDAO;
import com.Entities.User;
import com.Services.UserService;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

public class RegistrationPage extends JFrame {

    // GUI components used for registration
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JLabel messagLabel;

    // Constructor for setting up registration page
    public RegistrationPage() {

        super("Auction System - Registration");

        // Using borderlayout to allow cleaner structure and seperation of fields
        setLayout(new BorderLayout());

        //-----MAIN_PANEL--------------------
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(new Color(245, 245, 245)); // light grey
        // Create empty border taking up space no drawing, specifies width of top, left, bottom, and right side 
        main.setBorder(BorderFactory.createEmptyBorder(40, 40,40, 40));

        //-------Logo/Icon------------------
        // JLabel logo = new JLabel();

        //------PAGE_Header_TEXT-------------
        JLabel header = new JLabel("Create an account for Auction System", SwingConstants.CENTER);
        header.setFont(new Font("Arial",Font.BOLD, 18));
        header.setForeground(new Color(65, 105, 255)); // royal blue
        // Center text horizontally
        header.setAlignmentX(CENTER_ALIGNMENT);
        main.add(header);
        main.add(Box.createRigidArea(new Dimension(0,20)));


        //--------Input_Panel--------------
        JPanel input = new JPanel();
        input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
        input.setBackground(new Color(255, 255, 255)); // White background
        input.setPreferredSize(new Dimension(400, 300));
        input.setMaximumSize(new Dimension(500, 400));
        input.setBorder(new EmptyBorder(60, 50, 80, 100));
        

        //---------NAME_FIELD----------------

        // Add the name label and text field 
        JPanel name = new JPanel(new FlowLayout(FlowLayout.CENTER));
        name.setBackground(new Color(255, 255, 255)); // white
        name.add(new JLabel("        Name:      "));

        nameField = new JTextField(15);
        nameField.setPreferredSize(new Dimension(150, 25));
        nameField.setMaximumSize(new Dimension(200, 30));

        // Add tooltip to help user
        nameField.setToolTipText("Enter your full name");
        name.add(nameField);
        input.add(name);
        
        //---------EMAIL_FIELD---------------

        // Add the email label and text field 
        JPanel email = new JPanel(new FlowLayout(FlowLayout.CENTER));
        email.setBackground(new Color(255, 255, 255)); // white
        email.add(new JLabel("      Email:       "));
        emailField = new JTextField(15);
        emailField.setPreferredSize(new Dimension(150, 25));
        emailField.setMaximumSize(new Dimension(200, 30));
        emailField.setToolTipText("Enter your email address");
        email.add(emailField);
        input.add(email);
        

        // --------PASSWORD_FIELD-------------

        // Add the password label and text field 
        JPanel password = new JPanel(new FlowLayout(FlowLayout.CENTER));
        password.setBackground(new Color(255, 255, 255)); // white
        password.add(new JLabel("Password:"));
        passwordField = new JPasswordField(15);
        passwordField.setPreferredSize(new Dimension(150, 25));
        passwordField.setMaximumSize(new Dimension(200, 30));
        passwordField.setToolTipText("Enter a strong password");
        password.add(passwordField);
        input.add(password);
        // input.add(Box.createRigidArea(new Dimension(0,10)));

        //-------CONFIRM_PASSWORD_FIELD--------

        // Add the confirm password label and text field 
        JPanel confirmpassword = new JPanel(new FlowLayout(FlowLayout.CENTER));
        confirmpassword.setBackground(new Color(255, 255, 255)); // white
        confirmpassword.add(new JLabel("Confirm Password:"));
        confirmPasswordField = new JPasswordField(15);
        confirmPasswordField.setPreferredSize(new Dimension(150, 25));
        confirmPasswordField.setMaximumSize(new Dimension(200, 30));
        confirmPasswordField.setToolTipText("Re-enter your password");
        confirmpassword.add(confirmPasswordField);
        input.add(confirmpassword);
        // input.add(Box.createRigidArea(new Dimension(0,10)));


        //--------------------------------------

        // Adds input panel to center of the page
        main.add(input);
        // add(input, BorderLayout.CENTER);


        //-------BOTTOM_PANEL-------------------

        // Bottom panel for register button and message labels
        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        bottom.setBackground(new Color(245, 245, 245)); // light grey matches with main 
        bottom.setBorder(new EmptyBorder(10, 20, 20, 20));

        //-------REGISTER_BUTTON-----------------
        // Add register button
        registerButton = new JButton("Register");
        registerButton.setAlignmentX(CENTER_ALIGNMENT);
        registerButton.setBackground(new Color(32,178,170)); // light sea Green back
        registerButton.setForeground(new Color(255, 255, 255)); // white text
        registerButton.setFont(new Font("Arial", Font.BOLD, 18));
        bottom.add(registerButton);
        bottom.add(Box.createRigidArea(new Dimension(0,10)));

        //------Message----------------
        
        // Add message button to give user instructions for entering details on the page
        messagLabel = new JLabel("Please fill in your details", SwingConstants.CENTER);
        messagLabel.setForeground(new Color(139,0,0)); // dark red for message
        messagLabel.setAlignmentX(CENTER_ALIGNMENT);
        bottom.add(messagLabel);

        main.add(bottom);

        add(main, BorderLayout.CENTER);

        // To exit the application when the window closes, use default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set default window size (width and height) and then centrer it on the screen
        setSize(600, 800);
        
        setLocationRelativeTo(null);  // This(null) centers the window on the screen 

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performRegistration();
            }
        });



    }
    
    // Handles retrieving user inputs from the fileds and calling service layer to register user 

    private void performRegistration() {
        // Get user inputs
        String name = nameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            messagLabel.setText("All fields are required");
            return;
        }

        if (!password.equals(confirmPassword)) {
            messagLabel.setText("Passwords dont match, try again");
            return;
        }

        try {
            User user = new User(0, name, email, password);
            UserService.registerUser(user);
            messagLabel.setText("Registration successful. Return to Login Page");
            // Change to Login page or main menu?

        } catch (Exception e) {
            messagLabel.setText("Database error: " + e.getMessage());
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RegistrationPage().setVisible(true);
            }
        });
    }
}

