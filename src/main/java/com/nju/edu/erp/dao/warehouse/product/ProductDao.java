package com.nju.edu.erp.dao.warehouse.product;

import com.nju.edu.erp.model.po.warehouse.product.ProductPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductDao {

    int createProduct(ProductPO productPO);

    int updateById(ProductPO productPO);

    ProductPO findById(String id);

    List<ProductPO> findAll();

    int deleteById(String id);
}
