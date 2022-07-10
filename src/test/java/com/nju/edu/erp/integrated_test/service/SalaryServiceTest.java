package com.nju.edu.erp.integrated_test.service;

import com.nju.edu.erp.enums.sheet_state.finance.SalarySheetState;
import com.nju.edu.erp.model.vo.finance.salary.SalarySheetVO;
import com.nju.edu.erp.service.Interface.finance.bank_account.BankAccountService;
import com.nju.edu.erp.service.Interface.finance.salary.SalaryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback
public class SalaryServiceTest {

    @Autowired
    SalaryService salaryService;

    @Autowired
    BankAccountService bankAccountService;

    @Test
    public void sheetMake() {
        salaryService.makeSalarySheet(0, 0);
        salaryService.approval("GZD-202207-0", SalarySheetState.PENDING_LEVEL_2);
        salaryService.approval("GZD-202207-0", SalarySheetState.SUCCESS);
        BigDecimal amount = bankAccountService.getAmountByAccountId(0);
        List<SalarySheetVO> sheets = salaryService.getSalarySheetByState(SalarySheetState.PENDING_LEVEL_1);

        System.out.println(sheets);
    }
}
