package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.sheets_counting.BusinessHistoryQueryVO;
import com.nju.edu.erp.model.vo.sheets_counting.BusinessHistorySheetVO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetVO;
import com.nju.edu.erp.model.vo.purchaseReturns.PurchaseReturnsSheetVO;
import com.nju.edu.erp.model.vo.sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.sale.returns.SaleReturnsSheetVO;
import com.nju.edu.erp.service.Interface.sheets_counting.BusinessHistoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessHistoryServiceTest {

    @Autowired
    BusinessHistoryService businessHistoryService;

    @Test
    public void findAllSaleSheetTest(){
        BusinessHistoryQueryVO businessHistoryQueryVO = BusinessHistoryQueryVO.builder()
                .begin("2022-05-22")
                .end("2022-05-25")
                .salesman("xiaoshoujingli")
                .customer(2)
                .type("sale")
                .build();
        List<BusinessHistorySheetVO> ans = businessHistoryService.findAll(businessHistoryQueryVO);
        List<String> expected = new ArrayList<>();
        expected.add("XSD-20220524-00000");
        expected.add("XSD-20220524-00001");
        expected.add("XSD-20220524-00002");
        expected.add("XSD-20220524-00003");
        for(String e: expected){
            boolean success = false;
            for(BusinessHistorySheetVO b: ans){
                assert (b.getType().equals("sale"));
                assert (b.getSheetObject() instanceof SaleSheetVO);
                SaleSheetVO saleSheetVO = (SaleSheetVO) b.getSheetObject();
                if(!saleSheetVO.getId().equals(e))continue;
                assert(saleSheetVO.getSalesman().equals("xiaoshoujingli"));
                assert(saleSheetVO.getSupplier().equals(2));
                assert (saleSheetVO.getSaleSheetContent() != null);
                success = true;
                break;
            }
            assert(success);
        }
    }

    @Test
    public void findAllSaleReturnsSheetTest(){
        BusinessHistoryQueryVO businessHistoryQueryVO = BusinessHistoryQueryVO.builder()
                .begin("2022-05-20")
                .end("2022-07-10")
                .salesman("xiaoshoujingli")
                .customer(2)
                .type("sale-returns")
                .build();
        List<BusinessHistorySheetVO> ans = businessHistoryService.findAll(businessHistoryQueryVO);
        List<String> expected = new ArrayList<>();
        expected.add("XSTHD-20220709-00000");
        expected.add("XSTHD-20220709-00001");
        assert (expected.size() == ans.size());
        for (String e: expected){
            boolean success = false;
            for(BusinessHistorySheetVO b: ans){
                assert ("sale-returns".equals(b.getType()));
                assert(b.getSheetObject() instanceof SaleReturnsSheetVO);
                SaleReturnsSheetVO saleReturnsSheetVO = (SaleReturnsSheetVO) b.getSheetObject();
                if(!saleReturnsSheetVO.getId().equals(e))continue;
                assert (saleReturnsSheetVO.getSaleReturnsSheetContent() != null);
                success = true;
                break;
            }
            assert (success);
        }
    }

    @Test
    public void findPurchaseTest(){
        BusinessHistoryQueryVO businessHistoryQueryVO = BusinessHistoryQueryVO.builder()
                .begin("2022-05-20")
                .end("2022-05-24")
                .type("purchase")
                .build();
        List<BusinessHistorySheetVO> ans = businessHistoryService.findAll(businessHistoryQueryVO);
        List<String> expected = new ArrayList<>();
        expected.add("JHD-20220523-00000");
        expected.add("JHD-20220523-00001");
        expected.add("JHD-20220523-00002");
        expected.add("JHD-20220524-00002");
        assert (expected.size() == ans.size());
        for(String e: expected){
            boolean success = false;
            for(BusinessHistorySheetVO b: ans){
                assert ("purchase".equals(b.getType()));
                assert (b.getSheetObject() instanceof PurchaseSheetVO);
                PurchaseSheetVO purchaseSheetVO = (PurchaseSheetVO) b.getSheetObject();
                if(!purchaseSheetVO.getId().equals(e))continue;
                assert (purchaseSheetVO.getPurchaseSheetContent() != null);
                success = true;
                break;
            }
            assert success;
        }

    }

    @Test
    public void findPurchaseReturnsTest(){
        BusinessHistoryQueryVO businessHistoryQueryVO = BusinessHistoryQueryVO.builder()
                .begin("2022-05-24")
                .end("2022-05-26")
                .type("purchase-returns")
                .build();
        List<BusinessHistorySheetVO> ans = businessHistoryService.findAll(businessHistoryQueryVO);
        List<String> expected = new ArrayList<>();
        expected.add("JHTHD-20220524-00000");
        assert (expected.size() == ans.size());
        for(String e: expected){
            boolean success = true;
            for(BusinessHistorySheetVO b: ans){
                assert "purchase-returns".equals(b.getType());
                assert (b.getSheetObject() instanceof PurchaseReturnsSheetVO);
                PurchaseReturnsSheetVO purchaseReturnsSheetVO = (PurchaseReturnsSheetVO) b.getSheetObject();
                if(!purchaseReturnsSheetVO.getId().equals(e))continue;
                assert (purchaseReturnsSheetVO.getPurchaseReturnsSheetContent() != null);
                success = true;
                break;
            }
            assert success;
        }
    }


}
