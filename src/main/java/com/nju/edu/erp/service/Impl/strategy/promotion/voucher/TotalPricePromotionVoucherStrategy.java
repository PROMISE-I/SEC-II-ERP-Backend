package com.nju.edu.erp.service.Impl.strategy.promotion.voucher;

import com.nju.edu.erp.model.po.SaleSheetPO;
import com.nju.edu.erp.service.TotalPricePromotionService;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author WFS
 * @date 2022/7/9 15:44
 */
public class TotalPricePromotionVoucherStrategy implements VoucherStrategy {

    private final TotalPricePromotionService totalPricePromotionService;

    private final SaleSheetPO saleSheetPO;

    public TotalPricePromotionVoucherStrategy(TotalPricePromotionService totalPricePromotionService, SaleSheetPO saleSheetPO) {
        this.totalPricePromotionService = totalPricePromotionService;
        this.saleSheetPO = saleSheetPO;
    }

    @Override
    public BigDecimal getVoucherAmount() {
        BigDecimal rawTotalAmount = saleSheetPO.getRawTotalAmount();
        Date today = new Date();

        BigDecimal voucherAmount = totalPricePromotionService.getVoucherAmountByDateAndThreshold(today, rawTotalAmount);

        return voucherAmount == null ? BigDecimal.ZERO : voucherAmount;
    }
}
