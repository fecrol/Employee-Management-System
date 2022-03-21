package com.employeeManagementSystem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class HolidayStatusForm extends JPanel implements ActionListener, SaveData, HolidayCalculator {

    private Holidays holidays;
    private Employees employees;
    private Table employeesTable;
    private Table holidaysTable;

    private JPanel container;
    private JLabel status;
    private ButtonGroup radioButtons;
    private JRadioButton accepted;
    private JRadioButton declined;
    private Button updateStatusButton;

    public HolidayStatusForm(Holidays holidays, Employees employees, Table employeesTable, Table holidaysTable) {

        this.holidays = holidays;
        this.employees = employees;
        this.employeesTable = employeesTable;
        this.holidaysTable = holidaysTable;

        this.container = new JPanel();
        this.container.setLayout(new GridBagLayout());
        this.radioButtons = new ButtonGroup();

        this.status = new JLabel("Status");
        GridBagConstraints statusConstraints = new GridBagConstraints();
        statusConstraints.gridx = 0;
        statusConstraints.gridy = 0;
        statusConstraints.anchor = GridBagConstraints.WEST;
        statusConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.status, statusConstraints);

        this.accepted = new JRadioButton("Accepted");
        this.radioButtons.add(this.accepted);
        GridBagConstraints acceptedConstraints = new GridBagConstraints();
        acceptedConstraints.gridx = 1;
        acceptedConstraints.gridy = 0;
        acceptedConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.accepted, acceptedConstraints);

        this.declined = new JRadioButton("Declined");
        this.radioButtons.add(this.declined);
        GridBagConstraints declinedConstraints = new GridBagConstraints();
        declinedConstraints.gridx = 2;
        declinedConstraints.gridy = 0;
        declinedConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.declined, declinedConstraints);

        this.updateStatusButton = new Button("UPDATE STATUS");
        this.updateStatusButton.addActionListener(this);
        GridBagConstraints updateStatusButtonConstraints = new GridBagConstraints();
        updateStatusButtonConstraints.gridx = 0;
        updateStatusButtonConstraints.gridy = 1;
        updateStatusButtonConstraints.ipadx = 232;
        updateStatusButtonConstraints.gridwidth = 3;
        updateStatusButtonConstraints.fill = GridBagConstraints.BOTH;
        updateStatusButtonConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.updateStatusButton, updateStatusButtonConstraints);

        this.container.setBorder(new TitledBorder("Update Holiday Status"));

        this.setBorder(new EmptyBorder(0, 10, 10, 10));
        this.add(this.container);
    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    private boolean validateForm() {

        return this.getSelectedButtonText(this.radioButtons) != null;
    }

    private void updateStatus() {

        try {
            boolean formIsValid = validateForm();

            if (formIsValid) {
                String selectedStatus = this.getSelectedButtonText(this.radioButtons);
                Holiday holiday = this.holidays.getHolidays().get(this.holidaysTable.getTable().getSelectedRow());
                Employee employee = this.employees.findEmployee(holiday.getEmployeeID());

                if (!holiday.getStatus().equals("Declined")) {
                    if (selectedStatus.equals("Accepted")) {
                        holiday.setStatus(selectedStatus);
                        this.holidaysTable.updateSingleTableCell(selectedStatus, this.holidays.getHolidays().indexOf(holiday), 6);
                        this.saveData("holidays.txt", this.holidays.getHolidays());
                    }
                    else {
                        holiday.setStatus(selectedStatus);
                        int days = this.calculateNumberOfDaysRequested(holiday.getDateFrom(), holiday.getDateTo());
                        int holidaysRemaining = employee.getHolidaysRemaining() + days;
                        int holidaysRequested = employee.getHolidaysRequested() - days;
                        employee.setHolidaysRemaining(holidaysRemaining);
                        employee.setHolidaysRequested(holidaysRequested);
                        this.holidaysTable.updateSingleTableCell(selectedStatus, this.holidays.getHolidays().indexOf(holiday), 6);
                        this.employeesTable.updateSingleTableCell(holidaysRemaining, this.employees.getEmployees().indexOf(employee),6);
                        this.employeesTable.updateSingleTableCell(holidaysRequested, this.employees.getEmployees().indexOf(employee),7);
                        this.saveData("holidays.txt", this.holidays.getHolidays());
                        this.saveData("employees.txt", this.employees.getEmployees());
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Status cannot be changed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "No Status is Selected!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            JOptionPane.showMessageDialog(null, "No Holiday is Selected!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.updateStatusButton) {
            this.updateStatus();
        }
    }
}
