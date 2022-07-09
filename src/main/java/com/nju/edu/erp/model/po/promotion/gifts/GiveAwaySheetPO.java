package com.nju.edu.erp.model.po.promotion.gifts;

import com.nju.edu.erp.enums.sheet_state.gifts.GiveAwaySheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author WFS
 * @date 2022/7/8 8:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GiveAwaySheetPO {
    /**
     * 赠送单单据编号，格式：ZSD-yyyyMMdd-xxxxx
     */
    private String id;

    /**
     * 赠送单所属销售单的单据编号
     */
    private String saleSheetId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 单据状态
     */
    private GiveAwaySheetState state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 赠送商品总价
     */
    private BigDecimal totalAmount;
}
