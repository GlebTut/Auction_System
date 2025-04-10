package com;


import javax.swing.SwingUtilities;

import com.GUI.LoginPage;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }
}