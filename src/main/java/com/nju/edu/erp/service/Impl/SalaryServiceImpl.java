package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.finance.SalaryDao;
import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.model.po.PositionInfoPO;
import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.model.po.finance.SalarySheetPO;
import com.nju.edu.erp.model.vo.finance.SalarySheetVO;
import com.nju.edu.erp.service.*;
import com.nju.edu.erp.service.Impl.strategy.salary.calculate.FactorySalaryCalculateStrategy;
import com.nju.edu.erp.service.Impl.strategy.salary.calculate.StaffSalaryCalculateStrategy;
import com.nju.edu.erp.service.Impl.strategy.salary.calculate.StaffInfo;
import com.nju.edu.erp.utils.DateHelper;
import com.nju.edu.erp.utils.IdGenerator;
import com.nju.edu.erp.utils.TaxCalculator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final StaffService staffService;

    private final PositionService positionService;

    private final SaleService saleService;

    private final SaleReturnsService saleReturnsService;

    private final AttendanceService attendanceService;

    private final YearEndAwardsService yearEndAwardsService;

    @Autowired
    public SalaryServiceImpl(SalaryDao salaryDao, BankAccountService bankAccountService, StaffService staffService, PositionService positionService, SaleService saleService, SaleReturnsService saleReturnsService, AttendanceService attendanceService, YearEndAwardsService yearEndAwardsService) {
        this.salaryDao = salaryDao;
        this.bankAccountService = bankAccountService;
        this.staffService = staffService;
        this.positionService = positionService;
        this.saleService = saleService;
        this.saleReturnsService = saleReturnsService;
        this.attendanceService = attendanceService;
        this.yearEndAwardsService = yearEndAwardsService;
    }

    @Override
    @Transactional
    public void makeSalarySheet(int employeeId, int companyBankAccountId) {
        SalarySheetPO salarySheet = new SalarySheetPO();
        String employeeName = staffService.getNameByStaffId(employeeId);
        //薪酬制定方案
        BigDecimal yearEndAwards = getYearEndAwards(employeeId);
        BigDecimal rawSalary = calculateRawSalary(employeeId).add(yearEndAwards);
        BigDecimal tax = TaxCalculator.calculateTax(rawSalary);
        BigDecimal actualSalary = rawSalary.subtract(tax);

        String id = IdGenerator.generateSalarySheetId(employeeId);
        salarySheet.setId(id);
        salarySheet.setStaffId(employeeId);
        salarySheet.setStaffName(employeeName);
        salarySheet.setCompanyBankAccountId(companyBankAccountId);
        salarySheet.setRawSalary(rawSalary);
        salarySheet.setTax(tax);
        salarySheet.setActualSalary(actualSalary);
        salarySheet.setState(SalarySheetState.PENDING_LEVEL_1);
        salarySheet.setCreateTime(new Date());

        salaryDao.saveSheet(salarySheet);
    }

    @Override
    public BigDecimal getSalaryByEmployeeId(int employeeId) {
        Date date = new Date();
        SalarySheetPO sheet = salaryDao.findSheetByEmployeeIdAndDate(employeeId, date);
        if (sheet == null) {
            //计算本月的薪资
            BigDecimal rawSalary = calculateRawSalary(employeeId);
            BigDecimal tax = TaxCalculator.calculateTax(rawSalary);
            return rawSalary.subtract(tax);
        } else {
            return sheet.getActualSalary();
        }
    }

    @Override
    public List<SalarySheetVO> getSalarySheetByState(SalarySheetState state) {
        List<SalarySheetPO> all;
        if (state == null) {
            all = salaryDao.findAllSheet();
        } else {
            all = salaryDao.findAllSheetByState(state);
        }

        return getSalarySheetVOS(all);
    }

    @Override
    public Date getLatestDateByEmployeeId(int employeeId) {
        SalarySheetPO sheet = salaryDao.findLatestByEmployeeId(employeeId);
        return sheet == null ? null : sheet.getCreateTime();
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

                BigDecimal amount = bankAccountService.getAmountByAccountId(companyBankAccountId);
                if (amount.compareTo(actualSalary) >= 0) {
                    bankAccountService.spendAtAccountId(companyBankAccountId, actualSalary);
                    staffService.addBalanceByStaffId(actualSalary, salarySheetPO.getStaffId());
                } else {
                    throw new RuntimeException("公司账户余额不足，审批失败");
                }
            }
        }
    }

    @Override
    public List<SalarySheetVO> findAllSalarySheets() {
        List<SalarySheetPO> allSheets = salaryDao.findAllSheet();
        return getSalarySheetVOS(allSheets);
    }

    @Override
    public void redInkOffsetSheetMake(SalarySheetVO salarySheetVO) {
        SalarySheetPO toSave = new SalarySheetPO();

        BeanUtils.copyProperties(salarySheetVO, toSave);
        toSave.setState(SalarySheetState.PENDING_LEVEL_1);
        toSave.setCreateTime(new Date());

        salaryDao.saveSheet(toSave);
    }

    private BigDecimal calculateRawSalary(int staffId) {
        StaffInfo staffInfo = getStaffInfo(staffId);
        StaffSalaryCalculateStrategy strategy = FactorySalaryCalculateStrategy.productStrategy(staffInfo);
        return strategy.calculate();
    }

    private StaffInfo getStaffInfo(int staffId) {
        StaffInfo staffInfo = new StaffInfo();
        StaffPO staff = staffService.findStaffById(staffId);
        PositionInfoPO position = positionService.findOneByTitle(staff.getPosition());
        int year = DateHelper.getYearInLastMonth();
        int month = DateHelper.getMonthInLastMonth();
        String salesman = staff.getName();
        BigDecimal saleAmount = saleService.getTotalSaleAmountByMonthAndYearAndSalesman(year, month, salesman);
        BigDecimal saleReturnsAmount = saleReturnsService.getTotalSaleReturnsAmountByMonthAndYearAndSalesman(year, month, salesman);
        BigDecimal checkInTimeMonthly = BigDecimal.valueOf(attendanceService.getAttendanceTime(staffId, year, month));

        staffInfo.setBasicSalary(position.getBaseSalary());
        staffInfo.setSpecialSalary(position.getSpecialSalary());
        staffInfo.setSalaryCalculateMethod(position.getSalaryCalculateMethod());
        staffInfo.setTotalSaleAmount(saleAmount.subtract(saleReturnsAmount));
        staffInfo.setCheckInTimeMonthly(checkInTimeMonthly);

        return staffInfo;
    }

    private List<SalarySheetVO> getSalarySheetVOS(List<SalarySheetPO> sheets) {
        List<SalarySheetVO> res = new ArrayList<>();
        for (SalarySheetPO salarySheetPO : sheets) {
            SalarySheetVO salarySheetVO = new SalarySheetVO();
            BeanUtils.copyProperties(salarySheetPO, salarySheetVO);
            res.add(salarySheetVO);
        }
        return res;
    }

    /**
     * 非1月份制定的工资单调用这个方法返回均为0
     * @param staffId 员工编号
     * @return 年终奖金额
     */
    private BigDecimal getYearEndAwards(int staffId) {
        int year = DateHelper.getYearInLastMonth();
        int month = DateHelper.getMonthInLastMonth();
        if (month == 12) {
            return yearEndAwardsService.getYearEndAwardsByStaffId(staffId, year);
        } else {
            return BigDecimal.ZERO;
        }
    }
}
