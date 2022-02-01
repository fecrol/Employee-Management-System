package com.employeeManagementSystem;

public class Employee extends Person {

    private int id;
    private String department;
    private int holidaysRemaining;
    private int holidaysRequested;

    public Employee() {

    }

    public Employee(int id, String department, String firstName, String lastName, int age, char gender, int holidaysRemaining, int holidaysRequested) {
        super(firstName, lastName, age, gender);
        this.id = id;
        this.department = department;
        this.holidaysRemaining = holidaysRemaining;
        this.holidaysRequested = holidaysRequested;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getHolidaysRemaining() {
        return this.holidaysRemaining;
    }

    public void setHolidaysRemaining(int holidaysRemaining) {
        this.holidaysRemaining = holidaysRemaining;
    }

    public int getHolidaysRequested() {
        return this.holidaysRequested;
    }

    public void setHolidaysRequested(int holidaysRequested) {
        this.holidaysRequested = holidaysRequested;
    }

    @Override
    public String toString() {
        return this.getId() + "," + this.getDepartment() + "," + this.getFirstName() + "," + this.getLastName() + "," + this.getAge() + "," + this.getGender() + "," + this.getHolidaysRemaining() + "," + this.getHolidaysRequested();
    }
}
