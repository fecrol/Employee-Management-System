package com.employeeManagementSystem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.util.Arrays;

public class HolidayDetailsForm extends JPanel implements ActionListener, FormValidation, SaveData, HolidayCalculator {

    private String[] daysFrom = new String[31];
    private String[] daysTo = new String[31];
    private String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private String[] years = new String[5];

    private Employees employees;
    private Holidays holidays;
    private Table holidaysTable;
    private Table employeesTable;

    private ZoneId zone;

    private JPanel container;

    private JLabel id;
    private JTextField idText;

    private JLabel dateFrom;
    private JComboBox dateFromDay;
    private JComboBox dateFromMonth;
    private JComboBox dateFromYear;

    private JLabel dateTo;
    private JComboBox dateToDay;
    private JComboBox dateToMonth;
    private JComboBox dateToYear;

    private SubmitRequestButton submitRequestButton;

    public HolidayDetailsForm(Employees employees, Holidays holidays, Table holidaysTable, Table employeesTable) {

        this.employees = employees;
        this.holidays = holidays;
        this.holidaysTable = holidaysTable;
        this.employeesTable = employeesTable;

        this.container = new JPanel();
        this.container.setLayout(new GridBagLayout());

        this.zone = ZoneId.of("Europe/London");

        this.addYears();
        this.addDays(YearMonth.now(this.zone).getMonthValue(), Year.now(this.zone).getValue(), this.daysFrom);
        this.addDays(YearMonth.now(this.zone).getMonthValue(), Year.now(this.zone).getValue(), this.daysTo);

        this.id = new JLabel("Employee ID");
        GridBagConstraints idConstraints = new GridBagConstraints();
        idConstraints.gridx = 0;
        idConstraints.gridy = 0;
        idConstraints.anchor = GridBagConstraints.WEST;
        idConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.id, idConstraints);

        this.idText = new JTextField();
        GridBagConstraints idTextConstraints = new GridBagConstraints();
        idTextConstraints.gridx = 1;
        idTextConstraints.gridy = 0;
        idTextConstraints.gridwidth = 3;
        idTextConstraints.fill = GridBagConstraints.HORIZONTAL;
        idTextConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.idText, idTextConstraints);

        this.dateFrom = new JLabel("Date From");
        GridBagConstraints dateFromConstraints = new GridBagConstraints();
        dateFromConstraints.gridx = 0;
        dateFromConstraints.gridy = 1;
        dateFromConstraints.anchor = GridBagConstraints.WEST;
        dateFromConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.dateFrom, dateFromConstraints);

        this.dateFromDay = new JComboBox(this.daysFrom);
        this.dateFromDay.setSelectedIndex(LocalDate.now(this.zone).getDayOfMonth() - 1);
        GridBagConstraints dateFromDayConstraints = new GridBagConstraints();
        dateFromDayConstraints.gridx = 1;
        dateFromDayConstraints.gridy = 1;
        dateFromDayConstraints.fill = GridBagConstraints.HORIZONTAL;
        dateFromDayConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.dateFromDay, dateFromDayConstraints);

        this.dateFromMonth = new JComboBox(this.months);
        this.dateFromMonth.addActionListener(this);
        GridBagConstraints dateFromMonthConstraints = new GridBagConstraints();
        dateFromMonthConstraints.gridx = 2;
        dateFromMonthConstraints.gridy = 1;
        dateFromMonthConstraints.fill = GridBagConstraints.HORIZONTAL;
        dateFromMonthConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.dateFromMonth, dateFromMonthConstraints);

        this.dateFromYear = new JComboBox(this.years);
        this.dateFromYear.addActionListener(this);
        GridBagConstraints dateFromYearConstraints = new GridBagConstraints();
        dateFromYearConstraints.gridx = 3;
        dateFromYearConstraints.gridy = 1;
        dateFromYearConstraints.ipadx = 80;
        dateFromYearConstraints.fill = GridBagConstraints.HORIZONTAL;
        dateFromYearConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.dateFromYear, dateFromYearConstraints);

        this.dateTo = new JLabel("Date To");
        GridBagConstraints dateToConstraints = new GridBagConstraints();
        dateToConstraints.gridx = 0;
        dateToConstraints.gridy = 2;
        dateToConstraints.anchor = GridBagConstraints.WEST;
        dateToConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.dateTo, dateToConstraints);

        this.dateToDay = new JComboBox(this.daysTo);
        this.dateToDay.setSelectedIndex(LocalDate.now(this.zone).getDayOfMonth());
        GridBagConstraints dateToDayConstraints = new GridBagConstraints();
        dateToDayConstraints.gridx = 1;
        dateToDayConstraints.gridy = 2;
        dateToDayConstraints.fill = GridBagConstraints.HORIZONTAL;
        dateToDayConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.dateToDay, dateToDayConstraints);

        this.dateToMonth = new JComboBox(this.months);
        this.dateToMonth.addActionListener(this);
        GridBagConstraints dateToMonthConstraints = new GridBagConstraints();
        dateToMonthConstraints.gridx = 2;
        dateToMonthConstraints.gridy = 2;
        dateToMonthConstraints.fill = GridBagConstraints.HORIZONTAL;
        dateToMonthConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.dateToMonth, dateToMonthConstraints);

        this.dateToYear = new JComboBox(this.years);
        this.dateToYear.addActionListener(this);
        GridBagConstraints dateToYearConstraints = new GridBagConstraints();
        dateToYearConstraints.gridx = 3;
        dateToYearConstraints.gridy = 2;
        dateToYearConstraints.fill = GridBagConstraints.HORIZONTAL;
        dateToYearConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.dateToYear, dateToYearConstraints);

        this.submitRequestButton = new SubmitRequestButton("SUBMIT", this.holidays);
        this.submitRequestButton.addActionListener(this);
        GridBagConstraints submitRequestButtonConstraints = new GridBagConstraints();
        submitRequestButtonConstraints.gridx = 0;
        submitRequestButtonConstraints.gridy = 3;
        submitRequestButtonConstraints.gridwidth = 4;
        submitRequestButtonConstraints.fill = GridBagConstraints.BOTH;
        submitRequestButtonConstraints.insets = new Insets(30, 10, 10, 10);
        this.container.add(this.submitRequestButton, submitRequestButtonConstraints);

        this.container.setBorder(new TitledBorder("Holiday Request Form"));

        this.add(this.container);
        this.setBorder(new EmptyBorder(0, 10, 10, 10));
    }

    private void addDays(int month, int year, String[] days) {

        Arrays.fill(days, null);

        int daysInMonth = getDaysInMonth(month, year);

        for (int i=1; i<=daysInMonth; i++) {
            days[i - 1] = String.valueOf(i);
        }
    }

    private void addYears() {
        int currentYear = Year.now(this.zone).getValue();

        for (int i=0; i<5; i++) {
            this.years[i] = String.valueOf(currentYear + i);
        }
    }

    private void updateComboBox(JComboBox comboBox, String[] days) {
        /*
        Updates the dateFromDays and dateToDays combo box to display the correct number of days per selected month
         */

        comboBox.removeAllItems();

        for (String day : days) {
            if (day != null) {
                comboBox.addItem(day);
            }
        }
    }

    private void submitRequest() {
        String id = this.idText.getText();
        String dayFrom = this.dateFromDay.getSelectedItem().toString();
        int monthFrom = this.dateFromMonth.getSelectedIndex() + 1;
        String yearFrom = this.dateFromYear.getSelectedItem().toString();
        String dayTo = this.dateToDay.getSelectedItem().toString();
        int monthTo = this.dateToMonth.getSelectedIndex() + 1;
        String yearTo = this.dateToYear.getSelectedItem().toString();
        MyDate dateFrom = new MyDate(Integer.parseInt(dayFrom), monthFrom, Integer.parseInt(yearFrom));
        MyDate dateTo = new MyDate(Integer.parseInt(dayTo), monthTo, Integer.parseInt(yearTo));
        String status = "New";

        boolean formIsValid = this.validateForm(id, dateFrom, dateTo);

        if (formIsValid) {
            boolean idExists = checkIdExists(Integer.parseInt(id));

            if (idExists) {
                Employee employee = this.employees.findEmployee(Integer.parseInt(id));
                int daysRequested = this.calculateNumberOfDaysRequested(dateFrom, dateTo);

                if (daysRequested <= employee.getHolidaysRemaining()) {
                    this.submitRequestButton.requestHoliday(this.holidaysTable, Integer.parseInt(id), employee.getDepartment(), employee.getFirstName(), employee.getLastName(), dateFrom, dateTo, status);
                    int holidaysRemaining = employee.getHolidaysRemaining() - daysRequested;
                    int holidaysRequested = employee.getHolidaysRequested() + daysRequested;
                    employee.setHolidaysRemaining(holidaysRemaining);
                    employee.setHolidaysRequested(holidaysRequested);
                    this.employeesTable.updateSingleTableCell(holidaysRemaining, this.employees.getEmployees().indexOf(employee),6);
                    this.employeesTable.updateSingleTableCell(holidaysRequested, this.employees.getEmployees().indexOf(employee),7);
                    this.saveData("holidays.txt", this.holidays.getHolidays());
                    this.saveData("employees.txt", this.employees.getEmployees());
                    JOptionPane.showMessageDialog(null, "Holiday Request Successful", "Success", JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Number of requested days exceeds the amount of holidays remaining", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "ID Does Not Exist", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Form Invalid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public boolean validateForm(String id, String firstName, String lastName, String age, String gender) {
        return false;
    }

    @Override
    public boolean validateForm(String id, MyDate dateFrom, MyDate dateTo) {
        boolean isValid = true;

        if (id.trim().equals("") || id.trim().equals(" ") || !this.isNumber(id)) {
            isValid = false;
        }
        if ((dateFrom.getYear() > dateTo.getYear()) || (dateFrom.getMonth() > dateTo.getMonth() && dateFrom.getYear() >= dateTo.getYear()) || (dateFrom.getYear() == dateTo.getYear() && dateFrom.getMonth() == dateTo.getMonth() && dateFrom.getDay() > dateTo.getDay())) {
            isValid = false;
        }

        LocalDate from = LocalDate.of(dateFrom.getYear(), dateFrom.getMonth(), dateFrom.getDay());
        LocalDate today = LocalDate.of(LocalDate.now(this.zone).getYear(), LocalDate.now(this.zone).getMonthValue(), LocalDate.now(this.zone).getDayOfMonth());

        if (from.isBefore(today)) {
            isValid = false;
        }

        return isValid;
    }

    @Override
    public boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean checkIdExists(int id) {
        for (Employee employee : this.employees.getEmployees()) {
            if (employee.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.dateFromMonth || e.getSource() == this.dateFromYear) {
            this.addDays(this.dateFromMonth.getSelectedIndex() + 1, Integer.parseInt(this.years[this.dateFromYear.getSelectedIndex()]), this.daysFrom);
            this.updateComboBox(this.dateFromDay, this.daysFrom);
        }
        if (e.getSource() == this.dateToMonth || e.getSource() == this.dateToYear) {
            this.addDays(this.dateToMonth.getSelectedIndex() + 1, Integer.parseInt(this.years[this.dateToYear.getSelectedIndex()]), this.daysTo);
            this.updateComboBox(this.dateToDay, this.daysTo);
        }
        if (e.getSource() == this.submitRequestButton) {
            this.submitRequest();
        }
    }
}
