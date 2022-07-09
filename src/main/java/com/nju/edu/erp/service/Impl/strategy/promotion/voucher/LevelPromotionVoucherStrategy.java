package com.nju.edu.erp.service.Impl.strategy.promotion.voucher;

import java.math.BigDecimal;

/**
 * @author WFS
 * @date 2022/7/9 16:18
 */
//TODO 获得代金卷
public class LevelPromotionVoucherStrategy implements VoucherStrategy{
    private BigDecimal voucher;

    public LevelPromotionVoucherStrategy(BigDecimal voucher){
        this.voucher = voucher;
    }
    @Override
    public BigDecimal getVoucherAmount() {
        return voucher;
    }
}
