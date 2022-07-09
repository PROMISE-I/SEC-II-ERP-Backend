package com.nju.edu.erp.dao.finance;

import com.nju.edu.erp.enums.sheet_state.finance.ReceiveMoneySheetState;
import com.nju.edu.erp.model.po.finance.receive_money.ReceiveMoneySheetPO;
import com.nju.edu.erp.model.po.finance.receive_money.ReceiveMoneyTransferListPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReceiveMoneyDao {

    /**
     * 获取最近一条收款单
     * @return 最近一条收款单
     */
    ReceiveMoneySheetPO getLatest();

    /**
     * 存入一条收款单记录
     * @param receiveMoneySheetPO 收款单
     */
    void save(ReceiveMoneySheetPO receiveMoneySheetPO);

    /**
     * 把收款单上的转账列表存入数据库中
     * @param transferListPOS 转账列表
     */
    void saveBatch(List<ReceiveMoneyTransferListPO> transferListPOS);

    /**
     * 返回所有收款单
     * @return 收款单列表
     */
    List<ReceiveMoneySheetPO> findAll();

    /**
     * 根据state返回收款单
     * @param state 收款单状态
     * @return 收款单列表
     */
    List<ReceiveMoneySheetPO> finaAllByState(ReceiveMoneySheetState state);

    /**
     * 通过收款单单据编号找到对应的转账列表
     * @param receiveMoneySheetId 收款单单据编号
     * @return 转账列表
     */
    List<ReceiveMoneyTransferListPO> findTransferListByReceiveMoneySheetId(String receiveMoneySheetId);

    /**
     * 根据收款单编号找到收款单
     * @param receiveMoneySheetId 收款单编号
     * @return 收款单
     */
    ReceiveMoneySheetPO findOneById(String receiveMoneySheetId);

    /**
     * 将对应编号的收款单的状态更改为state
     * @param receiveMoneySheetId 收款单单据编号
     * @param state 修改后的状态
     * @return 影响行数
     */
    int updateState(String receiveMoneySheetId, ReceiveMoneySheetState state);

    /**
     * 跟对应编号收款单找出状态为prevState的收款单并且更改状态为state
     * @param receiveMoneySheetId 收款单单据编号
     * @param prevState 修改前状态
     * @param state 修改后状态
     * @return 影响行数
     */
    int updateStateV2(String receiveMoneySheetId, ReceiveMoneySheetState prevState, ReceiveMoneySheetState state);
}
