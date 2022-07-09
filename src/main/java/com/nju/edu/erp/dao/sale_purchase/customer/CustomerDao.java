package com.nju.edu.erp.dao.sale_purchase.customer;

import com.nju.edu.erp.enums.customer.CustomerType;
import com.nju.edu.erp.model.po.sale_purchase.customer.CustomerPO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CustomerDao {
    int updateOne(CustomerPO customerPO);

    CustomerPO findOneById(Integer supplier);

    List<CustomerPO> findAllByType(CustomerType customerType);

    int addOne(CustomerPO customerPO);

    @Delete("delete from customer where id=#{id}")
    int deleteByIdInt(Integer id);
}
