package com.nju.edu.erp.service.Impl.customer;

import com.nju.edu.erp.dao.customer.CustomerDao;
import com.nju.edu.erp.enums.customer.CustomerType;
import com.nju.edu.erp.model.po.customer.CustomerPO;
import com.nju.edu.erp.service.Interface.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * 根据id更新客户信息
     *
     * @param customerPO 客户信息
     */
    @Override
    public void updateCustomer(CustomerPO customerPO) {
        customerDao.updateOne(customerPO);
    }

    /**
     * 根据type查找对应类型的客户
     *
     * @param type 客户类型
     * @return 客户列表
     */
    @Override
    public List<CustomerPO> getCustomersByType(CustomerType type) {

        return customerDao.findAllByType(type);
    }

    @Override
    public CustomerPO findCustomerById(Integer supplier) {
        return customerDao.findOneById(supplier);
    }

    @Override
    public void addCustomer(CustomerPO customerPO) {
        customerDao.addOne(customerPO);
    }

    @Override
    public void deleteOne(int id) {
        customerDao.deleteByIdInt(id);
    }

}
