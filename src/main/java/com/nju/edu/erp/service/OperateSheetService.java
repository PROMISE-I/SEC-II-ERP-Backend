package com.nju.edu.erp.service;

import java.math.BigDecimal;

public interface OperateSheetService {
    public BigDecimal calculateFinalIncome(String begin, String end);

    public BigDecimal calculateDiscountedAmount(String begin, String end);

    public BigDecimal calculateCost(String begin, String end);

    public BigDecimal calculateProfit(String begin, String end);
}
