package com.nju.edu.erp.enums.salary_strategy;

import com.nju.edu.erp.enums.BaseEnum;

/**
 * @author WFS
 * @date 2022/7/6 11:08
 */
public enum SalarySendType implements BaseEnum<SalarySendType, String> {

    MONTHLY("每月发放"),
    ANNUALLY("每年发放");


    private final String value;

    SalarySendType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
