package com.employeeManagementSystem;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Table extends JPanel {

    private String[] tableColumns;
    private JTable table;
    private DefaultTableModel tableModel;
    private Object[][] data;

    public Table(String[] tableColumns, Object[][] data, String borderTitle) {
        this.tableColumns = tableColumns;
        this.data = data;

        this.setLayout(new BorderLayout());

        this.table = new JTable() {
            @Override // Overrides the method to make a cell selectable but not editable
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.tableModel = (DefaultTableModel) table.getModel();
        this.tableModel.setColumnIdentifiers(this.tableColumns);
        this.table.setFillsViewportHeight(true);

        this.loadData();

        this.setBorder(new TitledBorder(borderTitle));
        JScrollPane scrollPane = new JScrollPane(this.table);
        this.add(scrollPane);
    }

    private void loadData() {
        // Loads in the employee data to display it in the table

        for (Object[] data : this.data) {
            this.tableModel.addRow(data);
        }
    }

    public void insertRow(int index, Object[] data) {
        /*
        Inserts a single row into the table to display newly added information
         */

        this.tableModel.insertRow(index, data);
    }

    public void deleteRow(int index) {
        /*
        Deletes a single row from the table at given index
         */

        this.tableModel.removeRow(index);
    }

    public void updateSingleTableCell(Object value, int row, int col) {
        /*
        Used for updating the information in a single cell of the table
         */

        this.tableModel.setValueAt(value, row, col);
    }

    public JTable getTable() {
        return  this.table;
    }
}
