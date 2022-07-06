package com.nju.edu.erp.model.vo.promotion;

import com.nju.edu.erp.model.po.promotion.LevelPromotionStrategyPO;
import com.nju.edu.erp.model.po.promotion.PresentInfoPO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LevelPromotionStrategyVO {
    public LevelPromotionStrategyVO(LevelPromotionStrategyPO levelPromotionStrategyPO){
        this.id = levelPromotionStrategyPO.getId();
        this.level = levelPromotionStrategyPO.getLevel();
        this.discount = levelPromotionStrategyPO.getDiscount();
        this.coupon = levelPromotionStrategyPO.getCoupon();
        this.beginDate = levelPromotionStrategyPO.getBeginDate();
        this.endDate = levelPromotionStrategyPO.getEndDate();
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
    /**
     * 赠品信息列表
     */
    List<PresentInfoPO> presentInfoList;
}
