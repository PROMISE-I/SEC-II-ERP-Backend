package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.finance.PayMoneyDao;
import com.nju.edu.erp.enums.sheetState.PayMoneySheetState;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.po.finance.PayMoneySheetPO;
import com.nju.edu.erp.model.po.finance.PayMoneyTransferListPO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.finance.PayMoneySheetVO;
import com.nju.edu.erp.model.vo.finance.PayMoneyTransferListVO;
import com.nju.edu.erp.service.BankAccountService;
import com.nju.edu.erp.service.CustomerService;
import com.nju.edu.erp.service.PayMoneyService;
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
public class PayMoneyServiceImpl implements PayMoneyService {

    PayMoneyDao payMoneyDao;

    CustomerService customerService;

    BankAccountService bankAccountService;

    @Autowired
    public PayMoneyServiceImpl(PayMoneyDao payMoneyDao, CustomerService customerService, BankAccountService bankAccountService) {
        this.payMoneyDao = payMoneyDao;
        this.customerService = customerService;
        this.bankAccountService = bankAccountService;
    }

    @Override
    @Transactional
    public void makePayMoneySheet(UserVO userVO, PayMoneySheetVO payMoneySheetVO) {
        PayMoneySheetPO payMoneySheetPO = new PayMoneySheetPO();
        BeanUtils.copyProperties(payMoneySheetVO, payMoneySheetPO);
        // 此处根据制定单据人员确定操作员
        payMoneySheetPO.setOperator(userVO.getName());
        payMoneySheetPO.setCreateTime(new Date());
        PayMoneySheetPO latest = payMoneyDao.getLatest();
        String id = payMoneySheetVO.getId();
        //如果id为null说明是新建单据，否则为红冲或红冲并复制
        if (id == null) {
            id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "FKD");
        }
        payMoneySheetPO.setId(id);
        payMoneySheetPO.setState(PayMoneySheetState.PENDING_LEVEL_1);

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<PayMoneyTransferListPO> transferListPOS = new ArrayList<>();
        for (PayMoneyTransferListVO transferListVO : payMoneySheetVO.getTransferList()) {
            PayMoneyTransferListPO transferListPO = new PayMoneyTransferListPO();

            BeanUtils.copyProperties(transferListVO, transferListPO);
            transferListPO.setPayMoneySheetId(id);
            transferListPOS.add(transferListPO);

            totalAmount = totalAmount.add(transferListPO.getAmount());
        }

        payMoneySheetPO.setTotalAmount(totalAmount);
        payMoneyDao.save(payMoneySheetPO);
        payMoneyDao.saveBatch(transferListPOS);
    }

    @Override
    public List<PayMoneySheetVO> getPayMoneySheetByState(PayMoneySheetState state) {
        List<PayMoneySheetPO> all;
        if (state == null) {
            all = payMoneyDao.findAll();
        } else {
            all = payMoneyDao.finaAllByState(state);
        }

        return getPayMoneySheetVOS(all);
    }

    @Override
    @Transactional
    public void approval(String payMoneySheetId, PayMoneySheetState state) {
        if (state.equals(PayMoneySheetState.FAILURE)) {
            PayMoneySheetPO payMoneySheet = payMoneyDao.findOneById(payMoneySheetId);
            if (payMoneySheet.getState() == PayMoneySheetState.SUCCESS) throw new RuntimeException("状态更新失败");

            int effectLines = payMoneyDao.updateState(payMoneySheetId, state);
            if (effectLines == 0) throw new RuntimeException("运行失败");
        } else {
            PayMoneySheetState prevState;
            if(state.equals(PayMoneySheetState.SUCCESS)) {
                prevState = PayMoneySheetState.PENDING_LEVEL_2;
            } else if(state.equals(PayMoneySheetState.PENDING_LEVEL_2)) {
                prevState = PayMoneySheetState.PENDING_LEVEL_1;
            } else {
                throw new RuntimeException("状态更新失败");
            }

            int effectLines = payMoneyDao.updateStateV2(payMoneySheetId, prevState, state);
            if (effectLines == 0) throw new RuntimeException("更新失败");

            if (state.equals(PayMoneySheetState.SUCCESS)) {
                //单据审核通过，修改公司对客户的应付数据（减少payable）
                PayMoneySheetPO payMoneySheet = payMoneyDao.findOneById(payMoneySheetId);
                CustomerPO customer = customerService.findCustomerById(payMoneySheet.getCustomer());
                customer.setPayable(customer.getPayable().subtract(payMoneySheet.getTotalAmount()));
                customerService.updateCustomer(customer);
                //减少银行账户的余额
                List<PayMoneyTransferListPO> transferLists = payMoneyDao.findTransferListByPayMoneySheetId(payMoneySheetId);
                for (PayMoneyTransferListPO transferList : transferLists) {
                    BigDecimal amount = bankAccountService.getAmountByAccountId(transferList.getBankAccountId());
                    if (transferList.getAmount().compareTo(amount) > 0) {
                        throw new MyServiceException("D00001", "付款单转账列表的付款金额数大于银行账户的余额！审批失败！");
                    } else {
                        bankAccountService.spendAtAccountId(transferList.getBankAccountId(), transferList.getAmount());
                    }
                }
            }
        }
    }

    @Override
    public List<PayMoneySheetVO> findAllSheets() {
        List<PayMoneySheetPO> allSheets = payMoneyDao.findAll();
        return getPayMoneySheetVOS(allSheets);
    }

    private List<PayMoneySheetVO> getPayMoneySheetVOS(List<PayMoneySheetPO> allSheets) {
        List<PayMoneySheetVO> res = new ArrayList<>();

        for (PayMoneySheetPO po : allSheets) {
            PayMoneySheetVO vo = new PayMoneySheetVO();
            BeanUtils.copyProperties(po, vo);

            List<PayMoneyTransferListPO> transferLists = payMoneyDao.findTransferListByPayMoneySheetId(po.getId());
            List<PayMoneyTransferListVO> vos = new ArrayList<>();
            for (PayMoneyTransferListPO p : transferLists) {
                PayMoneyTransferListVO v = new PayMoneyTransferListVO();
                BeanUtils.copyProperties(p, v);
                vos.add(v);
            }

            vo.setTransferList(vos);
            res.add(vo);
        }
        return res;
    }
}
