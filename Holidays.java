package com.employeeManagementSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Holidays {

    private ArrayList<Holiday> holidays;

    public Holidays() {
        this.holidays = new ArrayList<>();
        this.readData();
    }

    public void addHoliday(Holiday holiday) {
        /*
        Adds a holiday object to the list of holidays
         */

        this.holidays.add(holiday);
    }

    public void addHoliday(int index, Holiday holiday) {
        /*
        Adds a holiday object to the list of holidays at specified index
         */

        this.holidays.add(index, holiday);
    }

    public void readData() {
        File file = new File("holidays.txt");

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String data = "";

            while ((data = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(data, ",");
                Holiday holiday = new Holiday();

                while (st.hasMoreTokens()) {
                    int id = Integer.parseInt(st.nextToken().trim());
                    String department = st.nextToken().trim();
                    String firstName = st.nextToken().trim();
                    String lastName = st.nextToken().trim();
                    String[] dateFromNums = st.nextToken().trim().split("/");
                    String[] dateToNums = st.nextToken().trim().split("/");
                    MyDate dateFrom = new MyDate(Integer.parseInt(dateFromNums[0]), Integer.parseInt(dateFromNums[1]), Integer.parseInt(dateFromNums[2]));
                    MyDate dateTo = new MyDate(Integer.parseInt(dateToNums[0]), Integer.parseInt(dateToNums[1]), Integer.parseInt(dateToNums[2]));
                    String status = st.nextToken().trim();;

                    holiday.setEmployeeID(id);
                    holiday.setDepartment(department);
                    holiday.setFirstName(firstName);
                    holiday.setLastName(lastName);
                    holiday.setDateFrom(dateFrom);
                    holiday.setDateTo(dateTo);
                    holiday.setStatus(status);

                    this.holidays.add(holiday);
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
        Creates a list of lists which hold the data of each holiday.
        Used for displaying data in the table.
         */

        Object[][] data = new Object[this.holidays.size()][];

        int index = 0;
        for (Holiday holiday : this.holidays) {
            String[] employeeData = {String.valueOf(holiday.getEmployeeID()), holiday.getDepartment(), holiday.getFirstName(), holiday.getLastName(), holiday.getDateFrom().toString(), holiday.getDateTo().toString(), holiday.getStatus()};
            data[index] = employeeData;
            index++;
        }
        return data;
    }

    public ArrayList<Holiday> getHolidays() {
        return this.holidays;
    }
}
