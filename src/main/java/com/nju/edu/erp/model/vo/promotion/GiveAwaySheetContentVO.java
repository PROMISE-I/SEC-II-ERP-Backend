package com.nju.edu.erp.model.vo.promotion;

import java.math.BigDecimal;

/**
 * @author WFS
 * @date 2022/7/9 10:53
 */
public class GiveAwaySheetContentVO {
    /**
     * 自增id
     */
    private Integer id;

    /**
     * 商品id
     */
    private String pid;

    /**
     * 赠品数量
     */
    private Integer quantity;

    /**
     * 赠品单价
     */
    private BigDecimal unitPrice;

    /**
     * 赠品总价
     */
    private BigDecimal totalPrice;

    /**
     * 备注
     */
    private String remark;
}
