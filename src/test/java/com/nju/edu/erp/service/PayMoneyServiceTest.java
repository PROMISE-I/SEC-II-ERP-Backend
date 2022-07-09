package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.PayMoneySheetState;
import com.nju.edu.erp.model.po.User;
import com.nju.edu.erp.model.vo.finance.PayMoneySheetVO;
import com.nju.edu.erp.model.vo.finance.PayMoneyTransferListVO;
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
        User user = User.builder()
                .role(Role.ADMIN)
                .id(4)
                .name("sky")
                .password("123456")
                .build();
        PayMoneyTransferListVO payMoneyTransferListVO = PayMoneyTransferListVO.builder()
                .id(1)
                .amount(BigDecimal.valueOf(2000000.00))
                .bankAccountId(3)
                .remark("对应进货单：JHD-20220523-00001")
                .build();
        PayMoneyTransferListVO payMoneyTransferListVO2 = PayMoneyTransferListVO.builder()
                .id(2)
                .amount(BigDecimal.valueOf(200000.00))
                .bankAccountId(2)
                .remark("对应进货单：JHD-20220523-00001")
                .build();

        PayMoneySheetVO payMoneySheetVO = PayMoneySheetVO.builder().build();
    }

    @Test
    @Transactional
    public void approvalTest(){

    }

}
