package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.promotion.LevelPromotionStrategyVO;

import java.util.List;

public interface LevelPromotionService {

    List<LevelPromotionStrategyVO> findAll();

    List<LevelPromotionStrategyVO> findByLevel(Integer level);

    void updateOne(LevelPromotionStrategyVO levelPromotionStrategyVO);

}
