package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.promotion.TotalPricePromotionVO;
import com.nju.edu.erp.service.TotalPricePromotionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WFS
 * @date 2022/7/8 19:31
 */
@Service
public class TotalPricePromotionServiceImpl implements TotalPricePromotionService {
    @Override
    public void makePromotion(UserVO userVO, TotalPricePromotionVO totalPricePromotionVO) {

    }

    @Override
    public void updatePromotion(UserVO userVO, TotalPricePromotionVO totalPricePromotionVO) {

    }

    @Override
    public List<TotalPricePromotionVO> findAll() {
        return null;
    }

    @Override
    public TotalPricePromotionVO findById(String id) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}
