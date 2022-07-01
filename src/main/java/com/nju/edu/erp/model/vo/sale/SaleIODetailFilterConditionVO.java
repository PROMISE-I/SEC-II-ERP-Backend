package com.nju.edu.erp.model.vo.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleIODetailFilterConditionVO {
    /**
     * 开始日期字符串
     */
    private String beginDateStr;

    /**
     * 结束日期字符串
     */
    private String endDateStr;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 客户编号
     */
    private Integer customer;

    /**
     * 业务员名称
     */
    private String salesman;
}
