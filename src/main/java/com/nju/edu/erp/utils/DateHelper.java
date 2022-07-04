package com.nju.edu.erp.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.spi.DateFormatProvider;
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
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return month == Calendar.DECEMBER && day == 1;
    }

    public static boolean isSalaryDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day == 1;
    }

    public static boolean isSameAboveMonth(Date date1, Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);

        calendar.setTime(date2);
        int year2 = calendar.get(Calendar.YEAR);
        int month2 = calendar.get(Calendar.MONTH);

        return year1 == year2 && month1 == month2;
    }
}