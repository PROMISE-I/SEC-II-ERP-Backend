package com.nju.edu.erp.enums.sheet_state.gifts;

import com.nju.edu.erp.enums.BaseEnum;

/**
 * @author WFS
 * @date 2022/7/9 10:47
 */
public enum GiveAwaySheetState implements BaseEnum<GiveAwaySheetState, String> {
    PENDING_SALE_SHEET_APPROVAL_SUCCESS("等待销售单通过审批"), //等待销售单通过审批
    PENDING_LEVEL_1("待一级审批"), // 待销售经理审批
    PENDING_LEVEL_2("待二级审批"), // 待总经理审批
    SUCCESS("审批完成"),
    FAILURE("审批失败");

    private final String value;

    GiveAwaySheetState(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
