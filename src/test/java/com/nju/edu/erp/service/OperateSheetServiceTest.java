package com.nju.edu.erp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperateSheetServiceTest {
    @Autowired
    OperateSheetService operateSheetService;

    @Test
    public void getFinalIncomeTest(){
        String begin = "2022-05-20";
        String end = "2022-07-10";
        BigDecimal ans = operateSheetService.calculateFinalIncome(begin, end);
        BigDecimal expected = BigDecimal.valueOf(1071600.00);
        assert ans.compareTo(expected) == 0;
    }

    @Test
    public void getDiscountedAmountTest(){
        String begin = "2022-05-01";
        String end = "2022-07-10";
        BigDecimal ans = operateSheetService.calculateDiscountedAmount(begin, end);
        BigDecimal expected = BigDecimal.valueOf(268400.00);
        assert expected.compareTo(ans) == 0;
    }

    @Test
    public void findCostTest(){
        String begin = "2022-04-10";
        String end = "2022-07-10";
        BigDecimal ans = operateSheetService.calculateCost(begin, end);
        BigDecimal expected = BigDecimal.valueOf(6340000.00);
        assert expected.compareTo(ans) == 0;
    }

    @Test
    public void findProfitTest(){
        String begin = "2022-01-01";
        String end = "2022-07-10";
        BigDecimal expected = BigDecimal.valueOf(-5268400.00);
        BigDecimal ans = operateSheetService.calculateProfit(begin, end);
        assert (expected.compareTo(ans) == 0);
    }

}
