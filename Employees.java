package com.employeeManagementSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Employees {

    private ArrayList<Employee> employees;

    public Employees() {
        this.employees = new ArrayList<>();
        this.readData();
    }

    public void addEmployee(Employee employee) {
        /*
        Adds an employee object to the list of employees
         */

        this.employees.add(employee);
    }

    public void addEmployee(int index, Employee employee) {
        /*
        Adds an employee object to the list of employees
         */

        this.employees.add(index, employee);
    }

    public Employee findEmployee(int id) {
        for (Employee employee : this.employees) {
            if (id == employee.getId()) {
                return employee;
            }
        }
        return null;
    }

    public void readData() {
        File file = new File("employees.txt");

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String data = "";

            while ((data = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(data, ",");
                Employee employee = new Employee();

                while (st.hasMoreTokens()) {
                    int id = Integer.parseInt(st.nextToken().trim());
                    String department = st.nextToken().trim();
                    String firstName = st.nextToken().trim();
                    String lastName = st.nextToken().trim();
                    int age = Integer.parseInt(st.nextToken().trim());
                    char gender = st.nextToken().trim().charAt(0);
                    int holidaysRemaining = Integer.parseInt(st.nextToken().trim());
                    int holidaysRequested = Integer.parseInt(st.nextToken().trim());

                    employee.setId(id);
                    employee.setDepartment(department);
                    employee.setFirstName(firstName);
                    employee.setLastName(lastName);
                    employee.setAge(age);
                    employee.setGender(gender);
                    employee.setHolidaysRemaining(holidaysRemaining);
                    employee.setHolidaysRequested(holidaysRequested);

                    this.employees.add(employee);
                }
            }
            br.close();
        }
        catch (Exception e) {
            // DO NOTHING
        }
    }

    public Object[][] getData() {
        /*
        Creates a list of lists which hold the data of each employee.
        Used for displaying data in the table.
         */

        Object[][] data = new Object[this.employees.size()][];

        int index = 0;
        for (Employee employee : this.employees) {
            String[] employeeData = {Integer.toString(employee.getId()), employee.getDepartment(), employee.getFirstName(), employee.getLastName(), Integer.toString(employee.getAge()), Character.toString(employee.getGender()), String.valueOf(employee.getHolidaysRemaining()), String.valueOf(employee.getHolidaysRequested())};
            data[index] = employeeData;
            index++;
        }
        return data;
    }

    public ArrayList<Employee> getEmployees() {
        return this.employees;
    }
}
