package com.employeeManagementSystem;

public interface FormValidation {

    public abstract boolean validateForm(String id, String firstName, String lastName, String age, String gender);

    public abstract boolean validateForm(String id, MyDate dateFrom, MyDate dateTo);

    public abstract boolean isNumber(String input);

    public abstract boolean checkIdExists(int id);
}
