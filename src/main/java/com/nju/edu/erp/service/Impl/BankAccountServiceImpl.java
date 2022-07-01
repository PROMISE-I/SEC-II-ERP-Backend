package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.model.vo.bankAccount.BankAccountVO;
import com.nju.edu.erp.service.BankAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    @Override
    public void addBankAccount(BankAccountVO bankAccountVO) {
        //TODO
    }

    @Override
    public void deleteBankAccount(int bankAccountId) {
        //TODO
    }

    @Override
    public void updateBankAccount(int bankAccountId, String newBankAccountName) {
        //TODO
    }

    @Override
    public List<BankAccountVO> findBankAccountByAmbiguousName(String ambiguousName) {
        //TODO
        return null;
    }

}
