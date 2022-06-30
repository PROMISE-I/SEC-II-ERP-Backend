package com.nju.edu.erp.service;

import com.nju.edu.erp.dao.SaleReturnsSheetDao;
import com.nju.edu.erp.dao.SaleSheetDao;
import com.nju.edu.erp.dao.WarehouseDao;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
import com.nju.edu.erp.enums.sheetState.SaleSheetState;
import com.nju.edu.erp.model.po.SaleReturnsSheetPO;
import com.nju.edu.erp.model.po.SaleSheetPO;
import com.nju.edu.erp.model.po.WarehousePO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.sale.SaleSheetContentVO;
import com.nju.edu.erp.model.vo.sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetContentVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetVO;
import com.nju.edu.erp.utils.IdGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
@Rollback
@Transactional
public class SaleReturnsServiceTest {

    @Autowired
    SaleSheetDao saleSheetDao;

    @Autowired
    SaleService saleService;

    @Autowired
    SaleReturnsService saleReturnsService;

    @Autowired
    SaleReturnsSheetDao saleReturnsSheetDao;

    @Autowired
    WarehouseDao warehouseDao;

    @Test
    public void makeSaleReturnsSheet() {
        UserVO userVO = UserVO.builder()
                .name("wfs")
                .role(Role.ADMIN)
                .build();

        //创建一个销售订单
        List<SaleSheetContentVO> saleSheetContentVOS = new ArrayList<>();
        saleSheetContentVOS.add(SaleSheetContentVO.builder()
                .pid("0000000000400000")
                .quantity(50)
                .remark("Test1-product1")
                .unitPrice(BigDecimal.valueOf(3200))
                .build());
        saleSheetContentVOS.add(SaleSheetContentVO.builder()
                .pid("0000000000400001")
                .quantity(60)
                .remark("Test1-product2")
                .unitPrice(BigDecimal.valueOf(4200))
                .build());
        SaleSheetVO saleSheetVO = SaleSheetVO.builder()
                .saleSheetContent(saleSheetContentVOS)
                .supplier(2)
                .discount(BigDecimal.valueOf(0.8))
                .voucherAmount(BigDecimal.valueOf(300))
                .remark("Test1")
                .build();
        saleService.makeSaleSheet(userVO, saleSheetVO);
        SaleSheetPO saleSheetPO = saleSheetDao.getLatestSheet();
        String saleSheetId = saleSheetPO.getId();
        //审批
        saleService.approval(saleSheetId, SaleSheetState.PENDING_LEVEL_2);
        saleService.approval(saleSheetId, SaleSheetState.SUCCESS);

        List<SaleReturnsSheetContentVO> saleReturnsSheetContentVOS = new ArrayList<>();
        saleReturnsSheetContentVOS.add(SaleReturnsSheetContentVO.builder()
                .pid("0000000000400000")
                .quantity(25)
                .remark("Test1-product1-return")
                .unitPrice(BigDecimal.valueOf(3200))
                .build());
        saleReturnsSheetContentVOS.add(SaleReturnsSheetContentVO.builder()
                .pid("0000000000400001")
                .quantity(30)
                .remark("Test1-product2-return")
                .unitPrice(BigDecimal.valueOf(4200))
                .build());
        SaleReturnsSheetVO saleReturnsSheetVO = SaleReturnsSheetVO.builder()
                .saleSheetId(saleSheetPO.getId())
                .remark("Test1-return")
                .saleReturnsSheetContent(saleReturnsSheetContentVOS)
                .build();
        SaleReturnsSheetPO prevSheet = saleReturnsSheetDao.getLatest();
        String realSheetId = IdGenerator.generateSheetId(prevSheet == null? null : prevSheet.getId(), "XSTHD");

        saleReturnsService.makeSaleReturnsSheet(userVO, saleReturnsSheetVO);
        SaleReturnsSheetPO latestSheet = saleReturnsSheetDao.getLatest();
        Assertions.assertNotNull(latestSheet);
        Assertions.assertEquals(realSheetId, latestSheet.getId());
        Assertions.assertEquals(latestSheet.getSaleSheetId(), saleSheetPO.getId());
        Assertions.assertEquals(0, latestSheet.getRawTotalAmount().compareTo(BigDecimal.valueOf(206000.00)));
        Assertions.assertEquals(0, latestSheet.getDiscount().compareTo(BigDecimal.valueOf(0.8)));
        Assertions.assertEquals(0, latestSheet.getVoucherAmount().compareTo(BigDecimal.valueOf(150)));
        Assertions.assertEquals(0, latestSheet.getFinalAmount().compareTo(BigDecimal.valueOf(164650.00)));
    }

    @Test
    public void getSaleSheetByState() {
        UserVO userVO = UserVO.builder()
                .name("wfs")
                .role(Role.ADMIN)
                .build();

        //创建一个销售订单
        List<SaleSheetContentVO> saleSheetContentVOS = new ArrayList<>();
        saleSheetContentVOS.add(SaleSheetContentVO.builder()
                .pid("0000000000500000")
                .quantity(50)
                .remark("Test1-product1")
                .unitPrice(BigDecimal.valueOf(3200))
                .build());
        saleSheetContentVOS.add(SaleSheetContentVO.builder()
                .pid("0000000000500001")
                .quantity(60)
                .remark("Test1-product2")
                .unitPrice(BigDecimal.valueOf(4200))
                .build());
        SaleSheetVO saleSheetVO = SaleSheetVO.builder()
                .saleSheetContent(saleSheetContentVOS)
                .supplier(2)
                .discount(BigDecimal.valueOf(0.8))
                .voucherAmount(BigDecimal.valueOf(300))
                .remark("Test1")
                .build();
        saleService.makeSaleSheet(userVO, saleSheetVO);
        SaleSheetPO saleSheetPO = saleSheetDao.getLatestSheet();
        String saleSheetId = saleSheetPO.getId();
        //审批
        saleService.approval(saleSheetId, SaleSheetState.PENDING_LEVEL_2);
        saleService.approval(saleSheetId, SaleSheetState.SUCCESS);


        List<WarehousePO> affectOfWarehouse1 = warehouseDao.findAll();

        List<SaleReturnsSheetContentVO> saleReturnsSheetContentVOS = new ArrayList<>();
        saleReturnsSheetContentVOS.add(SaleReturnsSheetContentVO.builder()
                .pid("0000000000500000")
                .quantity(25)
                .remark("Test1-product1-return")
                .unitPrice(BigDecimal.valueOf(3200))
                .build());
        saleReturnsSheetContentVOS.add(SaleReturnsSheetContentVO.builder()
                .pid("0000000000500001")
                .quantity(30)
                .remark("Test1-product2-return")
                .unitPrice(BigDecimal.valueOf(4200))
                .build());
        SaleReturnsSheetVO saleReturnsSheetVO = SaleReturnsSheetVO.builder()
                .saleSheetId(saleSheetPO.getId())
                .remark("Test1-return")
                .saleReturnsSheetContent(saleReturnsSheetContentVOS)
                .build();
        SaleReturnsSheetPO prevSheet = saleReturnsSheetDao.getLatest();
        String realSheetId = IdGenerator.generateSheetId(prevSheet == null? null : prevSheet.getId(), "XSTHD");
        saleReturnsService.makeSaleReturnsSheet(userVO, saleReturnsSheetVO);

        List<SaleReturnsSheetVO> saleReturnsSheetByState = saleReturnsService.getSaleReturnsSheetByState(SaleReturnsSheetState.PENDING_LEVEL_1);
        Assertions.assertNotNull(saleReturnsSheetByState);
        Assertions.assertEquals(1, saleReturnsSheetByState.size());
        SaleReturnsSheetVO sheet1 = saleReturnsSheetByState.get(0);
        Assertions.assertNotNull(sheet1);
        Assertions.assertEquals(realSheetId, sheet1.getId());

        List<SaleReturnsSheetContentVO> saleReturnsSheetContent = sheet1.getSaleReturnsSheetContent();
        Assertions.assertNotNull(saleReturnsSheetContent);
        saleReturnsSheetContent.sort(Comparator.comparing(SaleReturnsSheetContentVO::getPid));
        Assertions.assertEquals(2, saleReturnsSheetContent.size());

        SaleReturnsSheetPO saleReturnsSheetPO = saleReturnsSheetDao.getLatest();
        saleReturnsService.approval(saleReturnsSheetPO.getId(), SaleReturnsSheetState.PENDING_LEVEL_2);
        saleReturnsService.approval(saleReturnsSheetPO.getId(), SaleReturnsSheetState.SUCCESS);
        List<SaleReturnsSheetVO> saleReturnsSheetSuccess = saleReturnsService.getSaleReturnsSheetByState(SaleReturnsSheetState.SUCCESS);

        List<WarehousePO> affectOfWarehouse2 = warehouseDao.findAll();
        System.out.println("pending");
    }

}