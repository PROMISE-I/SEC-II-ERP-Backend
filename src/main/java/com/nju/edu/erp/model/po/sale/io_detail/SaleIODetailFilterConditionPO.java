package com.nju.edu.erp.model.po.sale.io_detail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleIODetailFilterConditionPO {
    /**
     * 开始日期字
     */
    private Date beginDate;

    /**
     * 结束日期字
     */
    private Date endDate;

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
