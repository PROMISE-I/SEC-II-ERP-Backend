package com.nju.edu.erp.model.vo.bankAccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountVO {

    /**
     * 公司银行编号
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 金额
     */
    private BigDecimal amount;
}
