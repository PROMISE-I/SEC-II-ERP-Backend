package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.enums.sheetState.ReceiveMoneySheetState;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.finance.ReceiveMoneySheetVO;
import com.nju.edu.erp.service.ReceiveMoneyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiveMoneyServiceImpl implements ReceiveMoneyService {
    @Override
    public void makeReceiveMoneySheet(UserVO userVO, ReceiveMoneySheetVO receiveMoneySheetVO) {

    }

    @Override
    public List<ReceiveMoneySheetVO> getReceiveMoneySheetByState(ReceiveMoneySheetState state) {
        return null;
    }

    @Override
    public void approval(String receiveMoneySheetId, ReceiveMoneySheetState state) {

    }
}
