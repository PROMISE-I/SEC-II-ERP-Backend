package com.nju.edu.erp.unit_test.utils;

import com.nju.edu.erp.utils.TaxCalculator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaxCalculatorTest {
    @Test
    public void taxTest1() {
        BigDecimal rawTotalAmount = BigDecimal.valueOf(10000);
        BigDecimal tax = TaxCalculator.calculateTax(rawTotalAmount);
        Assertions.assertEquals(0, tax.compareTo(BigDecimal.valueOf(150.00)));
    }

    @Test
    public void taxTest2() {
        BigDecimal rawTotalAmount = BigDecimal.valueOf(50000);
        BigDecimal tax = TaxCalculator.calculateTax(rawTotalAmount);
        Assertions.assertEquals(0, tax.compareTo(BigDecimal.valueOf(1980.00)));
    }

    @Test
    public void taxTest3() {
        BigDecimal rawTotalAmount = BigDecimal.valueOf(100000);
        BigDecimal tax = TaxCalculator.calculateTax(rawTotalAmount);
        Assertions.assertEquals(0, tax.compareTo(BigDecimal.valueOf(6980.00)));
    }

    @Test
    public void taxTest4() {
        BigDecimal rawTotalAmount = BigDecimal.valueOf(2000);
        BigDecimal tax = TaxCalculator.calculateTax(rawTotalAmount);
        Assertions.assertEquals(0, tax.compareTo(BigDecimal.valueOf(0.00)));
    }

    @Test
    public void taxTest5() {
        BigDecimal rawTotalAmount = BigDecimal.valueOf(5000);
        BigDecimal tax = TaxCalculator.calculateTax(rawTotalAmount);
        Assertions.assertEquals(0, tax.compareTo(BigDecimal.valueOf(0.00)));
    }

    @Test
    public void taxTest6() {
        BigDecimal rawTotalAmount = BigDecimal.valueOf(1000000);
        BigDecimal tax = TaxCalculator.calculateTax(rawTotalAmount);
        Assertions.assertEquals(0, tax.compareTo(BigDecimal.valueOf(265830.00)));
    }

    @Test
    public void taxTest7() {
        BigDecimal rawTotalAmount = BigDecimal.valueOf(5001);
        BigDecimal tax = TaxCalculator.calculateTax(rawTotalAmount);
        Assertions.assertEquals(0, tax.compareTo(BigDecimal.valueOf(0.03)));
    }
}
