package com.nju.edu.erp.service.stub;

import com.nju.edu.erp.enums.customer.CustomerType;
import com.nju.edu.erp.model.po.sale_purchase.customer.CustomerPO;
import com.nju.edu.erp.service.Interface.sale_purchase.customer.CustomerService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceStub implements CustomerService {
    @Override
    public void updateCustomer(CustomerPO customerPO) {

    }

    @Override
    public List<CustomerPO> getCustomersByType(CustomerType type) {
        List<CustomerPO> customerPOS = new ArrayList<>();
        CustomerPO customerPO = CustomerPO.builder()
                .id(1)
                .type("供应商")
                .level(1)
                .name("张三")
                .phone("12345678999")
                .address("南哪大学")
                .zipcode("345678")
                .email("123@nju.edu.cn")
                .lineOfCredit(BigDecimal.valueOf(100000000))
                .receivable(BigDecimal.ZERO)
                .payable(BigDecimal.ZERO)
                .operator("李四")
                .build();
        customerPOS.add(customerPO);
        return customerPOS;
    }

    @Override
    public CustomerPO findCustomerById(Integer supplier) {
        CustomerPO customerPO = CustomerPO.builder()
                .id(1)
                .type("供应商")
                .level(1)
                .name("张三")
                .phone("12345678999")
                .address("南哪大学")
                .zipcode("345678")
                .email("123@nju.edu.cn")
                .lineOfCredit(BigDecimal.valueOf(100000000))
                .receivable(BigDecimal.ZERO)
                .payable(BigDecimal.ZERO)
                .operator("李四")
                .build();
        return customerPO;
    }

    @Override
    public void addCustomer(CustomerPO customerPO) {

    }

    @Override
    public void deleteOne(int id) {

    }
}
