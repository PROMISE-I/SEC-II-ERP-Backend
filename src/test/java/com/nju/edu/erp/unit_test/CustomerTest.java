package com.nju.edu.erp.unit_test;

import com.nju.edu.erp.dao.sale_purchase.customer.CustomerDao;
import com.nju.edu.erp.enums.customer.CustomerType;
import com.nju.edu.erp.model.po.sale_purchase.customer.CustomerPO;
import com.nju.edu.erp.model.vo.sale_purchase.customer.CustomerVO;
import com.nju.edu.erp.service.Impl.sale_purchase.customer.CustomerServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WFS
 * @date 2022/7/10 14:15
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerTest {

    @InjectMocks
    CustomerServiceImpl customerService1;

    @Mock
    CustomerDao customerDao1;

    @Test
    public void getCustomersByTypeTest() {
        CustomerPO customerPO1 = CustomerPO.builder()
                .id(1)
                .type(String.valueOf(CustomerType.SELLER))
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
        CustomerPO customerPO2 = CustomerPO.builder()
                .id(2)
                .type(String.valueOf(CustomerType.SELLER))
                .level(1)
                .name("王五")
                .phone("12345678100")
                .address("南哪大学")
                .zipcode("345678")
                .email("234@nju.edu.cn")
                .lineOfCredit(BigDecimal.valueOf(100000000))
                .receivable(BigDecimal.ZERO)
                .payable(BigDecimal.ZERO)
                .operator("李四")
                .build();
        List<CustomerPO> customerPOS = new ArrayList<>();
        customerPOS.add(customerPO1);
        customerPOS.add(customerPO2);

        Mockito.when(customerDao1.findAllByType(CustomerType.SUPPLIER)).thenReturn(customerPOS);

        Assertions.assertEquals(customerPOS, customerService1.getCustomersByType(CustomerType.SUPPLIER));
    }

    @Test
    public void findCustomerByIdTest() {
        CustomerPO customerPO1 = CustomerPO.builder()
                .id(1)
                .type(String.valueOf(CustomerType.SELLER))
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

        Mockito.when(customerDao1.findOneById(1)).thenReturn(customerPO1);

        Assertions.assertEquals(customerPO1, customerService1.findCustomerById(1));
    }
}
