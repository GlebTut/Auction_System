package com.Entities;

public class Seller extends User{

    private double earnings;
    
    

    public Seller(int userid, String name, String email, String password, double earnings) {
        super(userid, name, email, password);
        this.earnings = earnings;
    }

    public double getEarnings() {
        return earnings;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }


    @Override
    public String toString() {
        return "Seller userID=" + getUserId() + ", name=" + getName() + ", earnings=" + earnings;
    }

}
