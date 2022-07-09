package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.promotion.level.LevelPromotionStrategyVO;
import com.nju.edu.erp.service.Interface.promotion.LevelPromotionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LevelPromotionServiceTest {

    @Autowired
    LevelPromotionService levelPromotionService;

    @Test
    public void findByLevelTest(){
        Integer level = 1;
        LevelPromotionStrategyVO levelPromotionStrategyVO = levelPromotionService.findByLevel(level);
        assert (levelPromotionStrategyVO.getLevel() == 1);
        assert (levelPromotionStrategyVO.getDiscount().compareTo(BigDecimal.valueOf(0.00)) == 0);
        assert (levelPromotionStrategyVO.getBeginDate().equals("1970-01-01"));
        assert (levelPromotionStrategyVO.getEndDate().equals("1970-01-01"));
        assert (levelPromotionStrategyVO.getCoupon().compareTo(BigDecimal.valueOf(0.00)) == 0);
        assert (levelPromotionStrategyVO.getPresentInfoList() == null || levelPromotionStrategyVO.getPresentInfoList().isEmpty());
    }

    @Test
    @Transactional
    public void updateTest(){
        LevelPromotionStrategyVO levelPromotionStrategyVO = new LevelPromotionStrategyVO();
        levelPromotionStrategyVO.setId(2);
        levelPromotionStrategyVO.setDiscount(BigDecimal.valueOf(0.80));
        levelPromotionStrategyVO.setLevel(1);
        levelPromotionStrategyVO.setPresentInfoList(null);
        levelPromotionStrategyVO.setCoupon(BigDecimal.valueOf(100.00));
        levelPromotionStrategyVO.setBeginDate("2022-07-07");
        levelPromotionStrategyVO.setEndDate("2022-12-31");
        levelPromotionService.updateOne(levelPromotionStrategyVO);
        LevelPromotionStrategyVO ans = levelPromotionService.findByLevel(1);
        assert ans.getLevel() == levelPromotionStrategyVO.getLevel();
        assert ans.getCoupon().compareTo(levelPromotionStrategyVO.getCoupon()) == 0;
        assert ans.getDiscount().compareTo(levelPromotionStrategyVO.getDiscount()) == 0;
        assert ans.getEndDate().equals(levelPromotionStrategyVO.getEndDate());
        assert ans.getBeginDate().equals(levelPromotionStrategyVO.getBeginDate());

    }
}
