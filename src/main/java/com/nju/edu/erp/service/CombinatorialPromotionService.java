package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.promotion.CombinatorialDiscountVO;

import java.util.List;

public interface CombinatorialPromotionService {
    List<CombinatorialDiscountVO> findAll();

    CombinatorialDiscountVO findByPair(String productOneId, String productTwoId);

    int createOne(CombinatorialDiscountVO combinatorialDiscountVO);

    int updateOne(CombinatorialDiscountVO combinatorialDiscountVO);

    int deleteOne(Integer id);
}
