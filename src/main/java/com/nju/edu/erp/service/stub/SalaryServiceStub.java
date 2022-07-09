package com.nju.edu.erp.service.stub;

import com.nju.edu.erp.enums.sheet_state.finance.SalarySheetState;
import com.nju.edu.erp.model.vo.finance.salary.SalarySheetVO;
import com.nju.edu.erp.service.Interface.finance.salary.SalaryService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WFS
 * @date 2022/7/10 2:16
 */
public class SalaryServiceStub implements SalaryService {
    @Override
    public void makeSalarySheet(int employeeId, int companyBankAccountId) {
    }

    @Override
    public BigDecimal getSalaryByEmployeeId(int employeeId) {
        return BigDecimal.valueOf(10000);
    }

    @Override
    public List<SalarySheetVO> getSalarySheetByState(SalarySheetState state) {
        List<SalarySheetVO> res = new ArrayList<>();
        SalarySheetVO salarySheetVO = SalarySheetVO.builder()
                .id("GZD-20220710-0")
                .staffId(1)
                .staffName("张三")
                .companyBankAccountId(1)
                .rawSalary(BigDecimal.valueOf(10000))
                .tax(BigDecimal.valueOf(2000))
                .actualSalary(BigDecimal.valueOf(8000))
                .state(SalarySheetState.SUCCESS)
                .createTime(new Date())
                .build();
        res.add(salarySheetVO);
        return res;
    }

    @Override
    public Date getLatestDateByEmployeeId(int employeeId) {
        return new Date();
    }

    @Override
    public void approval(String salarySheetId, SalarySheetState state) {}

    @Override
    public List<SalarySheetVO> findAllSalarySheets() {
        List<SalarySheetVO> res = new ArrayList<>();
        SalarySheetVO salarySheetVO = SalarySheetVO.builder()
                .id("GZD-20220710-0")
                .staffId(1)
                .staffName("张三")
                .companyBankAccountId(1)
                .rawSalary(BigDecimal.valueOf(10000))
                .tax(BigDecimal.valueOf(2000))
                .actualSalary(BigDecimal.valueOf(8000))
                .state(SalarySheetState.SUCCESS)
                .createTime(new Date())
                .build();
        res.add(salarySheetVO);
        return res;
    }

    @Override
    public void redInkOffsetSheetMake(SalarySheetVO salarySheetVO) {}

    @Override
    public BigDecimal getTotalAmountBeforeNovember(int staffId) {
        return BigDecimal.valueOf(10000);
    }

    @Override
    public boolean isSheetExists(String sheetId) {
        return true;
    }
}
