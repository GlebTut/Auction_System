package com.Entities;

public class User {
    // 
    private int userid;
    private String name;
    private String email;
    private String password;

    // Constructor
    public User(int userid, String name, String email, String password) {
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Default constructor
    public User() {
        this.userid = 0;
        this.name = "";
        this.email = "";
        this.password = "";
    }

    // Getters and Setters
    public int getUserId() {
        return userid;
    }

    public void setId(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
