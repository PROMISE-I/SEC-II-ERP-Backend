package com.nju.edu.erp.service.Impl.finance.sheets_counting;

import com.nju.edu.erp.dao.finance.sheets_counting.OperateSheetDao;
import com.nju.edu.erp.service.Interface.finance.sheets_counting.OperateSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

//TODO: handle exception
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
        if(finalSaleIncome == null)finalSaleIncome = BigDecimal.valueOf(0);
        BigDecimal saleReturnCost = operateSheetDao.findSaleReturnCost(begin, end);
        if(saleReturnCost == null)saleReturnCost = BigDecimal.valueOf(0);
        return finalSaleIncome.subtract(saleReturnCost);
    }

    @Override
    public BigDecimal calculateDiscountedAmount(String begin, String end) {
        BigDecimal discount = operateSheetDao.findDiscountedAmount(begin, end);

        return discount == null ? BigDecimal.valueOf(0) : discount;
    }

    @Override
    public BigDecimal calculateCost(String begin, String end) {
        BigDecimal humanResourceCost = operateSheetDao.findHumanResourceCost(begin, end);
        if(humanResourceCost == null)humanResourceCost = BigDecimal.valueOf(0);
        BigDecimal productPresentCost = operateSheetDao.findProductPresentCost(begin, end);
        if(productPresentCost == null) {
            productPresentCost = BigDecimal.valueOf(0);
        }
        BigDecimal saleCost = operateSheetDao.findPurchaseCost(begin, end);
        if(saleCost == null)saleCost = BigDecimal.valueOf(0);
        BigDecimal saleCostSecondTerm = operateSheetDao.findPurchaseReturnCost(begin, end);
        if(saleCostSecondTerm == null)saleCostSecondTerm = BigDecimal.valueOf(0);
        saleCost = saleCost.subtract(saleCostSecondTerm);
        return humanResourceCost.add(productPresentCost).add(saleCost);
    }

    @Override
    public BigDecimal calculateProfit(String begin, String end) {
        BigDecimal finalIncome = calculateFinalIncome(begin, end);
        BigDecimal cost = calculateCost(begin, end);

        return finalIncome.subtract(cost);
    }
}
