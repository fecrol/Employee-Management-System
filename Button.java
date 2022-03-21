package com.employeeManagementSystem;

import javax.swing.*;

public class Button extends JButton {

    private String buttonText;

    public Button(String text) {
        this.buttonText = text;
        this.setText(this.buttonText);
        this.setFocusable(false);
    }
}
