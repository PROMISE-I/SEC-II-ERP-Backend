package com.nju.edu.erp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperateSheetVO {
    /**
     * 开始日期
     */
    String beginDateStr;
    /**
     * 结束日期
     */
    String endDateStr;
    /**
     * 销售收入: 折让后总额
     */
    BigDecimal saleIncome;
    /**
     * 商品收入: 进货退货差价
     */
    BigDecimal productIncome;
    /**
     * 销售成本
     */
    BigDecimal saleCost;
    /**
     * 商品成本：报损（应该不考虑），赠出
     */
    BigDecimal productCost;
    /**
     * 利润
     */
    BigDecimal profit;
}
