package com.nju.edu.erp.service;

import com.nju.edu.erp.dao.SaleSheetDao;
import com.nju.edu.erp.dao.promotion.GiveAwayDao;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.po.SaleSheetPO;
import com.nju.edu.erp.model.po.promotion.GiveAwaySheetContentPO;
import com.nju.edu.erp.model.po.promotion.GiveAwaySheetPO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.promotion.GiveAwaySheetVO;
import com.nju.edu.erp.model.vo.sale.SaleSheetContentVO;
import com.nju.edu.erp.model.vo.sale.SaleSheetVO;
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
public class SaleServiceWithPromotionTest {

    @Autowired
    SaleService saleService;

    @Autowired
    SaleSheetDao saleSheetDao;

    @Autowired
    GiveAwayService giveAwayService;

    @Autowired
    GiveAwayDao giveAwayDao;

    @Test
    @Transactional
    public void makeSheetWithLevelPromotionTest(){
        UserVO userVO = UserVO.builder()
                .name("sky")
                .role(Role.ADMIN)
                .password("123456")
                .build();
        List<SaleSheetContentVO> saleSheetContentVOList = new ArrayList<>();
        SaleSheetContentVO saleSheetContentVO1 = SaleSheetContentVO.builder()
                .pid("0000000000400000")
                .quantity(2)
                .unitPrice(BigDecimal.valueOf(1000.00))
                .totalPrice(BigDecimal.valueOf(2000.00))
                .remark("")
                .build();
        SaleSheetContentVO saleSheetContentVO2 = SaleSheetContentVO.builder()
                .pid("0000000000400001")
                .quantity(3)
                .unitPrice(BigDecimal.valueOf(3000.00))
                .totalPrice(BigDecimal.valueOf(9000.00))
                .remark("")
                .build();
        saleSheetContentVOList.add(saleSheetContentVO1);
        saleSheetContentVOList.add(saleSheetContentVO2);
        SaleSheetVO saleSheetVO = SaleSheetVO.builder()
                .operator("sky")
                .remark("level test")
                .saleSheetContent(saleSheetContentVOList)
                .supplier(2)
                .salesman("sky")
                .build();
        saleService.makeSaleSheet(userVO, saleSheetVO);
        SaleSheetPO saleSheetPO = saleSheetDao.getLatestSheet();
        String saleSheetId = saleSheetPO.getId();
        SaleSheetVO s = saleService.getSaleSheetById(saleSheetId);
        assert s.getDiscount().compareTo(BigDecimal.valueOf(0.80)) == 0;
        assert s.getVoucherAmount().compareTo(BigDecimal.valueOf(100.00)) == 0;
        GiveAwaySheetPO giveAwaySheetPO = giveAwayService.getSheetBySaleSheetId(saleSheetId);
        List<GiveAwaySheetContentPO> giveAwaySheetContentPOList = giveAwayDao.findContentById(giveAwaySheetPO.getId());
        List<String> expected = new ArrayList<>();
        expected.add("0000000000400000");
        expected.add("0000000000400001");
        assert expected.size() == giveAwaySheetContentPOList.size();
        for(String e : expected){
            boolean success = false;
            for(GiveAwaySheetContentPO giveAwaySheetContentPO: giveAwaySheetContentPOList){
                if(giveAwaySheetContentPO.getPid().equals(e)){
                    success = true;
                    break;
                }
            }
            assert success;
        }
    }

    @Test
    @Transactional
    public void makeSaleSheetTotalPromotionTest(){

    }

    @Test
    @Transactional
    public void makeSheetCombinatorialPromotionTest(){

    }
}
