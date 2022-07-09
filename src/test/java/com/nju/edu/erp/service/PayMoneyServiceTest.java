package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.PayMoneySheetState;
import com.nju.edu.erp.model.vo.finance.PayMoneySheetVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayMoneyServiceTest {
    @Autowired
    PayMoneyService payMoneyService;

    @Test
    public void getByStateTest(){
        List<PayMoneySheetVO> ans = payMoneyService.getPayMoneySheetByState(PayMoneySheetState.PENDING_LEVEL_1);
        List<String> expected = new ArrayList<>();
        expected.add("FKD-20220528-00000");
        expected.add("FKD-20220528-00001");
        expected.add("FKD-20220528-00002");
        expected.add("FKD-20220528-00003");
        assert expected.size() == ans.size();
        for(String e: expected){
            boolean success = false;
            for(PayMoneySheetVO p: ans){
                if(!p.getId().equals(e))continue;
                success = true;
                break;
            }
            assert success;
        }
    }

    @Test
    public void getAllTest(){
        List<PayMoneySheetVO> ans = payMoneyService.getPayMoneySheetByState(null);
        List<String> expected = new ArrayList<>();
        expected.add("FKD-20220528-00000");
        expected.add("FKD-20220528-00001");
        expected.add("FKD-20220528-00002");
        expected.add("FKD-20220528-00003");
        assert expected.size() == ans.size();
        for(String e: expected){
            boolean success = false;
            for(PayMoneySheetVO p: ans){
                if(!p.getId().equals(e))continue;
                success = true;
                break;
            }
            assert success;
        }
    }

    @Test
    @Transactional
    public void makePayMoneySheetTest(){

    }

    @Test
    @Transactional
    public void approvalTest(){

    }

}
