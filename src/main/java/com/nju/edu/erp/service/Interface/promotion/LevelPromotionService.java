package com.nju.edu.erp.service.Interface.promotion;

import com.nju.edu.erp.model.po.promotion.gifts.PresentInfoPO;
import com.nju.edu.erp.model.vo.promotion.level.LevelPromotionStrategyVO;

import java.util.List;

public interface LevelPromotionService {

    List<LevelPromotionStrategyVO> findAll();

    LevelPromotionStrategyVO findByLevel(Integer level);

    void updateOne(LevelPromotionStrategyVO levelPromotionStrategyVO);

    List<PresentInfoPO> findPresentInfoByLevel(Integer level);

}
