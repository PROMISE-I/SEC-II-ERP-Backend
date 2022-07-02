package com.nju.edu.erp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessHistorySheetVO {
    /**
     * 单据类型:
     * 1. 销售类（销售出货单，销售退货单）
     * 2. 财务类（付款单，收款单，工资单）
     * 3. 进货类（进货单，进货退货单）
     * 4. 库存类（赠送单）
     */
    public String type;

    /**
     * 单据内容
     */
    Object sheetObject;
}
