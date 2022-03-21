package com.employeeManagementSystem;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    private String title;

    public MyFrame() {
        this.title = "Workforce Management";

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle(this.title);
    }
}