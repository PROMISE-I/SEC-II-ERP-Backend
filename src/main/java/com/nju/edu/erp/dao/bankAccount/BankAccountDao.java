package com.nju.edu.erp.dao.bankAccount;

import com.nju.edu.erp.model.po.bankAccount.BankAccountPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Mapper
@Repository
public interface BankAccountDao {

    /**
     * 创建银行账户
     * @param bankAccountPO 银行账户
     */
    void addBankAccount(BankAccountPO bankAccountPO);

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
    List<BankAccountPO> findBankAccountByAmbiguousName(String ambiguousName);

    /**
     * 从银行账户播走一笔前
     * @param companyBankAccountId 银行账户id
     * @param actualSalary 金额数
     */
    void spendAtAccountId(Integer companyBankAccountId, BigDecimal actualSalary);

    /**
     * 查询账户id的余额
     * @param companyBankAccountId 账户id
     * @return 账户余额
     */
    BigDecimal getAmountByAccountId(Integer companyBankAccountId);
}
