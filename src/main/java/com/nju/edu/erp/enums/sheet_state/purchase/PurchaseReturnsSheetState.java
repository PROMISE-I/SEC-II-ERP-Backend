package com.nju.edu.erp.enums.sheet_state.purchase;

import com.nju.edu.erp.enums.BaseEnum;

// 进货退货单状态
public enum PurchaseReturnsSheetState implements BaseEnum<PurchaseReturnsSheetState, String> {
	
	PENDING_LEVEL_1("待一级审批"), // 待销售经理审批
	PENDING_LEVEL_2("待二级审批"), // 待总经理审批
	SUCCESS("审批完成"),
	FAILURE("审批失败");
	
	private final String value;
	
	PurchaseReturnsSheetState(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
}