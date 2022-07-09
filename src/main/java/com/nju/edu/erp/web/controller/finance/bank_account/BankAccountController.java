package com.nju.edu.erp.web.controller.finance.bank_account;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.user.Role;
import com.nju.edu.erp.model.vo.finance.bank_account.BankAccountVO;
import com.nju.edu.erp.service.Interface.finance.bank_account.BankAccountService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/bankAccount")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    /**
     * admin 增加公司银行账户
     * @param bankAccountVO 公司银行账户
     */
    @Authorized(roles = {Role.ADMIN})
    @PostMapping("/add")
    public Response addBankAccount(@RequestBody BankAccountVO bankAccountVO) {
        bankAccountService.addBankAccount(bankAccountVO);
        return Response.buildSuccess();
    }

    /**
     * admin 删除公司银行账户
     * @param bankAccountId 公司银行账户id
     */
    @Authorized(roles = {Role.ADMIN})
    @GetMapping("/delete")
    public Response deleteBankAccount(@RequestParam int bankAccountId) {
        bankAccountService.deleteBankAccount(bankAccountId);
        return Response.buildSuccess();
    }

    /**
     * 更改银行账户名称
     * @param bankAccountId 银行账户id
     * @param newBankAccountName 新的银行账户名称
     */
    @Authorized(roles = {Role.ADMIN})
    @GetMapping("/update")
    public Response updateBankAccount(@RequestParam int bankAccountId, @RequestParam String newBankAccountName) {
        bankAccountService.updateBankAccount(bankAccountId, newBankAccountName);
        return Response.buildSuccess();
    }

    /**
     * 通过模糊查找查询银行账户信息
     * @param ambiguousName 模糊的名称
     */
    @Authorized(roles = {Role.ADMIN})
    @GetMapping("/findByAmbiguousName")
    public Response findBankAccount(@RequestParam String ambiguousName) {
        return Response.buildSuccess(bankAccountService.findBankAccountByAmbiguousName(ambiguousName));
    }

    /**
     * 查询对应id的银行账户
     * @param bankAccountId 公司银行账户id
     */
    @GetMapping("/findAccountById")
    public Response findAccountById(@RequestParam Integer bankAccountId) {
        return Response.buildSuccess(bankAccountService.findAccountById(bankAccountId));
    }

    /**
     * 查询所有银行账户
     */
    @GetMapping("findAllAccount")
    public Response findAll() {
        return Response.buildSuccess(bankAccountService.findAllAccount());
    }
}
