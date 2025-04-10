package com.Services;

import com.DAO.UserDAO;
import com.Entities.User;
import java.sql.SQLException;

public class UserService {
    
    // Creates new user after users input is validated
    public static void registerUser(User user) throws SQLException {
        // Check if the users name is null or empty
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        // Check if the users email is null or empty
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }

        UserDAO.createUser(user);

    }

    // Checks a users by authenticating a users email and password
    public static User loginUser(String email, String password) throws SQLException {
        // Retrieve user email from the database using the CRUD 
        User user = UserDAO.getUserByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        // Check the password to see if it matches
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Incorrect password");
        }

        return user;
    }
}
