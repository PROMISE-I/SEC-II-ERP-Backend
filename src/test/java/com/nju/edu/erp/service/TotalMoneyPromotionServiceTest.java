package com.nju.edu.erp.service;

import com.nju.edu.erp.dao.promotion.strategy.TotalPricePromotionDao;
import com.nju.edu.erp.enums.user.Role;
import com.nju.edu.erp.model.vo.promotion.total_price.TotalPricePromotionContentVO;
import com.nju.edu.erp.model.vo.promotion.total_price.TotalPricePromotionVO;
import com.nju.edu.erp.model.vo.user.UserVO;
import com.nju.edu.erp.service.Interface.promotion.TotalPricePromotionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TotalMoneyPromotionServiceTest {
    @Autowired
    TotalPricePromotionService totalPricePromotionService;

    @Autowired
    TotalPricePromotionDao totalPricePromotionDao;

    @Test
    public void findAllTest(){
        List<String> expected = new ArrayList<>();
        expected.add("ZJCXCL-20220709-00000");
        expected.add("ZJCXCL-20220709-00001");
        List<TotalPricePromotionVO> ans = totalPricePromotionService.findAll();
        assert expected.size() == ans.size();
        for(String e: expected){
            boolean success = false;
            for(TotalPricePromotionVO t: ans){
                if(t.getId().equals(e)){
                    success = true;
                    break;
                }
            }
            assert success;
        }
    }

    @Test
    @Transactional
    public void makeSheetTest(){
        UserVO userVO = UserVO.builder()
                .name("sky")
                .role(Role.ADMIN)
                .password("123456")
                .build();
        List<TotalPricePromotionContentVO> lst = new ArrayList<>();
        TotalPricePromotionContentVO t = TotalPricePromotionContentVO.builder()
                .pid("0000000000400000")
                .unitPrice(BigDecimal.valueOf(1000.00))
                .quantity(100)
                .totalPrice(BigDecimal.valueOf(100000.00))
                .build();
        lst.add(t);
        TotalPricePromotionVO totalPricePromotionVO = TotalPricePromotionVO.builder()
                .beginTimeStr("2022-07-01 00:00:00")
                .endTimeStr("2022-08-01 00:00:00")
                .threshold(BigDecimal.valueOf(8888.00))
                .operator("sky")
                .voucherAmount(BigDecimal.valueOf(500.00))
                .contentList(lst)
                .build();
        totalPricePromotionService.makePromotion(userVO, totalPricePromotionVO);
        String id = totalPricePromotionDao.getLatest().getId();
        TotalPricePromotionVO totalPricePromotionVO1 = totalPricePromotionService.findById(id);
        assert totalPricePromotionVO1.getVoucherAmount().compareTo(totalPricePromotionVO.getVoucherAmount()) == 0;
        assert totalPricePromotionVO.getContentList().size() == totalPricePromotionVO1.getContentList().size();

    }
}
