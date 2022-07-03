package com.nju.edu.erp.model.po.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalarySheetPO {

    /**
     * 工资单编号，格式："GZD-yyyyMM"
     */
    private Integer id;

}
