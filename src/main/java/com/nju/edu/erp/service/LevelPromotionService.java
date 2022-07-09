package com.nju.edu.erp.service;

import com.nju.edu.erp.model.po.promotion.PresentInfoPO;
import com.nju.edu.erp.model.vo.promotion.LevelPromotionStrategyVO;

import java.util.List;

public interface LevelPromotionService {

    List<LevelPromotionStrategyVO> findAll();

    LevelPromotionStrategyVO findByLevel(Integer level);

    void updateOne(LevelPromotionStrategyVO levelPromotionStrategyVO);

    List<PresentInfoPO> findPresentInfoByLevel(Integer level);

}
