package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.ReceiveMoneySheetState;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.finance.ReceiveMoneySheetVO;

import java.util.List;

public interface ReceiveMoneyService {

    /**
     * 财务人员制定收款单
     * @param userVO 操作员
     * @param receiveMoneySheetVO 收款单
     */
    void makeReceiveMoneySheet(UserVO userVO, ReceiveMoneySheetVO receiveMoneySheetVO);

    /**
     * 根据状态查看收款单
     * @param state 收款单状态
     */
    List<ReceiveMoneySheetVO> getReceiveMoneySheetByState(ReceiveMoneySheetState state);

    /**
     * 审批单据
     * @param receiveMoneySheetId 收款单编号
     * @param state 修改后的状态
     */
    void approval(String receiveMoneySheetId, ReceiveMoneySheetState state);
}
