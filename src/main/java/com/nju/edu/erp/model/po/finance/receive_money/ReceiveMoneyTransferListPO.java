package com.nju.edu.erp.model.po.finance.receive_money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceiveMoneyTransferListPO {

    /**
     * 自增id
     */
    private Integer id;

    /**
     * 收款单编号
     */
    private String receiveMoneySheetId;

    /**
     * 银行账户id
     */
    private Integer bankAccountId;

    /**
     * 转账金额
     */
    private BigDecimal amount;

    /**
     * 备注
     */
    private String remark;
}
