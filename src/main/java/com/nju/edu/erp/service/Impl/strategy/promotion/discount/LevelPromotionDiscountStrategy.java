package com.nju.edu.erp.service.Impl.strategy.promotion.discount;

import java.math.BigDecimal;

/**
 * @author WFS
 * @date 2022/7/9 16:26
 */
//TODO 返回折扣
public class LevelPromotionDiscountStrategy implements DiscountStrategy{

    @Override
    public BigDecimal getDiscount() {
        return BigDecimal.ONE;
    }
}
