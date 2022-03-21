package com.employeeManagementSystem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        String[] employeesTableColumns = {"ID", "Department", "Forename", "Surname", "Age", "Gender", "Holidays Remaining", "Holidays Requested"};
        String[] holidaysTableColumns = {"ID", "Department", "Forename", "Surname", "Date From", "Date To", "Status"};

        String employeesTableTitle = "Employee Details";
        String holidaysTableTile = "Holiday Requests";

        Employees employees = new Employees();
        Holidays holidays = new Holidays();

        MyFrame display = new MyFrame();
        JPanel tablesContainer = new JPanel();
        tablesContainer.setLayout(new BoxLayout(tablesContainer, BoxLayout.X_AXIS));
        tablesContainer.setBorder(new EmptyBorder(5, 5, 0, 10));
        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BorderLayout());
        JTable holidayFormContainer = new JTable();
        holidayFormContainer.setLayout(new BorderLayout());

        Table employeesTable = new Table(employeesTableColumns, employees.getData(), employeesTableTitle);
        Table holidaysTable = new Table(holidaysTableColumns, holidays.getData(), holidaysTableTile);

        EmployeeDetailsForm employeeDetailsForm = new EmployeeDetailsForm(employees, holidays, employeesTable, holidaysTable);
        HolidayDetailsForm holidayDetailsForm = new HolidayDetailsForm(employees, holidays, holidaysTable, employeesTable);
        HolidayStatusForm holidayStatusForm = new HolidayStatusForm(holidays, employees, employeesTable, holidaysTable);

        tablesContainer.add(employeesTable);
        tablesContainer.add(holidaysTable);

        holidayFormContainer.add(holidayDetailsForm, BorderLayout.NORTH);
        holidayFormContainer.add(holidayStatusForm, BorderLayout.CENTER);
        formContainer.add(employeeDetailsForm, BorderLayout.NORTH);
        formContainer.add(holidayFormContainer, BorderLayout.CENTER);

        display.add(formContainer, BorderLayout.WEST);
        display.add(tablesContainer, BorderLayout.CENTER);

        display.setVisible(true);
    }
}
