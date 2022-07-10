package com.nju.edu.erp.integrated_test.service;

import com.nju.edu.erp.dao.finance.ReceiveMoneyDao;
import com.nju.edu.erp.enums.sheet_state.finance.ReceiveMoneySheetState;
import com.nju.edu.erp.enums.user.Role;
import com.nju.edu.erp.model.vo.finance.receive_money.ReceiveMoneySheetVO;
import com.nju.edu.erp.model.vo.finance.receive_money.ReceiveMoneyTransferListVO;
import com.nju.edu.erp.model.vo.user.UserVO;
import com.nju.edu.erp.service.Interface.finance.ReceiveMoneyService;
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
public class ReceiveMoneyServiceTest {

    @Autowired
    ReceiveMoneyService receiveMoneyService;

    @Autowired
    ReceiveMoneyDao receiveMoneyDao;

    @Test
    public void findAllTest(){
        List<ReceiveMoneySheetVO> ans = receiveMoneyService.findAllSheet();
        List<String> expected = new ArrayList<>();
        expected.add("SKD-20220528-00000");
        expected.add("SKD-20220528-00001");
        expected.add("SKD-20220528-00002");
        expected.add("SKD-20220528-00003");

        assert expected.size() == ans.size();

        for(String e: expected){
            boolean success = false;
            for(ReceiveMoneySheetVO receiveMoneySheetVO: ans){
                if(receiveMoneySheetVO.getId().equals(e)){
                    success = true;
                    break;
                }
            }
            assert success;
        }
    }

    @Test
    public void isSheetExistsTest(){
        assert receiveMoneyService.isSheetExists("SKD-20220528-00000");
        assert (!receiveMoneyService.isSheetExists("SKD-20220528-00004"));
    }

    @Test
    public void findByStateTest1(){
        List<ReceiveMoneySheetVO> ans = receiveMoneyService.getReceiveMoneySheetByState(null);
        List<String> expected = new ArrayList<>();
        expected.add("SKD-20220528-00000");
        expected.add("SKD-20220528-00001");
        expected.add("SKD-20220528-00002");
        expected.add("SKD-20220528-00003");

        assert expected.size() == ans.size();

        for(String e: expected){
            boolean success = false;
            for(ReceiveMoneySheetVO receiveMoneySheetVO: ans){
                if(receiveMoneySheetVO.getId().equals(e)){
                    success = true;
                    break;
                }
            }
            assert success;
        }
    }

    @Test
    public void findByStateTest2(){
        List<ReceiveMoneySheetVO> ans = receiveMoneyService.getReceiveMoneySheetByState(ReceiveMoneySheetState.PENDING_LEVEL_1);
        List<String> expected = new ArrayList<>();
        expected.add("SKD-20220528-00002");
        expected.add("SKD-20220528-00003");

        assert expected.size() == ans.size();

        for(String e: expected){
            boolean success = false;
            for(ReceiveMoneySheetVO receiveMoneySheetVO: ans){
                if(receiveMoneySheetVO.getId().equals(e)){
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
        Integer originalSize = receiveMoneyService.findAllSheet().size();
        UserVO userVO = UserVO.builder()
                .name("sky")
                .role(Role.ADMIN)
                .password("123456")
                .build();
        ReceiveMoneyTransferListVO receiveMoneyTransferListVO = ReceiveMoneyTransferListVO.builder()
                .amount(BigDecimal.valueOf(100000.0))
                .bankAccountId(1)
                .remark("")
                .build();
        List<ReceiveMoneyTransferListVO> lst = new ArrayList<>();
        lst.add(receiveMoneyTransferListVO);
        ReceiveMoneySheetVO receiveMoneySheetVO = ReceiveMoneySheetVO.builder()
                .customer(2)
                .operator("sky")
                .totalAmount(BigDecimal.valueOf(100000.00))
                .transferList(lst)
                .build();
        receiveMoneyService.makeReceiveMoneySheet(userVO, receiveMoneySheetVO);
        Integer currentSize = receiveMoneyService.findAllSheet().size();
        assert (currentSize - originalSize) == 1;
    }

    @Test
    @Transactional
    public void approvalTest(){
        Integer originalSize = receiveMoneyService.getReceiveMoneySheetByState(ReceiveMoneySheetState.PENDING_LEVEL_2).size();
        UserVO userVO = UserVO.builder()
                .name("sky")
                .role(Role.ADMIN)
                .password("123456")
                .build();
        ReceiveMoneyTransferListVO receiveMoneyTransferListVO = ReceiveMoneyTransferListVO.builder()
                .amount(BigDecimal.valueOf(100000.0))
                .bankAccountId(1)
                .remark("")
                .build();
        List<ReceiveMoneyTransferListVO> lst = new ArrayList<>();
        lst.add(receiveMoneyTransferListVO);
        ReceiveMoneySheetVO receiveMoneySheetVO = ReceiveMoneySheetVO.builder()
                .customer(2)
                .operator("sky")
                .totalAmount(BigDecimal.valueOf(100000.00))
                .transferList(lst)
                .build();
        receiveMoneyService.makeReceiveMoneySheet(userVO, receiveMoneySheetVO);
        String sheetId = receiveMoneyDao.getLatest().getId();
        receiveMoneyService.approval(sheetId, ReceiveMoneySheetState.PENDING_LEVEL_2);
        Integer currentSize = receiveMoneyService.getReceiveMoneySheetByState(ReceiveMoneySheetState.PENDING_LEVEL_2).size();
        assert (currentSize - originalSize) == 1;
    }
}
