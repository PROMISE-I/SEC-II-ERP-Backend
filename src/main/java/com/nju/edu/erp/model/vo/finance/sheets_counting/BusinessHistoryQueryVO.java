package com.nju.edu.erp.model.vo.finance.sheets_counting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessHistoryQueryVO {
    /**
     * 开始时间
     */
    String begin;
    /**
     * 结束时间
     */
    String end;
    /**
     * 业务员
     */
    String salesman;
    /**
     * 顾客
     */
    Integer customer;
    /**
     * 单据类型
     */
    String type;
}
