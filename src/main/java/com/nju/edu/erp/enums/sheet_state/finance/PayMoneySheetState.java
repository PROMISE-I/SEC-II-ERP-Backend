package com.nju.edu.erp.enums.sheet_state.finance;

import com.nju.edu.erp.enums.BaseEnum;

// 付款单状态
public enum PayMoneySheetState implements BaseEnum<PayMoneySheetState, String> {
    PENDING_LEVEL_1("待一级审批"), // 待销财务人员审批
    PENDING_LEVEL_2("待二级审批"), // 待总经理审批
    SUCCESS("审批完成"),
    FAILURE("审批失败");

    private final String value;

    PayMoneySheetState(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
