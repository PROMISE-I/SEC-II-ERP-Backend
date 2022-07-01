package com.nju.edu.erp.model.vo.finance;

import com.nju.edu.erp.enums.sheetState.ReceiveMoneySheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceiveMoneySheetVO {

    /**
     * 收款单单据编号（格式为：SKD-yyyyMMdd-xxxxx）, 新建单据时前端传null
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
     * 转账列表
     */
    private List<TransferListVO> transferList;
}
