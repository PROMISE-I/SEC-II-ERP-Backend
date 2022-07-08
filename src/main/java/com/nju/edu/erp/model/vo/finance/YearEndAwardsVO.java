package com.nju.edu.erp.model.vo.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author WFS
 * @date 2022/7/8 10:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YearEndAwardsVO {
    /**
     * 员工id
     */
    private Integer staffId;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 年终奖总额
     */
    private BigDecimal amount;
}
