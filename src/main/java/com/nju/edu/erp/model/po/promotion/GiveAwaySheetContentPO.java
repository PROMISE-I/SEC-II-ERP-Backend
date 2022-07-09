package com.nju.edu.erp.model.po.promotion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author WFS
 * @date 2022/7/8 8:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GiveAwaySheetContentPO {
    /**
     * 自增id
     */
    private Integer id;

    /**
     * 赠送单id
     */
    private String giveAwaySheetId;

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
