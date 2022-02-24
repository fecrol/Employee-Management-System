package com.employeeManagementSystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public interface HolidayCalculator {

    default int getDaysInMonth(int month, int year) {
        YearMonth lengthOfMonth = YearMonth.of(year, Month.of(month));

        return lengthOfMonth.lengthOfMonth();
    }

    default boolean isWeekend(LocalDate date) {
        /*
        Checks if a day of the week is a weekend day (day off) to take it away from the total days of holidays
         */

        DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));

        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    default int calculateNumberOfDaysRequested(MyDate dateFrom, MyDate dateTo) {
        /*
        Calculates the number of days requested taking away weekends (days off)
         */

        int totalDays = 0;

        LocalDate from = LocalDate.of(dateFrom.getYear(), dateFrom.getMonth(), dateFrom.getDay());
        LocalDate to = LocalDate.of(dateTo.getYear(), dateTo.getMonth(), dateTo.getDay());

        int numOfDaysBetween = (int) ChronoUnit.DAYS.between(from, to) + 1;
        int year = dateFrom.getYear();
        int month = dateFrom.getMonth();
        int day = dateFrom.getDay();

        for (int i=numOfDaysBetween; i>0; i--) {
            if (!isWeekend(LocalDate.of(year, month, day))) {
                totalDays++;
            }

            day++;

            if (day >= this.getDaysInMonth(month, year)) {
                day = 1;
                month++;
            }
            if (month >= 12) {
                month = 1;
                year++;
            }
        }

        return totalDays;
    }
}
