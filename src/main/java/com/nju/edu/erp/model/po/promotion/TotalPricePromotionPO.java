package com.nju.edu.erp.model.po.promotion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author WFS
 * @date 2022/7/8 20:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TotalPricePromotionPO {
    /**
     * 总价促销策略id
     */
    private String id;

    /**
     * 触发此策略的总价阈值
     */
    private BigDecimal threshold;

    /**
     * 策略生效开始时间
     */
    private Date beginTime;

    /**
     * 策略生效截至时间
     */
    private Date endTime;

    /**
     * 操作者
     */
    private String operator;

    /**
     * 优惠卷金额
     */
    private BigDecimal voucherAmount;

    /**
     * 备注
     */
    private String remark;
}
