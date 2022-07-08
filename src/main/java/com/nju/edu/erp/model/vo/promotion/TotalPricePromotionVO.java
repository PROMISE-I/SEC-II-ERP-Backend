package com.nju.edu.erp.model.vo.promotion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author WFS
 * @date 2022/7/8 19:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TotalPricePromotionVO {
    /**
     * 总价促销策略id
     */
    private Integer id;

    /**
     * 触发此策略的总价阈值
     */
    private BigDecimal threshold;

    /**
     * 策略生效开始时间字符串
     */
    private String beginTimeStr;

    /**
     * 策略生效截至时间字符串
     */
    private String endTimeStr;

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

    /**
     * 赠品清单
     */
    private List<TotalPricePromotionContentVO> contentList;
}
