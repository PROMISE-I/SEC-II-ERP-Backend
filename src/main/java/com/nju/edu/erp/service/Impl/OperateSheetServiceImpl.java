package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.OperateSheetDao;
import com.nju.edu.erp.service.OperateSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OperateSheetServiceImpl implements OperateSheetService {

    private final OperateSheetDao operateSheetDao;

    @Autowired
    public OperateSheetServiceImpl(OperateSheetDao operateSheetDao){
        this.operateSheetDao = operateSheetDao;
    }

    @Override
    public BigDecimal calculateFinalIncome(String begin, String end) {
        BigDecimal finalSaleIncome = operateSheetDao.findSaleIncome(begin, end);
        BigDecimal saleReturnCost = operateSheetDao.findSaleReturnCost(begin, end);

        return finalSaleIncome.subtract(saleReturnCost);
    }

    @Override
    public BigDecimal calculateDiscountedAmount(String begin, String end) {
        return operateSheetDao.findDiscountedAmount(begin, end);
    }

    @Override
    public BigDecimal calculateCost(String begin, String end) {
        BigDecimal humanResourceCost = operateSheetDao.findHumanResourceCost(begin, end);
        BigDecimal productPresentCost = operateSheetDao.findProductPresentCost(begin, end);
        BigDecimal saleCost = operateSheetDao.findPurchaseCost(begin, end).
                subtract(operateSheetDao.findPurchaseReturnCost(begin, end));
        return humanResourceCost.add(productPresentCost).add(saleCost);
    }

    @Override
    public BigDecimal calculateProfit(String begin, String end) {
        BigDecimal finalIncome = calculateFinalIncome(begin, end);
        BigDecimal cost = calculateCost(begin, end);

        return finalIncome.subtract(cost);
    }
}
