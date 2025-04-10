package com.Services;

import com.DAO.AdminDAO;
import com.Entities.Admin;
import java.sql.SQLException;

public class AdminService {

    public static void registerAdmin(Admin admin) throws SQLException {
        // Ensure that admin privileges is not null or empty 
        if (admin.getAdminPrivileges() == null || admin.getAdminPrivileges().isEmpty()) {
            throw new IllegalArgumentException("Admin privileges can't be empty.");
        }
        AdminDAO.createAdmin(admin);
    }
}
