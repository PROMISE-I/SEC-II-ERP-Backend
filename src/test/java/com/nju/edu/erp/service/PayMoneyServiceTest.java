package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.PayMoneySheetState;
import com.nju.edu.erp.model.vo.finance.PayMoneySheetVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayMoneyServiceTest {
    @Autowired
    PayMoneyService payMoneyService;

    @Test
    public void getByStateTest(){
        List<PayMoneySheetVO> ans = payMoneyService.getPayMoneySheetByState(PayMoneySheetState.PENDING_LEVEL_1);

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
