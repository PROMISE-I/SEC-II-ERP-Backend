package com.nju.edu.erp.service;

import com.nju.edu.erp.dao.promotion.gifts.GiveAwayDao;
import com.nju.edu.erp.dao.sale_purchase.sale.SaleSheetDao;
import com.nju.edu.erp.enums.user.Role;
import com.nju.edu.erp.model.po.promotion.gifts.GiveAwaySheetContentPO;
import com.nju.edu.erp.model.po.promotion.gifts.GiveAwaySheetPO;
import com.nju.edu.erp.model.po.sale_purchase.sale.SaleSheetPO;
import com.nju.edu.erp.model.vo.sale_purchase.sale.SaleSheetContentVO;
import com.nju.edu.erp.model.vo.sale_purchase.sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.user.UserVO;
import com.nju.edu.erp.service.Interface.promotion.gifts.GiveAwayService;
import com.nju.edu.erp.service.Interface.sale_purchase.sale.SaleService;
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
    public void makeSaleSheetCombinatorialTest(){
        UserVO userVO = UserVO.builder()
                .name("sky")
                .role(Role.ADMIN)
                .password("123456")
                .build();
        List<SaleSheetContentVO> saleSheetContentVOList = new ArrayList<>();
        SaleSheetContentVO saleSheetContentVO1 = SaleSheetContentVO.builder()
                .pid("0000000000400001")
                .quantity(2)
                .unitPrice(BigDecimal.valueOf(1000.00))
                .totalPrice(BigDecimal.valueOf(2000.00))
                .remark("")
                .build();
        SaleSheetContentVO saleSheetContentVO2 = SaleSheetContentVO.builder()
                .pid("0000000000500000")
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
        assert saleSheetPO.getVoucherAmount().compareTo(BigDecimal.valueOf(1100.00)) == 0;
    }

    @Test
    @Transactional
    public void makeSheetTotalPromotionTest(){
        UserVO userVO = UserVO.builder()
                .name("sky")
                .role(Role.ADMIN)
                .password("123456")
                .build();
        List<SaleSheetContentVO> saleSheetContentVOList = new ArrayList<>();
        SaleSheetContentVO saleSheetContentVO1 = SaleSheetContentVO.builder()
                .pid("0000000000500002")
                .quantity(200)
                .unitPrice(BigDecimal.valueOf(1000.00))
                .totalPrice(BigDecimal.valueOf(200000.00))
                .remark("")
                .build();
        SaleSheetContentVO saleSheetContentVO2 = SaleSheetContentVO.builder()
                .pid("0000000000500000")
                .quantity(300)
                .unitPrice(BigDecimal.valueOf(3000.00))
                .totalPrice(BigDecimal.valueOf(900000.00))
                .remark("")
                .build();
        saleSheetContentVOList.add(saleSheetContentVO1);
        saleSheetContentVOList.add(saleSheetContentVO2);
        SaleSheetVO saleSheetVO = SaleSheetVO.builder()
                .operator("sky")
                .remark("level test")
                .saleSheetContent(saleSheetContentVOList)
                .supplier(1)
                .salesman("sky")
                .build();
        saleService.makeSaleSheet(userVO, saleSheetVO);
        SaleSheetPO saleSheetPO = saleSheetDao.getLatestSheet();
        assert saleSheetPO.getVoucherAmount().compareTo(BigDecimal.valueOf(10000.00)) == 0;
        GiveAwaySheetPO giveAwaySheetPO = giveAwayService.getSheetBySaleSheetId(saleSheetPO.getId());
        List<GiveAwaySheetContentPO> lst = giveAwayDao.findContentById(giveAwaySheetPO.getId());
        List<String> expected = new ArrayList<>();
        expected.add("0000000000400000");
        expected.add("0000000000400001");
        assert expected.size() == lst.size();
        for(String e: expected){
            boolean success = false;
            for(GiveAwaySheetContentPO giveAwaySheetContentPO: lst){
                if(giveAwaySheetContentPO.getPid().equals(e)){
                    success = true;
                    break;
                }
            }
            assert success;
        }
    }
}
