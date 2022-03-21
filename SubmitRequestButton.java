package com.employeeManagementSystem;

import java.time.LocalDate;

public class SubmitRequestButton extends Button {

    private Holidays holidays;

    public SubmitRequestButton(String text, Holidays holidays) {
        super(text);
        this.holidays = holidays;
    }

    public void requestHoliday(Table holidaysTable, int id, String department, String firstName, String lastName, MyDate dateFrom, MyDate dateTo, String status) {
        Holiday holiday = new Holiday(id, department, firstName, lastName, dateFrom, dateTo, status);

        int index = 0;
        for (int i=0; i<this.holidays.getHolidays().size(); i++) {
            LocalDate date1 = LocalDate.of(holiday.getDateFrom().getYear(), holiday.getDateFrom().getMonth(), holiday.getDateFrom().getDay());
            LocalDate date2 = LocalDate.of(holidays.getHolidays().get(i).getDateFrom().getYear(), holidays.getHolidays().get(i).getDateFrom().getMonth(), holidays.getHolidays().get(i).getDateFrom().getDay());

            if (date1.isBefore(date2)) {
                break;
            }
            else {
                index++;
            }
        }

        Object[] data = {String.valueOf(id), department, firstName, lastName, dateFrom, dateTo, status};

        holidaysTable.insertRow(index, data);
        this.holidays.addHoliday(index, holiday);
    }
}
