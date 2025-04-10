package com.Entities;

public class Admin {

    private int adminID;
    private String adminPrivileges;
    
    public Admin() {

    }

    public Admin(int adminID, String adminPrivileges) {
        this.adminID = adminID;
        this.adminPrivileges = adminPrivileges;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getAdminPrivileges() {
        return adminPrivileges;
    }

    public void setAdminPrivileges(String adminPrivileges) {
        this.adminPrivileges = adminPrivileges;
    }

    public String toString() {
        return  "Admin adminID=" + adminID + ", admin Privileges" + adminPrivileges;
    }
}
