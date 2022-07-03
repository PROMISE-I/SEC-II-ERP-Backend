package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.model.po.finance.SalarySheetPO;
import com.nju.edu.erp.model.vo.finance.SalarySheetVO;
import com.nju.edu.erp.service.BankAccountService;
import com.nju.edu.erp.service.SalaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    private final SalaryDao salaryDao;

    private final BankAccountService bankAccountService;

    @Override
    @Transactional
    public void makeSalarySheet(int employeeId) {

    }

    @Override
    public BigDecimal getSalaryByEmployeeId(int employeeId) {
        Date date;
        SalarySheetPO sheet = salaryDao.findSheetByEmployeeIdAndDate(employeeId, date);
        if (sheet == null) {
            //TODO 计算本月的薪资
            return BigDecimal.ZERO;
        } else {
            return sheet.getActualSalary();
        }
    }

    @Override
    public List<SalarySheetVO> getSalarySheetByState(SalarySheetState state) {
        List<SalarySheetVO> res = new ArrayList<>();
        List<SalarySheetPO> all;
        if (state == null) {
            all = salaryDao.findAllSheet();
        } else {
            all = salaryDao.findAllSheetByState();
        }

        for (SalarySheetPO salarySheetPO : all) {
            SalarySheetVO salarySheetVO = new SalarySheetVO();
            BeanUtils.copyProperties(salarySheetPO, salarySheetVO);
            res.add(salarySheetVO);
        }
        return res;
    }

    @Override
    public Date getLatestDateByEmployeeId(int employeeId) {
        SalarySheetPO sheet = salaryDao.findLatestByEmployeeId(employeeId);
        return sheet.getCreateTime();
    }

    @Override
    @Transactional
    public void approval(String salarySheetId, SalarySheetState state) {
        if (state.equals(SalarySheetState.FAILURE)) {
            SalarySheetPO salarySheetPO = salaryDao.findSheetById(salarySheetId);
            if (salarySheetPO.getState() == SalarySheetState.SUCCESS) throw new RuntimeException("状态更新失败");
            int effectLines = salaryDao.updateSheetState(salarySheetId, state);
            if (effectLines == 0) throw new RuntimeException("状态更新失败");
        } else {
            SalarySheetState prevState;
            if (state.equals(SalarySheetState.PENDING_LEVEL_2)) {
                prevState = SalarySheetState.PENDING_LEVEL_1;
            } else if (state.equals(SalarySheetState.SUCCESS)) {
                prevState = SalarySheetState.PENDING_LEVEL_2;
            } else {
                throw new RuntimeException("状态更新失败");
            }

            int effectLines = salaryDao.updateSheetStateOnPrev(salarySheetId, prevState, state);
            if (effectLines == 0) throw new RuntimeException("状态更新失败");

            if (state.equals(SalarySheetState.SUCCESS)) {
                SalarySheetPO salarySheetPO = salaryDao.findSheetById(salarySheetId);
                Integer companyBankAccountId = salarySheetPO.getCompanyBankAccountId();
                BigDecimal actualSalary = salarySheetPO.getActualSalary();

                bankAccountService.spendAtAccountId(companyBankAccountId, actualSalary);
            }
        }
    }

    private BigDecimal calculateRawSalary() {

    }
}
