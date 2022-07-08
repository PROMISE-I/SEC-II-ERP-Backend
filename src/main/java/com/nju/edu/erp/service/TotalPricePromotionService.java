package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.promotion.TotalPricePromotionVO;

import java.util.List;

/**
 * @author WFS
 * @date 2022/7/8 19:29
 */
public interface TotalPricePromotionService {
    void makePromotion(UserVO userVO, TotalPricePromotionVO totalPricePromotionVO);

    void updatePromotion(UserVO userVO, TotalPricePromotionVO totalPricePromotionVO);

    List<TotalPricePromotionVO> findAll();

    TotalPricePromotionVO findById(String id);

    void deleteById(String id);
}
