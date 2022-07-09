package com.nju.edu.erp.model.vo.finance.receive_money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceiveMoneyTransferListVO {

    /**
     * 自增id, 新建单据时前端传null
     */
    private Integer id;

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
