package com.nju.edu.erp.model.vo.promotion.total_price;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author WFS
 * @date 2022/7/8 20:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TotalPricePromotionContentVO {
    /**
     * 自增id
     */
    private Integer id;

    /**
     * 商品id
     */
    private String pid;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 商品单价
     */
    private BigDecimal unitPrice;

    /**
     * 商品总价
     */
    private BigDecimal totalPrice;

    /**
     * 备注
     */
    private String remark;
}
