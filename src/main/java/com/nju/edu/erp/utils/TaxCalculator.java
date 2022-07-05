package com.nju.edu.erp.utils;


import java.math.BigDecimal;

/**
 * 表驱动计算工资税款
 */
public class TaxCalculator {
    private static final double[] rawMoneyBound = {0, 36000, 144000, 300000, 420000, 660000, 960000, Double.MAX_VALUE};
    private static final double[] taxRate = {0.03, 0.10, 0.20, 0.25, 0.30, 0.35, 0.45};
    private static final BigDecimal lowestBound = BigDecimal.valueOf(5000);
    private static final int level = 7;

    public static BigDecimal calculateTax(BigDecimal rawAmount) {
        BigDecimal tax = BigDecimal.ZERO;

        if (rawAmount.compareTo(lowestBound) > 0) {
            rawAmount = rawAmount.subtract(lowestBound);
            for (int i = 0; i < level; i++) {
                BigDecimal boundInThisLevel = BigDecimal.valueOf(rawMoneyBound[i]);
                BigDecimal boundInNextLevel = BigDecimal.valueOf(rawMoneyBound[i + 1]);
                BigDecimal rateInThisLevel = BigDecimal.valueOf(taxRate[i]);

                if (rawAmount.compareTo(boundInNextLevel) <= 0) {
                    tax = tax.add(rawAmount.subtract(boundInThisLevel).multiply(rateInThisLevel));
                    break;
                } else {
                    tax = tax.add(boundInNextLevel.subtract(boundInThisLevel).multiply(rateInThisLevel));
                }
            }
        }

        return tax;
    }
}
