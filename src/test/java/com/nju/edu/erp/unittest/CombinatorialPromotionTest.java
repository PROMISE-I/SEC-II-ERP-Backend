package com.nju.edu.erp.unittest;

import com.nju.edu.erp.dao.promotion.strategy.CombinatorialPromotionDao;
import com.nju.edu.erp.model.po.promotion.strategy.combinatorial.CombinatorialDiscountPO;
import com.nju.edu.erp.model.vo.promotion.combinatorial.CombinatorialDiscountVO;
import com.nju.edu.erp.model.vo.warehouse.product.ProductInfoVO;
import com.nju.edu.erp.service.Impl.promotion.CombinatorialServiceImpl;
import com.nju.edu.erp.service.Impl.warehouse.product.ProductServiceImpl;
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
public class CombinatorialPromotionTest {

    @InjectMocks
    CombinatorialServiceImpl combinatorialService;

    @Mock
    ProductServiceImpl productService;

    @Mock
    CombinatorialPromotionDao combinatorialPromotionDao;

    @Test
    public void findAllTest(){
        CombinatorialDiscountPO combinatorialDiscountPO1 = CombinatorialDiscountPO.builder()
                .id(1)
                .productOneId("0000000000500000")
                .productTwoId("0000000000400001")
                .discountAmount(BigDecimal.valueOf(100.00))
                .beginDate("2022-07-01")
                .endDate("2022-08-01")
                .build();
        CombinatorialDiscountPO combinatorialDiscountPO2 = CombinatorialDiscountPO.builder()
                .id(2)
                .productOneId("0000000000500002")
                .productTwoId("0000000000400001")
                .discountAmount(BigDecimal.valueOf(200.00))
                .beginDate("2022-07-01")
                .endDate("2022-08-01")
                .build();
        List<CombinatorialDiscountPO> combinatorialDiscountPOList = new ArrayList<>();
        combinatorialDiscountPOList.add(combinatorialDiscountPO1);
        combinatorialDiscountPOList.add(combinatorialDiscountPO2);
        Mockito.when(combinatorialPromotionDao.findAll()).thenReturn(combinatorialDiscountPOList);
        ProductInfoVO productInfoVO1 = ProductInfoVO.builder()
                .id("0000000000400001")
                .name("小米手机")
                .build();
        ProductInfoVO productInfoVO2 = ProductInfoVO.builder()
                .id("0000000000500000")
                .name("intel电脑")
                .build();
        ProductInfoVO productInfoVO3 = ProductInfoVO.builder()
                .id("0000000000500002")
                .name("坚果")
                .build();
        Mockito.when(productService.getOneProductByPid("0000000000400001")).thenReturn(productInfoVO1);
        Mockito.when(productService.getOneProductByPid("0000000000500000")).thenReturn(productInfoVO2);
        Mockito.when(productService.getOneProductByPid("0000000000500002")).thenReturn(productInfoVO3);
        List<CombinatorialDiscountVO> expected = new ArrayList<>();
        CombinatorialDiscountVO combinatorialDiscountVO1 = CombinatorialDiscountVO.builder()
                .id(1)
                .productOneId("0000000000500000")
                .productTwoId("0000000000400001")
                .discountAmount(BigDecimal.valueOf(100.00))
                .beginDate("2022-07-01")
                .endDate("2022-08-01")
                .productOneName("intel电脑")
                .productTwoName("小米手机")
                .build();
        CombinatorialDiscountVO combinatorialDiscountVO2 = CombinatorialDiscountVO.builder()
                .id(2)
                .productOneId("0000000000500002")
                .productTwoId("0000000000400001")
                .discountAmount(BigDecimal.valueOf(200.00))
                .beginDate("2022-07-01")
                .endDate("2022-08-01")
                .productOneName("坚果")
                .productTwoName("小米手机")
                .build();
        expected.add(combinatorialDiscountVO1);
        expected.add(combinatorialDiscountVO2);
        List<CombinatorialDiscountVO> ans = combinatorialService.findAll();
        Assert.assertEquals(expected, ans);
    }

    @Test
    public void findByPairTest(){
        CombinatorialDiscountPO combinatorialDiscountPO1 = CombinatorialDiscountPO.builder()
                .id(1)
                .productOneId("0000000000500000")
                .productTwoId("0000000000400001")
                .discountAmount(BigDecimal.valueOf(100.00))
                .beginDate("2022-07-01")
                .endDate("2022-08-01")
                .build();
        CombinatorialDiscountVO expected = CombinatorialDiscountVO.builder()
                .id(1)
                .productOneId("0000000000500000")
                .productTwoId("0000000000400001")
                .discountAmount(BigDecimal.valueOf(100.00))
                .beginDate("2022-07-01")
                .endDate("2022-08-01")
                .productOneName("intel电脑")
                .productTwoName("小米手机")
                .build();
        ProductInfoVO productInfoVO1 = ProductInfoVO.builder()
                .id("0000000000400001")
                .name("小米手机")
                .build();
        ProductInfoVO productInfoVO2 = ProductInfoVO.builder()
                .id("0000000000500000")
                .name("intel电脑")
                .build();
        Mockito.when(productService.getOneProductByPid("0000000000400001")).thenReturn(productInfoVO1);
        Mockito.when(productService.getOneProductByPid("0000000000500000")).thenReturn(productInfoVO2);
        Mockito.when(combinatorialPromotionDao.findByPair("0000000000400001", "0000000000500000")).thenReturn(combinatorialDiscountPO1);
        CombinatorialDiscountVO ans = combinatorialService.findByPair("0000000000400001", "0000000000500000");
        assert ans.getId() == expected.getId();
        assert (ans.getProductOneId().equals(expected.getProductOneId()) && ans.getProductTwoId().equals(expected.getProductTwoId()))
                ||
                (ans.getProductOneId().equals(expected.getProductTwoId()) && ans.getProductTwoId().equals(expected.getProductOneId()));
        assert ans.getBeginDate().equals(expected.getBeginDate());
        assert ans.getEndDate().equals(expected.getEndDate());
        assert ans.getDiscountAmount().compareTo(expected.getDiscountAmount()) == 0;
    }

}
