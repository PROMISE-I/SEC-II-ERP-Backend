package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.promotion.TotalPricePromotionVO;

import java.util.List;

/**
 * @author WFS
 * @date 2022/7/8 19:29
 */
public interface TotalPricePromotionService {
    /**
     * 总经理制定一个总价的促销策略
     * @param userVO
     * @param totalPricePromotionVO
     */
    void makePromotion(UserVO userVO, TotalPricePromotionVO totalPricePromotionVO);

    /**
     * 获取所有总价的促销策略
     * @return
     */
    List<TotalPricePromotionVO> findAll();

    /**
     * 根据促销策略id设置总价的促销策略
     * @param id
     * @return
     */
    TotalPricePromotionVO findById(String id);

    /**
     * 根据促销策略id伤处一个总价的促销策略
     * @param id
     */
    void deleteById(String id);
}
