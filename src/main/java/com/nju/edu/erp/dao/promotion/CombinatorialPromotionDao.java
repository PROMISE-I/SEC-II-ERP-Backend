package com.nju.edu.erp.dao.promotion;

import com.nju.edu.erp.model.po.promotion.CombinatorialDiscountPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
@Repository
public interface CombinatorialPromotionDao {
    List<CombinatorialDiscountPO> findAll();

    CombinatorialDiscountPO findByPair(@Param("productOne") String productOneId, @Param("productTwo") String productTwoId);

    int createOne(CombinatorialDiscountPO combinatorialDiscountPO);

    int updateOne(CombinatorialDiscountPO combinatorialDiscountPO);

    int deleteOne(@Param("id") Integer id);
}
