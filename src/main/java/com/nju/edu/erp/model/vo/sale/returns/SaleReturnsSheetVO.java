package com.nju.edu.erp.model.vo.sale.returns;

import com.nju.edu.erp.enums.sheet_state.sale.SaleReturnsSheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleReturnsSheetVO {
    /**
     * 销售退货单单据编号（格式：XSTHD-yyyyMMdd-xxxxx）,新建单据前端传null
     */
    private String id;
    /**
     * 关联的销售单id
     */
    private String saleSheetId;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 备注
     */
    private String remark;
    /**
     * 折让前退货总额, 新建单据时前端传null
     */
    private BigDecimal rawTotalAmount;
    /**
     * 折扣
     */
    private BigDecimal discount;
    /**
     * 使用代金卷总额（按照退货比例比例退回）
     */
    private BigDecimal voucherAmount;
    /**
     * 折让后退款的总额
     */
    private BigDecimal finalAmount;
    /**
     * 单据状态
     */
    private SaleReturnsSheetState state;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 销售退货单具体内容
     */
    private List<SaleReturnsSheetContentVO> saleReturnsSheetContent;
}
