package com.nju.edu.erp.model.po.finance;

import com.nju.edu.erp.enums.sheetState.ReceiveMoneySheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayMoneySheetPO {
    /**
     * 付款单单据编号（格式为：FKD-yyyyMMdd-xxxxx）, 新建单据时前端传null
     */
    private String id;

    /**
     * 客户id(包括供应商和销售商)
     */
    private Integer customer;

    /**
     * 操作员
     */
    private String operator;

    /**
     * 转账总额
     */
    private BigDecimal totalAmount;

    /**
     * 单据状态，新建单据时前端传null
     */
    private ReceiveMoneySheetState state;

    /**
     * 创建时间
     */
    private Date createTime;
}
