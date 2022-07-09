package com.nju.edu.erp.model.vo.finance.sheets_counting;

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
     * 折让后总额
     */
    BigDecimal finalIncome;
    /**
     * 折让金额
     */
    BigDecimal discountedAmount;
    /**
     * 总支出
     */
    BigDecimal cost;
    /**
     * 利润
     */
    BigDecimal profit;
}
