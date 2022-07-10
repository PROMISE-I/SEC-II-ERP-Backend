package com.nju.edu.erp.unit_test;

import com.nju.edu.erp.dao.promotion.gifts.PresentInfoDao;
import com.nju.edu.erp.dao.promotion.strategy.LevelStrategyDao;
import com.nju.edu.erp.model.po.promotion.gifts.PresentInfoPO;
import com.nju.edu.erp.model.po.promotion.strategy.level.LevelPromotionStrategyPO;
import com.nju.edu.erp.model.vo.promotion.level.LevelPromotionStrategyVO;
import com.nju.edu.erp.service.Impl.promotion.LevelPromotionServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class LevelPromotionTest {
    @InjectMocks
    LevelPromotionServiceImpl levelPromotionService;

    @Mock
    LevelStrategyDao levelStrategyDao;

    @Mock
    PresentInfoDao presentInfoDao;

    @Test
    public void findAllTest(){
        LevelPromotionStrategyPO levelPromotionStrategyPO1 = LevelPromotionStrategyPO.builder()
                .id(1)
                .level(1)
                .beginDate("2022-07-01")
                .endDate("2022-08-01")
                .discount(BigDecimal.valueOf(0.80))
                .coupon(BigDecimal.valueOf(1000.00))
                .build();
        LevelPromotionStrategyPO levelPromotionStrategyPO2 = LevelPromotionStrategyPO.builder()
                .id(2)
                .level(2)
                .beginDate("2022-07-01")
                .endDate("2022-08-01")
                .discount(BigDecimal.valueOf(0.70))
                .coupon(BigDecimal.valueOf(2000.00))
                .build();
        PresentInfoPO presentInfoPO1 = PresentInfoPO.builder()
                .id(1)
                .level(1)
                .pid("0000000000400000")
                .quantity(100)
                .build();
        PresentInfoPO presentInfoPO2 = PresentInfoPO.builder()
                .id(2)
                .level(2)
                .pid("0000000000400001")
                .quantity(20)
                .build();
        List<PresentInfoPO> lst1 = new ArrayList<>();
        lst1.add(presentInfoPO1);
        List<PresentInfoPO> lst2 = new ArrayList<>();
        lst2.add(presentInfoPO2);
        LevelPromotionStrategyVO levelPromotionStrategyVO1 = LevelPromotionStrategyVO.builder()
                .id(1)
                .level(1)
                .discount(BigDecimal.valueOf(0.80))
                .coupon(BigDecimal.valueOf(1000.00))
                .beginDate("2022-07-01")
                .endDate("2022-08-01")
                .presentInfoList(lst1)
                .build();
        LevelPromotionStrategyVO levelPromotionStrategyVO2 = LevelPromotionStrategyVO.builder()
                .id(2)
                .level(2)
                .discount(BigDecimal.valueOf(0.70))
                .coupon(BigDecimal.valueOf(2000.00))
                .beginDate("2022-07-01")
                .endDate("2022-08-01")
                .presentInfoList(lst2)
                .build();
        List<PresentInfoPO> lst3 = new ArrayList<>();
        lst3.add(presentInfoPO1);
        lst3.add(presentInfoPO2);
        Mockito.when(presentInfoDao.findAll()).thenReturn(lst3);
        List<LevelPromotionStrategyPO> lst4 = new ArrayList<>();
        lst4.add(levelPromotionStrategyPO1);
        lst4.add(levelPromotionStrategyPO2);
        Mockito.when(levelStrategyDao.findAll()).thenReturn(lst4);
        List<LevelPromotionStrategyVO> ans = levelPromotionService.findAll();
        List<LevelPromotionStrategyVO> expected = new ArrayList<>();
        expected.add(levelPromotionStrategyVO1);
        expected.add(levelPromotionStrategyVO2);
        Assert.assertEquals(expected, ans);
    }
}
