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
     * 根据状态查看收款单(state == null 则获取所有收款单)
     * @param state 收款单状态
     */
    List<ReceiveMoneySheetVO> getReceiveMoneySheetByState(ReceiveMoneySheetState state);

    /**
     * 审批单据
     * @param receiveMoneySheetId 收款单编号
     * @param state 修改后的状态
     */
    void approval(String receiveMoneySheetId, ReceiveMoneySheetState state);

    /**
     * 获得所有收款单
     * @return 收款单列表
     */
    List<ReceiveMoneySheetVO> findAllSheet();

    /**
     * 查看单据是否存在
     * @param sheetId 单据编号
     * @return 是否存在, true 表示存在
     */
    boolean isSheetExists(String sheetId);
}
