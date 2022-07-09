package com.nju.edu.erp.service.stub;

import com.nju.edu.erp.model.vo.finance.bank_account.BankAccountVO;
import com.nju.edu.erp.service.Interface.finance.bank_account.BankAccountService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WFS
 * @date 2022/7/10 2:04
 */
public class BankAccountStub implements BankAccountService {

    @Override
    public void addBankAccount(BankAccountVO bankAccountVO) {}

    @Override
    public void deleteBankAccount(int bankAccountId) {}

    @Override
    public void updateBankAccount(int bankAccountId, String newBankAccountName) {}

    @Override
    public List<BankAccountVO> findBankAccountByAmbiguousName(String ambiguousName) {
        List<BankAccountVO> res = new ArrayList<>();
        BankAccountVO bankAccountVO = BankAccountVO.builder()
                .id(1)
                .name("中国银行")
                .amount(BigDecimal.valueOf(1000000))
                .build();
        res.add(bankAccountVO);
        return res;
    }

    @Override
    public void spendAtAccountId(Integer companyBankAccountId, BigDecimal amount) {}

    @Override
    public void incomeAtAccountId(Integer companyBankAccountId, BigDecimal amount) {}

    @Override
    public BigDecimal getAmountByAccountId(Integer companyBankAccountId) {
        return BigDecimal.valueOf(1000000);
    }

    @Override
    public BankAccountVO findAccountById(Integer bankAccountId) {
        BankAccountVO bankAccountVO = BankAccountVO.builder()
                .id(1)
                .name("中国银行")
                .amount(BigDecimal.valueOf(1000000))
                .build();
        return bankAccountVO;
    }

    @Override
    public List<BankAccountVO> findAllAccount() {
        List<BankAccountVO> res = new ArrayList<>();
        BankAccountVO bankAccountVO = BankAccountVO.builder()
                .id(1)
                .name("中国银行")
                .amount(BigDecimal.valueOf(1000000))
                .build();
        res.add(bankAccountVO);
        return res;
    }
}
