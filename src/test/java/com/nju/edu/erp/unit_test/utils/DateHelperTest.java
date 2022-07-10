package com.nju.edu.erp.unit_test.utils;

import com.nju.edu.erp.utils.DateHelper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

/**
 * @author WFS
 * @date 2022/7/10 11:59
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DateHelperTest {
    @Test
    public void isSameYearTest1() {
        Date today = new Date();
        Assertions.assertTrue(DateHelper.isSameYear(today, today));
    }

    @Test
    public void isSameYearTest2() {
        Calendar calendar = Calendar.getInstance();
        Date today = new Date();

        calendar.setTime(today);
        calendar.set(Calendar.YEAR, 2019);
        Assertions.assertFalse(DateHelper.isSameYear(today, calendar.getTime()));
    }

    @Test
    public void isSameYearAndMonthTest1() {
        Date today = new Date();
        Assertions.assertTrue(DateHelper.isSameYearAndMonth(today, today));
    }

    @Test
    public void isSameYearAndMonthTest2() {
        Calendar calendar = Calendar.getInstance();
        Date today = new Date();

        calendar.setTime(today);
        calendar.set(Calendar.MONTH, 2);
        Assertions.assertFalse(DateHelper.isSameYearAndMonth(today, calendar.getTime()));
    }

    @Test
    public void getYearInLastMonthTest() {
        Assertions.assertEquals(2022, DateHelper.getYearInLastMonth());
    }

    @Test
    public void getLastYearTest() {
        Assertions.assertEquals(2021, DateHelper.getLastYear());
    }

    @Test
    public void stringToDateTest() {
        String dateStr = "2022-01-01 00:00:00";
        Date date = DateHelper.stringToDate(dateStr);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        Assertions.assertEquals(2022, calendar.get(Calendar.YEAR));
        Assertions.assertEquals(0, calendar.get(Calendar.MONTH));
        Assertions.assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
        Assertions.assertEquals(0, calendar.get(Calendar.HOUR_OF_DAY));
        Assertions.assertEquals(0, calendar.get(Calendar.MINUTE));
        Assertions.assertEquals(0, calendar.get(Calendar.MILLISECOND));
    }
}
