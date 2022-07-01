package com.nju.edu.erp.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleIODetailPO {
    /**
     * 产品销售（退货）时间，精确到天。
     * 格式："yyyy-MM-dd"
     */
    private Date createTime;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 销售（退货）产品数量
     */
    private Integer quantity;

    /**
     * 产品单价
     */
    private BigDecimal unitPrice;

    /**
     * 销售（退货）产品总额
     */
    private BigDecimal totalAmount;
}
