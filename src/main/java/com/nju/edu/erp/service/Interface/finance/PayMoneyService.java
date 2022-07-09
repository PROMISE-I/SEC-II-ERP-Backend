package com.nju.edu.erp.service.Interface.finance;

import com.nju.edu.erp.enums.sheet_state.finance.PayMoneySheetState;
import com.nju.edu.erp.model.vo.user.UserVO;
import com.nju.edu.erp.model.vo.finance.pay_money.PayMoneySheetVO;

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

    /**
     * 获取所有的付款单
     * @return 付款单列表
     */
    List<PayMoneySheetVO> findAllSheets();

    /**
     * 查看单据是否存在
     * @param sheetId 单据编号
     * @return 是否存在, true 表示存在
     */
    boolean isSheetExists(String sheetId);
}
