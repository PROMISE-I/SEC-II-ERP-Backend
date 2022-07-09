package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.SaleSheetPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessHistoryTest {
    @Autowired
    BusinessHistoryDao businessHistoryDao;

    @Test
    public void findSaleSheetByConditionTest1(){
        List<String> expectedIdList = new ArrayList<>();
        expectedIdList.add("XSD-20220523-00000");
        expectedIdList.add("XSD-20220524-00000");
        expectedIdList.add("XSD-20220524-00001");
        expectedIdList.add("XSD-20220524-00002");
        expectedIdList.add("XSD-20220524-00003");
        expectedIdList.add("XSD-20220524-00004");
        String begin = "2022-05-22";
        String end = "2022-05-26";
        String salesman = "xiaoshoujingli";
        Integer customer = 2;
        List<SaleSheetPO> ans = businessHistoryDao.findAllSaleSheetByInterval(begin, end, salesman, customer);
        for(String expectedId : expectedIdList){
            boolean success = false;
            for(SaleSheetPO saleSheetPO: ans){
                if(saleSheetPO.getId().equals(expectedId)){
                    success = true;
                    break;
                }
            }
            assert (success);
        }
    }

    @Test
    public void findSaleSheetByCondition2(){
        String begin = "2022-05-20";
        String end = "2022-05-22";
        String salesman = "xiaoshoujingli";
        Integer customer = 2;
        List<SaleSheetPO> ans = businessHistoryDao.findAllSaleSheetByInterval(begin, end, salesman, customer);
        assert (ans == null || ans.size() == 0);
    }


}
