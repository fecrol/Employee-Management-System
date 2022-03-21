package com.employeeManagementSystem;

public class AddButton extends Button {

    private Employees employees;

    public AddButton(String text, Employees employees) {
        super(text);
        this.employees = employees;
    }

    public void addEmployee(Table employeesTable, int id, String department, String firstName, String lastName, int age, char gender, int holidaysRemaining, int holidaysRequested) {
        Employee employee = new Employee(id, department, firstName, lastName, age, gender, holidaysRemaining, holidaysRequested);

        int index = this.employees.getEmployees().size();
        for (int i=this.employees.getEmployees().size() - 1; i>=0; i--) {
            if (id > this.employees.getEmployees().get(i).getId()) {
                break;
            }
            index--;
        }

        Object[] data = {String.valueOf(id), department, firstName, lastName, String.valueOf(age), String.valueOf(gender), String.valueOf(holidaysRemaining), String.valueOf(holidaysRequested)};

        employeesTable.insertRow(index, data);
        this.employees.addEmployee(index, employee);
    }
}
