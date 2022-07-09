package com.nju.edu.erp.model.po.promotion.strategy.level;

import com.nju.edu.erp.model.vo.promotion.level.LevelPromotionStrategyVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LevelPromotionStrategyPO {
    public LevelPromotionStrategyPO(LevelPromotionStrategyVO levelPromotionStrategyVO){
        this.id = levelPromotionStrategyVO.getId();
        this.level = levelPromotionStrategyVO.getLevel();
        this.discount = levelPromotionStrategyVO.getDiscount();
        this.coupon = levelPromotionStrategyVO.getCoupon();
        this.beginDate = levelPromotionStrategyVO.getBeginDate();
        this.endDate = levelPromotionStrategyVO.getEndDate();
    }
    /**
     * id
     */
    Integer id;

    /**
     * 对应的级别
     */
    Integer level;

    /**
     * 折让比例
     */
    BigDecimal discount;

    /**
     * 代金券
     */
    BigDecimal coupon;

    /**
     * 开始日期
     */
    String beginDate;

    /**
     * 过期日期
     */
    String endDate;
}
