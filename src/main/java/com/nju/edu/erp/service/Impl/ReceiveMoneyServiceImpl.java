package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.finance.ReceiveMoneyDao;
import com.nju.edu.erp.enums.sheetState.ReceiveMoneySheetState;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.po.finance.ReceiveMoneySheetPO;
import com.nju.edu.erp.model.vo.finance.ReceiveMoneySheetVO;
import com.nju.edu.erp.model.po.finance.ReceiveMoneyTransferListPO;
import com.nju.edu.erp.model.vo.finance.ReceiveMoneyTransferListVO;
import com.nju.edu.erp.service.BankAccountService;
import com.nju.edu.erp.service.CustomerService;
import com.nju.edu.erp.service.ReceiveMoneyService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReceiveMoneyServiceImpl implements ReceiveMoneyService {

    ReceiveMoneyDao receiveMoneyDao;

    CustomerService customerService;

    BankAccountService bankAccountService;

    @Autowired
    public ReceiveMoneyServiceImpl(ReceiveMoneyDao receiveMoneyDao, CustomerService customerService, BankAccountService bankAccountService) {
        this.receiveMoneyDao = receiveMoneyDao;
        this.customerService = customerService;
        this.bankAccountService = bankAccountService;
    }

    @Override
    @Transactional
    public void makeReceiveMoneySheet(UserVO userVO, ReceiveMoneySheetVO receiveMoneySheetVO) {
        ReceiveMoneySheetPO receiveMoneySheetPO = new ReceiveMoneySheetPO();
        BeanUtils.copyProperties(receiveMoneySheetVO, receiveMoneySheetPO);
        // 此处根据制定单据人员确定操作员
        receiveMoneySheetPO.setOperator(userVO.getName());
        receiveMoneySheetPO.setCreateTime(new Date());
        ReceiveMoneySheetPO latest = receiveMoneyDao.getLatest();
        String id = receiveMoneySheetVO.getId();
        //如果id为null说明是新建单据，否则为红冲或红冲并复制
        if (id == null) {
            id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "SKD");
        }
        receiveMoneySheetPO.setId(id);
        receiveMoneySheetPO.setState(ReceiveMoneySheetState.PENDING_LEVEL_1);

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<ReceiveMoneyTransferListPO> transferListPOS = new ArrayList<>();
        for (ReceiveMoneyTransferListVO transferListVO : receiveMoneySheetVO.getTransferList()) {
            ReceiveMoneyTransferListPO transferListPO = new ReceiveMoneyTransferListPO();

            BeanUtils.copyProperties(transferListVO, transferListPO);
            transferListPO.setReceiveMoneySheetId(id);
            transferListPOS.add(transferListPO);

            totalAmount = totalAmount.add(transferListPO.getAmount());
        }

        receiveMoneySheetPO.setTotalAmount(totalAmount);
        receiveMoneyDao.save(receiveMoneySheetPO);
        receiveMoneyDao.saveBatch(transferListPOS);
    }

    @Override
    public List<ReceiveMoneySheetVO> getReceiveMoneySheetByState(ReceiveMoneySheetState state) {
        List<ReceiveMoneySheetPO> all;
        if (state == null) {
            all = receiveMoneyDao.findAll();
        } else {
            all = receiveMoneyDao.finaAllByState(state);
        }

        return getReceiveMoneySheetVOS(all);
    }

    /**
     * 根据收款单id进行审批(state == "待二级审批"/"审批完成"/"审批失败")
     * @param receiveMoneySheetId 收款单编号
     * @param state 修改后的状态
     */
    @Override
    @Transactional
    public void approval(String receiveMoneySheetId, ReceiveMoneySheetState state) {
        if (state.equals(ReceiveMoneySheetState.FAILURE)) {
            ReceiveMoneySheetPO receiveMoneySheet = receiveMoneyDao.findOneById(receiveMoneySheetId);
            if (receiveMoneySheet.getState() == ReceiveMoneySheetState.SUCCESS) throw new RuntimeException("状态更新失败");

            int effectLines = receiveMoneyDao.updateState(receiveMoneySheetId, state);
            if (effectLines == 0) throw new RuntimeException("运行失败");
        } else {
            ReceiveMoneySheetState prevState;
            if(state.equals(ReceiveMoneySheetState.SUCCESS)) {
                prevState = ReceiveMoneySheetState.PENDING_LEVEL_2;
            } else if(state.equals(ReceiveMoneySheetState.PENDING_LEVEL_2)) {
                prevState = ReceiveMoneySheetState.PENDING_LEVEL_1;
            } else {
                throw new RuntimeException("状态更新失败");
            }

            int effectLines = receiveMoneyDao.updateStateV2(receiveMoneySheetId, prevState, state);
            if (effectLines == 0) throw new RuntimeException("更新失败");

            if (state.equals(ReceiveMoneySheetState.SUCCESS)) {
                //单据审核通过，修改公司对客户的应收数据（减少receivable）
                ReceiveMoneySheetPO receiveMoneySheet = receiveMoneyDao.findOneById(receiveMoneySheetId);
                CustomerPO customer = customerService.findCustomerById(receiveMoneySheet.getCustomer());
                customer.setReceivable(customer.getReceivable().subtract(receiveMoneySheet.getTotalAmount()));
                customerService.updateCustomer(customer);
                //增加银行账户的余额
                List<ReceiveMoneyTransferListPO> transferLists = receiveMoneyDao.findTransferListByReceiveMoneySheetId(receiveMoneySheetId);
                for (ReceiveMoneyTransferListPO transferList : transferLists) {
                    bankAccountService.incomeAtAccountId(transferList.getBankAccountId(), transferList.getAmount());
                }
            }
        }
    }

    @Override
    public List<ReceiveMoneySheetVO> findAllSheet() {
        List<ReceiveMoneySheetPO> allSheets = receiveMoneyDao.findAll();
        return getReceiveMoneySheetVOS(allSheets);
    }

    @Override
    public boolean isSheetExists(String sheetId) {
        return receiveMoneyDao.findOneById(sheetId) != null;
    }

    private List<ReceiveMoneySheetVO> getReceiveMoneySheetVOS(List<ReceiveMoneySheetPO> allSheets) {
        List<ReceiveMoneySheetVO> res = new ArrayList<>();

        for (ReceiveMoneySheetPO po : allSheets) {
            ReceiveMoneySheetVO vo = new ReceiveMoneySheetVO();
            BeanUtils.copyProperties(po, vo);

            List<ReceiveMoneyTransferListPO> transferLists = receiveMoneyDao.findTransferListByReceiveMoneySheetId(po.getId());
            List<ReceiveMoneyTransferListVO> vos = new ArrayList<>();
            for (ReceiveMoneyTransferListPO p : transferLists) {
                ReceiveMoneyTransferListVO v = new ReceiveMoneyTransferListVO();
                BeanUtils.copyProperties(p, v);
                vos.add(v);
            }

            vo.setTransferList(vos);
            res.add(vo);
        }

        return res;
    }
}
