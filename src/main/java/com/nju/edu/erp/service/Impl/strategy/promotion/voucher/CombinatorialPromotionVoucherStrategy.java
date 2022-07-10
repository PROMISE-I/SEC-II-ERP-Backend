package com.nju.edu.erp.service.Impl.strategy.promotion.voucher;

import java.math.BigDecimal;

/**
 * @author WFS
 * @date 2022/7/9 16:19
 */
//获得代金卷
public class CombinatorialPromotionVoucherStrategy implements VoucherStrategy{
    private BigDecimal voucher;

    public CombinatorialPromotionVoucherStrategy(BigDecimal voucher){
        this.voucher = voucher;
    }
    @Override
    public BigDecimal getVoucherAmount() {
        return voucher;
    }
}
