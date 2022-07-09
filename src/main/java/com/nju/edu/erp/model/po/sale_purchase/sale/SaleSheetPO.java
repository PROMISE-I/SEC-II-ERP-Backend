package com.nju.edu.erp.model.po.sale_purchase.sale;

import com.nju.edu.erp.enums.sheet_state.sale.SaleSheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleSheetPO {
    /**
     * 销售单单据编号（格式为：XSD-yyyyMMdd-xxxxx
     */
    private String id;
    /**
     * 客户/销售商id
     */
    private Integer supplier;
    /**
     * 业务员
     */
    private String salesman;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 备注
     */
    private String remark;
    /**
     * 折让前总额
     */
    private BigDecimal rawTotalAmount;
    /**
     * 折让
     */
    private BigDecimal discount;
    /**
     * 使用代金券总额
     */
    private BigDecimal voucherAmount;
    /**
     * 折让后总额
     */
    private BigDecimal finalAmount;
    /**
     * 单据状态
     */
    private SaleSheetState state;
    /**
     * 创建时间
     */
    private Date createTime;

    @Override
    public boolean equals(Object o){
        if(o instanceof SaleSheetPO) {
            return ((SaleSheetPO) o).getId().equals(id);
        }

        return false;
    }
}
