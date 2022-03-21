package com.employeeManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class DeleteButton extends Button {

    public DeleteButton(String text) {
        super(text);
    }

    public void delete(JTable employeesTable, Table holidaysTable, Employees employees, Holidays holidays) {

        int row = employeesTable.getSelectedRow();
        if (row > -1) {
            int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this employee?", "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == 0) {
                ((DefaultTableModel)employeesTable.getModel()).removeRow(row);
                Employee employee = employees.getEmployees().remove(row);


                ArrayList<Holiday> holidaysToRemove = new ArrayList<>();

                for (Holiday holiday : holidays.getHolidays()) {
                    if (employee.getId() == holiday.getEmployeeID()) {
                        holidaysToRemove.add(holiday);
                        int index = holidays.getHolidays().indexOf(holiday);
                        index -= holidays.getHolidays().size() - holidaysTable.getTable().getRowCount();
                        holidaysTable.deleteRow(index);
                    }
                }

                holidays.getHolidays().removeAll(holidaysToRemove);
            }

        }
    }
}
