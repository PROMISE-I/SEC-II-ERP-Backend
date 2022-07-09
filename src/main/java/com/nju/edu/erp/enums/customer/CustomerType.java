package com.nju.edu.erp.enums.customer;

import com.nju.edu.erp.enums.BaseEnum;

public enum CustomerType implements BaseEnum<CustomerType, String> {

    SUPPLIER("供应商"),
    SELLER("销售商");
	
	private final String value;
	
	CustomerType(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
}
