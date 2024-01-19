//Erick Lopez
//Advanced Java
//OCCC Fall 2023
//OCCCDate

import java.util.GregorianCalendar;
import java.io.Serializable;

public class OCCCDate implements Serializable {
    private int dayOfMonth;
    private int monthOfYear;
    private int year;
    private GregorianCalendar gc;
    private boolean dateFormat = FORMAT_US;
    private boolean dateStyle = STYLE_NUMBERS;
    private boolean dateDayName = SHOW_DAY_NAME;

    public static final boolean FORMAT_US = true;
    public static final boolean FORMAT_EURO = false;
    public static final boolean STYLE_NUMBERS = true;
    public static final boolean STYLE_NAMES = false;
    public static final boolean SHOW_DAY_NAME = true;
    public static final boolean HIDE_DAY_NAME = false;

    public OCCCDate() {
        this.gc = new GregorianCalendar();
        this.dayOfMonth = gc.get(GregorianCalendar.DAY_OF_MONTH);
        this.monthOfYear = gc.get(GregorianCalendar.MONTH);
        this.year = gc.get(GregorianCalendar.YEAR);
    }

    public OCCCDate(int day, int month, int year) {
        this.gc = new GregorianCalendar();
        month--;
        gc.set(year, month, day);

        if (checkDate(day, month, year, gc)) {
            this.dayOfMonth = gc.get(GregorianCalendar.DAY_OF_MONTH);
            this.monthOfYear = gc.get(GregorianCalendar.MONTH);
            this.monthOfYear++;
            this.year = gc.get(GregorianCalendar.YEAR);
        } else {
            this.dayOfMonth = gc.get(GregorianCalendar.DAY_OF_MONTH);
            this.monthOfYear = gc.get(GregorianCalendar.MONTH);
            this.monthOfYear++;
            this.year = gc.get(GregorianCalendar.YEAR);
            throw new InvalidOCCCDateException();
        }
    }

    public OCCCDate(OCCCDate d) {
        gc = new GregorianCalendar(d.year, d.monthOfYear--, d.dayOfMonth);
    }

    public OCCCDate(GregorianCalendar gc) {
        this.dayOfMonth = gc.get(GregorianCalendar.DAY_OF_MONTH);
        this.monthOfYear = gc.get(GregorianCalendar.MONTH);
        this.year = gc.get(GregorianCalendar.YEAR);
    }

    private boolean checkDate(int day, int month, int year, GregorianCalendar gc) {
        return day == gc.get(GregorianCalendar.DAY_OF_MONTH) && month == gc.get(GregorianCalendar.MONTH)
                && year == gc.get(GregorianCalendar.YEAR);

    }

    public int getDayofMonth() {
        return dayOfMonth;
    }

    public String getDayName() {
        String name = "";
        // couldn't figure out how to do it with gc so I made a switch case
        switch (gc.get(GregorianCalendar.DAY_OF_WEEK)) {
            case 1:
                name = "Sunday";
                break;
            case 2:
                name = "Monday";
                break;
            case 3:
                name = "Tuesday";
                break;
            case 4:
                name = "Wednesday";
                break;
            case 5:
                name = "Thursday";
                break;
            case 6:
                name = "Friday";
                break;
            case 7:
                name = "Saturday";
                break;
        }
        return name;
    }

    public int getMonthNumber() {
        return monthOfYear;
    }

    public String getMonthName() {
        String name = "";
        switch (monthOfYear) {
            case 1:
                name = "January";
                break;
            case 2:
                name = "February";
                break;
            case 3:
                name = "March";
                break;
            case 4:
                name = "April";
                break;
            case 5:
                name = "May";
                break;
            case 6:
                name = "June";
                break;
            case 7:
                name = "July";
                break;
            case 8:
                name = "August";
                break;
            case 9:
                name = "September";
                break;
            case 10:
                name = "October";
                break;
            case 11:
                name = "November";
                break;
            case 12:
                name = "December";
                break;
        }
        return name;
    }

    public int getYear() {
        return year;
    }

    public void setDateFormat(boolean df) {
        this.dateFormat = df;
    }

    public void setStyleFormat(boolean sf) {
        this.dateStyle = sf;
    }

    public void setDayName(boolean nf) {
        this.dateDayName = nf;
    }

    public int getDifferenceInYears() {
        return 0;
    }

    public int getDifferenceInYears(OCCCDate d) {
        return 0;
    }

    public boolean equals(OCCCDate dob) {
        return dayOfMonth == dob.dayOfMonth && monthOfYear == dob.monthOfYear && year == dob.year;
    }

    @Override
    public String toString() {
        String dateDayNameString = "";
        String dateFormatString = "";
        String finalString = "";

        if (dateDayName) {
            dateDayNameString = getDayName() + ", ";
        }

        if (dateFormat && dateStyle) {
            // US and Number
            dateFormatString = getMonthNumber() + " / " + getDayofMonth() + " / " + getYear();
        } else if (dateFormat && !dateStyle) {
            // US and Names
            dateFormatString = getMonthName() + " " + getDayofMonth() + ", " + getYear();
        } else if (!dateFormat && dateStyle) {
            // EU and Number
            dateFormatString = getDayofMonth() + " / " + getMonthNumber() + " / " + getYear();
        } else if (!dateFormat && !dateStyle) {
            // EU and Names
            dateFormatString = getDayofMonth() + " " + getMonthName() + " " + getYear();
        }

        finalString = dateFormatString;

        return finalString;
    }
}