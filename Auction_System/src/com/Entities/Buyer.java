package com.Entities;

public class Buyer extends User{

    public Buyer(int userId, String name, String email, String password)  {
        super(userId, name, email, password);
    }

    @Override
    public String toString() {
        return "Buyer userID=" + getUserId() + ", name=" +getName();
    }

}
