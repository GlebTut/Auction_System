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
    private JButton loginButton;
    private JLabel messageLabel;

    // Constructor for setting up registration page
    public RegistrationPage() {

        super("Auction System - Registration");

        // Using borderlayout to allow cleaner structure and seperation of fields
        setLayout(new BorderLayout());

        //-----MAIN_PANEL--------------------
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(new Color(250, 250, 250)); // 
        // Create empty border taking up space no drawing, specifies width of top, left, bottom, and right side 
        main.setBorder(BorderFactory.createEmptyBorder(25, 25,25, 25));

        //------PAGE_Header_TEXT-------------
        JLabel header = new JLabel("Create an account for Auction System", SwingConstants.CENTER);
        header.setFont(new Font("Arial",Font.BOLD, 24));
        header.setForeground(new Color(63, 81, 181)); // royal blue

        // Center text horizontally
        header.setAlignmentX(CENTER_ALIGNMENT);
        main.add(header);
        main.add(Box.createRigidArea(new Dimension(0,20)));


        //-------SUB_HEADING------------------
        JLabel subHeading = new JLabel("Joing our auction community today");
        subHeading.setFont(new Font("Arial", Font.BOLD, 20));
        subHeading.setForeground(new Color(117, 117, 117));

        subHeading.setAlignmentX(CENTER_ALIGNMENT);
        main.add(subHeading);
        main.add(Box.createRigidArea(new Dimension(0,20)));



        //--------Input_Panel--------------
        JPanel input = new JPanel();
        input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
        input.setBackground(new Color(255,255,255)); // White background
        input.setPreferredSize(new Dimension(400, 300));
        input.setMaximumSize(new Dimension(550, 350));
        input.setBorder(new EmptyBorder(60, 20, 20, 50));
        

        //---------NAME_FIELD----------------

        // Add the name label and text field 
        JPanel name = new JPanel();
        name.setLayout(new BoxLayout(name, BoxLayout.X_AXIS));
        name.setBackground(new Color(255, 255, 255)); // white
        name.add(new JLabel("Full Name:          "));
        name.add(Box.createRigidArea(new Dimension(10,10)));

        nameField = new JTextField(20);
        nameField.setPreferredSize(new Dimension(150, 30));
        nameField.setMaximumSize(new Dimension(240, 30));
        nameField.setToolTipText("Enter your full name");
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        name.add(nameField);
        input.add(name);
        input.add(Box.createRigidArea(new Dimension(0,20)));
        
        //---------EMAIL_FIELD---------------

        // Add the email label and text field 
        // JPanel email = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel email = new JPanel();
        email.setLayout(new BoxLayout(email, BoxLayout.X_AXIS));
        email.setBackground(new Color(255, 255, 255)); // white
        email.add(new JLabel("Email Address:   "));
        email.add(Box.createRigidArea(new Dimension(7,0)));

        emailField = new JTextField(20);
        emailField.setPreferredSize(new Dimension(150, 30));
        emailField.setMaximumSize(new Dimension(240, 30));
        emailField.setToolTipText("Enter your email address");
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        email.add(emailField);
        input.add(email);
        input.add(Box.createRigidArea(new Dimension(0,20)));
        

        // --------PASSWORD_FIELD-------------

        // Add the password label and text field 
        // JPanel password = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel password = new JPanel();
        password.setLayout(new BoxLayout(password, BoxLayout.X_AXIS));
        password.setBackground(new Color(255, 255, 255)); // white
        password.add(new JLabel("Password:        "));
        password.add(Box.createRigidArea(new Dimension(15, 0)));

        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(150, 30));
        passwordField.setMaximumSize(new Dimension(240, 30));
        passwordField.setToolTipText("Enter a strong password");
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        password.add(passwordField);
        input.add(password);
        input.add(Box.createRigidArea(new Dimension(0,20)));

        //-------CONFIRM_PASSWORD_FIELD--------

        // Add the confirm password label and text field 
        // JPanel confirmpassword = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel confirmPassword = new JPanel();
        confirmPassword.setLayout(new BoxLayout(confirmPassword, BoxLayout.X_AXIS));
        confirmPassword.setBackground(new Color(255, 255, 255)); // white
        confirmPassword.add(new JLabel("Confirm Password:"));
        confirmPassword.add(Box.createRigidArea(new Dimension(10, 0)));

        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setPreferredSize(new Dimension(150, 30));
        confirmPasswordField.setMaximumSize(new Dimension(220, 30));
        confirmPasswordField.setToolTipText("Re-enter your password");
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));
        confirmPassword.add(confirmPasswordField);
        input.add(confirmPassword);
        input.add(Box.createRigidArea(new Dimension(0,10)));


        // Adds input panel to center of the page
        main.add(input);

        //-------BOTTOM_PANEL-------------------

        // Bottom panel for register button and message labels
        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        bottom.setBackground(new Color(250,250,250)); // light grey matches with main 
        bottom.setBorder(new EmptyBorder(10, 20, 20, 20));

        //-------REGISTER_BUTTON-----------------
        // Add register button
        registerButton = new JButton("Register");
        registerButton.setAlignmentX(CENTER_ALIGNMENT);
        registerButton.setBackground(new Color(32,178,170)); // light sea Green back
        registerButton.setForeground(new Color(255, 255, 255)); // white text
        registerButton.setFont(new Font("Arial", Font.BOLD, 18));
        registerButton.setBorder(BorderFactory.createEmptyBorder(10,20,10, 20));
        bottom.add(Box.createHorizontalGlue());
        bottom.add(registerButton);
        bottom.add(Box.createRigidArea(new Dimension(0,10)));

        //--------BACK_TO_LOGIN-------------------
        loginButton = new JButton("Login");
        loginButton.setAlignmentX(CENTER_ALIGNMENT);
        loginButton.setBackground(new Color(255, 64, 129)); // light sea Green back
        loginButton.setForeground(new Color(255, 255, 255)); // white text
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setBorder(BorderFactory.createEmptyBorder(10,20,10, 20));
        bottom.add(Box.createHorizontalGlue());
        bottom.add(loginButton);
        bottom.add(Box.createRigidArea(new Dimension(0,10)));
        
        //------Message----------------
        
        // Add message button to give user instructions for entering details on the page
        messageLabel = new JLabel("Please fill in your details", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        messageLabel.setForeground(new Color(117, 117, 117)); // dark red for message
        messageLabel.setAlignmentX(CENTER_ALIGNMENT);
        bottom.add(messageLabel);

        main.add(bottom);

        add(main, BorderLayout.CENTER);

        // To exit the application when the window closes, use default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set default window size (width and height) and then centrer it on the screen
        setSize(800, 600);
        
        setLocationRelativeTo(null);  // This(null) centers the window on the screen 


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performRegistration();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
                dispose();
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
            messageLabel.setText("All fields are required");
            messageLabel.setForeground(new Color(244, 67, 54));
            return;
        }

        if (!password.equals(confirmPassword)) {
            messageLabel.setText("Passwords dont match, try again");
            messageLabel.setForeground(new Color(244, 67, 54));
            return;
        }

        try {
            User user = new User(0, name, email, password);
            UserService.registerUser(user);
            messageLabel.setText("Registration successful. Return to Login Page");
            messageLabel.setForeground(new Color(76, 175, 80));
            // Change to Login page or main menu?

        } catch (Exception e) {
            messageLabel.setText("Database error: " + e.getMessage());
            messageLabel.setForeground(new Color(244, 67, 54));
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