package com.nju.edu.erp.service.Interface.finance.bank_account;

import com.nju.edu.erp.model.vo.finance.bank_account.BankAccountVO;

import java.math.BigDecimal;
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
     * @param ambiguousName 模糊的名称
     * @return 银行账户
     */
    List<BankAccountVO> findBankAccountByAmbiguousName(String ambiguousName);

    /**
     * 从银行账户播走一笔前
     * @param companyBankAccountId 银行账户id
     * @param amount 金额数
     */
    void spendAtAccountId(Integer companyBankAccountId, BigDecimal amount);

    /**
     * 存入银行账户一笔钱
     * @param companyBankAccountId 银行账户id
     * @param amount 金额数
     */
    void incomeAtAccountId(Integer companyBankAccountId, BigDecimal amount);

    /**
     * 查询账户id的余额
     * @param companyBankAccountId 账户id
     * @return 账户余额
     */
    BigDecimal getAmountByAccountId(Integer companyBankAccountId);

    /**
     * 查询对应id的银行账户
     * @param bankAccountId 公司银行账户id
     */
    BankAccountVO findAccountById(Integer bankAccountId);

    /**
     * 查询所有银行账户
     */
    List<BankAccountVO> findAllAccount();
}
