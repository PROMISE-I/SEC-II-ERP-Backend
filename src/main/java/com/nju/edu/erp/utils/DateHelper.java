package com.nju.edu.erp.utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

    public static Date clearDayAndBelow(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static boolean isGMSalaryDay() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        return month == Calendar.DECEMBER;
    }

    public static boolean isSalaryDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day == 1;
    }

    public static boolean isSameYear(Date date1, Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);

        calendar.setTime(date2);
        int year2 = calendar.get(Calendar.YEAR);

        return year1 == year2;
    }

    public static boolean isSameYearAndMonth(Date date1, Date date2) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date1);
        int year1 = calender.get(Calendar.YEAR);
        int month1 = calender.get(Calendar.MONTH);

        calender.setTime(date2);
        int year2 = calender.get(Calendar.YEAR);
        int month2 = calender.get(Calendar.MONTH);

        return year1 == year2 && month1 == month2;
    }

    public static int getYearInLastMonth() {
        LocalDate today = LocalDate.now();
        today = today.minusMonths(1);
        return today.getYear();
    }

    public static int getMonthInLastMonth() {
        LocalDate today = LocalDate.now();
        today = today.minusMonths(1);
        return today.getMonth().getValue();
    }

    public static int getLastYear() {
        LocalDate today = LocalDate.now();
        today = today.minusYears(1);
        return today.getYear();
    }
}
