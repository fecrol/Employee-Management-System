package com.employeeManagementSystem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class EmployeeDetailsForm extends JPanel implements ActionListener, FormValidation, SaveData {

    private Employees employees;
    private Holidays holidays;
    private Table employeesTable;
    private Table holidaysTable;

    private JPanel container;

    private JLabel id;
    private JTextField idText;

    private JLabel department;
    private JComboBox departmentCombo;
    private String[] departments = {"Art & Design", "Computing", "Engineering", "Modern Languages", "Music & Sound Production", "Science"};

    private JLabel firstName;
    private JTextField firstNameText;

    private JLabel lastName;
    private JTextField lastNameText;

    private JLabel age;
    private JTextField ageText;

    private JLabel gender;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private JRadioButton nonBinaryRadio;
    private ButtonGroup radioButtons;

    private AddButton addButton;
    private DeleteButton deleteButton;

    public EmployeeDetailsForm(Employees employees, Holidays holidays, Table employeesTable, Table holidaysTable) {

        this.employees = employees;
        this.holidays = holidays;
        this.employeesTable = employeesTable;
        this.holidaysTable = holidaysTable;

        this.container = new JPanel();
        this.container.setLayout(new GridBagLayout());

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

        this.department = new JLabel("Department");
        GridBagConstraints departmentConstraints = new GridBagConstraints();
        departmentConstraints.gridx = 0;
        departmentConstraints.gridy = 1;
        departmentConstraints.anchor = GridBagConstraints.WEST;
        departmentConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.department, departmentConstraints);

        this.departmentCombo = new JComboBox(this.departments);
        GridBagConstraints departmentComboConstraints = new GridBagConstraints();
        departmentComboConstraints.gridx = 1;
        departmentComboConstraints.gridy = 1;
        departmentComboConstraints.gridwidth = 3;
        departmentComboConstraints.fill = GridBagConstraints.HORIZONTAL;
        departmentComboConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.departmentCombo, departmentComboConstraints);

        this.firstName = new JLabel("First Name");
        GridBagConstraints firstNameConstraints = new GridBagConstraints();
        firstNameConstraints.gridx = 0;
        firstNameConstraints.gridy = 2;
        firstNameConstraints.anchor = GridBagConstraints.WEST;
        firstNameConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.firstName, firstNameConstraints);

        this.firstNameText = new JTextField();
        GridBagConstraints firstNameTextConstraints = new GridBagConstraints();
        firstNameTextConstraints.gridx = 1;
        firstNameTextConstraints.gridy = 2;
        firstNameTextConstraints.gridwidth = 3;
        firstNameTextConstraints.fill = GridBagConstraints.HORIZONTAL;
        firstNameTextConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.firstNameText, firstNameTextConstraints);

        this.lastName = new JLabel("Last Name");
        GridBagConstraints lastNameConstraints = new GridBagConstraints();
        lastNameConstraints.gridx = 0;
        lastNameConstraints.gridy = 3;
        lastNameConstraints.anchor = GridBagConstraints.WEST;
        lastNameConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.lastName, lastNameConstraints);

        this.lastNameText = new JTextField();
        GridBagConstraints lastNameTextConstraints = new GridBagConstraints();
        lastNameTextConstraints.gridx = 1;
        lastNameTextConstraints.gridy = 3;
        lastNameTextConstraints.gridwidth = 3;
        lastNameTextConstraints.fill = GridBagConstraints.HORIZONTAL;
        lastNameTextConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.lastNameText, lastNameTextConstraints);

        this.age = new JLabel("Age");
        GridBagConstraints ageConstraints = new GridBagConstraints();
        ageConstraints.gridx = 0;
        ageConstraints.gridy = 4;
        ageConstraints.anchor = GridBagConstraints.WEST;
        ageConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.age, ageConstraints);

        this.ageText = new JTextField();
        GridBagConstraints ageTextConstraints = new GridBagConstraints();
        ageTextConstraints.gridx = 1;
        ageTextConstraints.gridy = 4;
        ageTextConstraints.gridwidth = 3;
        ageTextConstraints.fill = GridBagConstraints.HORIZONTAL;
        ageTextConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.ageText, ageTextConstraints);

        this.gender = new JLabel("Gender");
        GridBagConstraints genderConstraints = new GridBagConstraints();
        genderConstraints.gridx = 0;
        genderConstraints.gridy = 5;
        genderConstraints.anchor = GridBagConstraints.WEST;
        genderConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.gender, genderConstraints);

        this.maleRadio = new JRadioButton("Male");
        GridBagConstraints maleConstraints = new GridBagConstraints();
        maleConstraints.gridx = 1;
        maleConstraints.gridy = 5;
        maleConstraints.anchor = GridBagConstraints.WEST;
        maleConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.maleRadio, maleConstraints);

        this.femaleRadio = new JRadioButton("Female");
        GridBagConstraints femaleConstraints = new GridBagConstraints();
        femaleConstraints.gridx = 2;
        femaleConstraints.gridy = 5;
        femaleConstraints.anchor = GridBagConstraints.WEST;
        femaleConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.femaleRadio, femaleConstraints);

        this.nonBinaryRadio = new JRadioButton("Non-binary");
        GridBagConstraints nonBinaryConstraints = new GridBagConstraints();
        nonBinaryConstraints.gridx = 3;
        nonBinaryConstraints.gridy = 5;
        nonBinaryConstraints.anchor = GridBagConstraints.WEST;
        nonBinaryConstraints.insets = new Insets(10, 10, 10, 10);
        this.container.add(this.nonBinaryRadio, nonBinaryConstraints);

        this.radioButtons = new ButtonGroup();
        this.radioButtons.add(this.maleRadio);
        this.radioButtons.add(this.femaleRadio);
        this.radioButtons.add(this.nonBinaryRadio);

        this.addButton = new AddButton("ADD", this.employees);
        this.addButton.addActionListener(this);
        GridBagConstraints addButtonConstraints = new GridBagConstraints();
        addButtonConstraints.gridx = 0;
        addButtonConstraints.gridy = 6;
        addButtonConstraints.fill = GridBagConstraints.BOTH;
        addButtonConstraints.insets = new Insets(30, 10, 10, 10);
        this.container.add(this.addButton, addButtonConstraints);

        this.deleteButton = new DeleteButton("DELETE");
        this.deleteButton.addActionListener(this);
        this.deleteButton.setBackground(Color.red);
        GridBagConstraints deleteButtonConstraints = new GridBagConstraints();
        deleteButtonConstraints.gridx = 1;
        deleteButtonConstraints.gridy = 6;
        deleteButtonConstraints.fill = GridBagConstraints.BOTH;
        deleteButtonConstraints.insets = new Insets(30, 10, 10, 10);
        this.container.add(this.deleteButton, deleteButtonConstraints);

        this.container.setBorder(new TitledBorder("Employee Details Form"));

        this.add(this.container);
        this.setBorder(new EmptyBorder(0, 10, 10, 10));
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

    private void emptyForm() {
        // Empties the form to make form more user-friendly

        this.idText.setText("");
        this.departmentCombo.setSelectedIndex(0);
        this.firstNameText.setText("");
        this.lastNameText.setText("");
        this.ageText.setText("");

    }

    private void addEmployee() {
        String id = this.idText.getText().trim();
        String department = this.departmentCombo.getSelectedItem().toString();
        String firstName = this.firstNameText.getText();
        String lastName = this.lastNameText.getText();
        String age = this.ageText.getText();
        String gender = getSelectedButtonText(this.radioButtons);
        int holidaysRemaining = 30;
        int holidaysRequested = 0;

        boolean formIsValid = this.validateForm(id, firstName, lastName, age, gender);

        if (formIsValid) {
            boolean idExists = this.checkIdExists(Integer.parseInt(id));

            if (!idExists) {
                this.addButton.addEmployee(this.employeesTable, Integer.parseInt(id), department, firstName, lastName, Integer.parseInt(age), gender.charAt(0), holidaysRemaining, holidaysRequested);
                this.saveData("employees.txt", this.employees.getEmployees());
                this.emptyForm();
                JOptionPane.showMessageDialog(null, "Employee added successfully", "Success", JOptionPane.PLAIN_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "ID Already Exists", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Form Invalid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public boolean validateForm(String id, String firstName, String lastName, String age, String gender) {
        boolean isValid = true;

        if (id.trim().equals("") || id.trim().equals(" ") || !this.isNumber(id)) {
            isValid = false;
        }
        if (firstName.trim().equals("") || firstName.trim().equals(" ")) {
            isValid = false;
        }
        if (lastName.trim().equals("") || lastName.trim().equals(" ")) {
            isValid = false;
        }
        if (age.trim().equals("") || age.trim().equals(" ") || !this.isNumber(age) || Integer.parseInt(age) < 18) {
            isValid = false;
        }
        if (this.getSelectedButtonText(this.radioButtons) == null) {
            isValid = false;
        }

        return isValid;
    }

    @Override
    public boolean validateForm(String id, MyDate dateFrom, MyDate dateTo) {
        return false;
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
        if (e.getSource() == this.addButton) {
            this.addEmployee();
        }
        if (e.getSource() == this.deleteButton) {
            this.deleteButton.delete(this.employeesTable.getTable(), this.holidaysTable, this.employees, this.holidays);
            this.saveData("employees.txt", this.employees.getEmployees());
            this.saveData("holidays.txt", this.holidays.getHolidays());
        }
    }
}
