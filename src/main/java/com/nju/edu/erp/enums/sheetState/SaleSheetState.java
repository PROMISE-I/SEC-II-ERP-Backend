package com.nju.edu.erp.enums.sheetState;

import com.nju.edu.erp.enums.BaseEnum;

// 销售单状态
public enum SaleSheetState implements BaseEnum<SaleSheetState, String> {
	
	PENDING_LEVEL_1("待一级审批"), // 待销售经理审批
	PENDING_LEVEL_2("待二级审批"), // 待总经理审批
	SUCCESS("审批完成"),
	FAILURE("审批失败");
	
	private final String value;
	
	SaleSheetState(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
}
