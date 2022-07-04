package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.bankAccount.BankAccountDao;
import com.nju.edu.erp.model.po.bankAccount.BankAccountPO;
import com.nju.edu.erp.model.vo.bankAccount.BankAccountVO;
import com.nju.edu.erp.service.BankAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountDao bankAccountDao;

    @Autowired
    public BankAccountServiceImpl(BankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }

    @Override
    public void addBankAccount(BankAccountVO bankAccountVO) {
        BankAccountPO bankAccountPO = new BankAccountPO();
        BeanUtils.copyProperties(bankAccountVO, bankAccountPO);
        bankAccountDao.addBankAccount(bankAccountPO);
    }

    @Override
    public void deleteBankAccount(int bankAccountId) {
        bankAccountDao.deleteBankAccount(bankAccountId);
    }

    @Override
    public void updateBankAccount(int bankAccountId, String newBankAccountName) {
        bankAccountDao.updateBankAccount(bankAccountId, newBankAccountName);
    }

    @Override
    public List<BankAccountVO> findBankAccountByAmbiguousName(String ambiguousName) {
        List<BankAccountPO> accounts = bankAccountDao.findBankAccountByAmbiguousName(ambiguousName);

        List<BankAccountVO> res = new ArrayList<>();
        for (BankAccountPO account : accounts) {
            BankAccountVO bankAccountVO = new BankAccountVO();
            BeanUtils.copyProperties(account, bankAccountVO);
            res.add(bankAccountVO);
        }

        return res;
    }

    @Override
    public void spendAtAccountId(Integer companyBankAccountId, BigDecimal actualSalary) {
        bankAccountDao.spendAtAccountId(companyBankAccountId, actualSalary);
    }

    @Override
    public BigDecimal getAmountByAccountId(Integer companyBankAccountId) {
        return bankAccountDao.getAmountByAccountId(companyBankAccountId);
    }

}
