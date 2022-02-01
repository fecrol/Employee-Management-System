package com.employeeManagementSystem;

public class Holiday {

    private int employeeID;
    private String department;
    private String firstName;
    private String lastName;
    private MyDate dateFrom;
    private MyDate dateTo;
    String status;

    public Holiday() {

    }

    public Holiday(int employeeID, String department, String firstName, String lastName, MyDate dateFrom, MyDate dateTo, String status) {
        this.employeeID = employeeID;
        this.department = department;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.status = status;
    }

    public int getEmployeeID() {
        return this.employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public MyDate getDateFrom() {
        return this.dateFrom;
    }

    public void setDateFrom(MyDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public MyDate getDateTo() {
        return this.dateTo;
    }

    public void setDateTo(MyDate dateTo) {
        this.dateTo = dateTo;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.getEmployeeID() + "," + this.getDepartment() + "," + this.getFirstName() + "," + this.getLastName() + "," + this.getDateFrom().toString() + "," + this.getDateTo().toString() + "," + this.getStatus();
    }
}
