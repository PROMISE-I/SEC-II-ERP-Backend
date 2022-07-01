package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.PayMoneySheetState;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.finance.PayMoneySheetVO;

import java.util.List;

public interface PayMoneyService {

    /**
     * 财务人员制定付款单
     * @param userVO 操作员
     * @param payMoneySheetVO 付款单
     */
    void makePayMoneySheet(UserVO userVO, PayMoneySheetVO payMoneySheetVO);

    /**
     * 根据状态查看付款单(state == null 则获取所有付款单)
     * @param state 付款单状态
     */
    List<PayMoneySheetVO> getPayMoneySheetByState(PayMoneySheetState state);

    /**
     * 审批单据
     * @param payMoneySheetId 付款单编号
     * @param state 修改后的状态
     */
    void approval(String payMoneySheetId, PayMoneySheetState state);
}
