package com.employeeManagementSystem;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public interface SaveData {

    default void saveData(String pathname, ArrayList arrayList) {
        File file = new File(pathname);

        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Object object : arrayList) {
                bw.write(object.toString());
                bw.newLine();
            }

            bw.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
