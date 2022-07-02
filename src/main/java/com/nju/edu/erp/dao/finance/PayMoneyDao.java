package com.nju.edu.erp.dao.finance;

import com.nju.edu.erp.enums.sheetState.PayMoneySheetState;
import com.nju.edu.erp.model.po.finance.PayMoneySheetPO;
import com.nju.edu.erp.model.po.finance.PayMoneyTransferListPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PayMoneyDao {
    /**
     * 获取最近一条付款单
     * @return 最近一条付款单
     */
    PayMoneySheetPO getLatest();

    /**
     * 存入一条付款单记录
     * @param payMoneySheetPO 付款单
     */
    void save(PayMoneySheetPO payMoneySheetPO);

    /**
     * 把付款单上的转账列表存入数据库中
     * @param transferListPOS 转账列表
     */
    void saveBatch(List<PayMoneyTransferListPO> transferListPOS);

    /**
     * 返回所有付款单
     * @return 付款单列表
     */
    List<PayMoneySheetPO> findAll();

    /**
     * 根据state返回付款单
     * @param state 付款单状态
     * @return 付款单列表
     */
    List<PayMoneySheetPO> finaAllByState(PayMoneySheetState state);

    /**
     * 通过付款单单据编号找到对应的转账列表
     * @param payMoneySheetId 付款单单据编号
     * @return 转账列表
     */
    List<PayMoneyTransferListPO> findTransferListByPayMoneySheetId(String payMoneySheetId);

    /**
     * 根据付款单编号找到收款单
     * @param payMoneySheetId 付款单编号
     * @return 付款单
     */
    PayMoneySheetPO findOneById(String payMoneySheetId);

    /**
     * 将对应编号的付款单的状态更改为state
     * @param payMoneySheetId 付款单单据编号
     * @param state 修改后的状态
     * @return 影响行数
     */
    int updateState(String payMoneySheetId, PayMoneySheetState state);

    /**
     * 跟对应编号付款单找出状态为prevState的付款单并且更改状态为state
     * @param payMoneySheetId 付款单单据编号
     * @param prevState 修改前状态
     * @param state 修改后状态
     * @return 影响行数
     */
    int updateStateV2(String payMoneySheetId, PayMoneySheetState prevState, PayMoneySheetState state);
}
