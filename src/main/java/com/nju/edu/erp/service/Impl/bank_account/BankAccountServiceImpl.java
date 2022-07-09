package com.nju.edu.erp.service.Impl.bank_account;

import com.nju.edu.erp.dao.account.BankAccountDao;
import com.nju.edu.erp.model.po.bank_account.BankAccountPO;
import com.nju.edu.erp.model.vo.bank_account.BankAccountVO;
import com.nju.edu.erp.service.Interface.bank_account.BankAccountService;
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

        return getBankAccountVOS(accounts);
    }

    @Override
    public void spendAtAccountId(Integer companyBankAccountId, BigDecimal amount) {
        bankAccountDao.spendAtAccountId(companyBankAccountId, amount);
    }

    @Override
    public void incomeAtAccountId(Integer companyBankAccountId, BigDecimal amount) {
        bankAccountDao.incomeAtAccountId(companyBankAccountId, amount);
    }

    @Override
    public BigDecimal getAmountByAccountId(Integer companyBankAccountId) {
        return bankAccountDao.getAmountByAccountId(companyBankAccountId);
    }

    @Override
    public BankAccountVO findAccountById(Integer bankAccountId) {
        BankAccountPO account = bankAccountDao.findBankAccountById(bankAccountId);
        BankAccountVO res = new BankAccountVO();

        BeanUtils.copyProperties(account, res);

        return res;
    }

    @Override
    public List<BankAccountVO> findAllAccount() {
        List<BankAccountPO> allAccount = bankAccountDao.findAll();
        return getBankAccountVOS(allAccount);
    }

    private List<BankAccountVO> getBankAccountVOS(List<BankAccountPO> allAccount) {
        List<BankAccountVO> res = new ArrayList<>();

        for (BankAccountPO accountPO : allAccount) {
            BankAccountVO accountVO = new BankAccountVO();
            BeanUtils.copyProperties(accountPO, accountVO);
            res.add(accountVO);
        }

        return res;
    }

}
