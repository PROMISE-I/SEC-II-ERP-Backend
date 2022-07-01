package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.bankAccount.BankAccountVO;

import java.util.List;

public interface BankAccountService {

    /**
     * 创建银行账户
     * @param bankAccountVO 银行账户
     */
    void addBankAccount(BankAccountVO bankAccountVO);

    /**
     * 删除银行账户
     * @param bankAccountId 银行账户id
     */
    void deleteBankAccount(int bankAccountId);

    /**
     * 更新银行账户名称
     * @param bankAccountId 银行账户id
     * @param newBankAccountName 新的银行账户名称
     */
    void updateBankAccount(int bankAccountId, String newBankAccountName);

    /**
     * 通过模糊查找查询所有
     * @param ambiguousName
     * @return
     */
    List<BankAccountVO> findBankAccountByAmbiguousName(String ambiguousName);
}
