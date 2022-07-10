package com.nju.edu.erp.unit_test;

import com.nju.edu.erp.dao.finance.salary.SalaryDao;
import com.nju.edu.erp.enums.sheet_state.finance.SalarySheetState;
import com.nju.edu.erp.model.po.finance.salary.SalarySheetPO;
import com.nju.edu.erp.model.vo.finance.salary.SalarySheetVO;
import com.nju.edu.erp.service.Impl.finance.salary.SalaryServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SalaryTest {

    @InjectMocks
    SalaryServiceImpl salaryService1;

    @Mock
    SalaryDao salaryDao1;

    @Test
    public void getSalarySheetByStateTest() {
        Date date1 = new Date();
        Date date2 = new Date();
        SalarySheetPO salarySheetPO1 = SalarySheetPO.builder()
                .id("GZD-20220710-0")
                .staffId(1)
                .staffName("张三")
                .companyBankAccountId(1)
                .rawSalary(BigDecimal.valueOf(10000))
                .tax(BigDecimal.valueOf(2000))
                .actualSalary(BigDecimal.valueOf(8000))
                .state(SalarySheetState.SUCCESS)
                .createTime(date1)
                .build();
        SalarySheetPO salarySheetPO2 = SalarySheetPO.builder()
                .id("GZD-20220610-0")
                .staffId(1)
                .staffName("张三")
                .companyBankAccountId(1)
                .rawSalary(BigDecimal.valueOf(10000))
                .tax(BigDecimal.valueOf(2000))
                .actualSalary(BigDecimal.valueOf(8000))
                .state(SalarySheetState.SUCCESS)
                .createTime(date2)
                .build();
        List<SalarySheetPO> salarySheetPOList = new ArrayList<>();
        salarySheetPOList.add(salarySheetPO1);
        salarySheetPOList.add(salarySheetPO2);

        Mockito.when(salaryDao1.findAllSheetByState(SalarySheetState.SUCCESS)).thenReturn(salarySheetPOList);

        SalarySheetVO salarySheetVO1 = SalarySheetVO.builder()
                .id("GZD-20220710-0")
                .staffId(1)
                .staffName("张三")
                .companyBankAccountId(1)
                .rawSalary(BigDecimal.valueOf(10000))
                .tax(BigDecimal.valueOf(2000))
                .actualSalary(BigDecimal.valueOf(8000))
                .state(SalarySheetState.SUCCESS)
                .createTime(date1)
                .build();
        SalarySheetVO salarySheetVO2 = SalarySheetVO.builder()
                .id("GZD-20220610-0")
                .staffId(1)
                .staffName("张三")
                .companyBankAccountId(1)
                .rawSalary(BigDecimal.valueOf(10000))
                .tax(BigDecimal.valueOf(2000))
                .actualSalary(BigDecimal.valueOf(8000))
                .state(SalarySheetState.SUCCESS)
                .createTime(date2)
                .build();
        List<SalarySheetVO> salarySheetVOS = new ArrayList<>();
        salarySheetVOS.add(salarySheetVO1);
        salarySheetVOS.add(salarySheetVO2);

        Assertions.assertEquals(salarySheetVOS, salaryService1.getSalarySheetByState(SalarySheetState.SUCCESS));
    }

    @Test
    public void getLatestDateByEmployeeIdTest() {
        Date date = new Date();
        SalarySheetPO salarySheetPO = SalarySheetPO.builder()
                .id("GZD-20220710-0")
                .staffId(1)
                .staffName("张三")
                .companyBankAccountId(1)
                .rawSalary(BigDecimal.valueOf(10000))
                .tax(BigDecimal.valueOf(2000))
                .actualSalary(BigDecimal.valueOf(8000))
                .state(SalarySheetState.SUCCESS)
                .createTime(date)
                .build();

        Mockito.when(salaryDao1.findLatestByEmployeeId(1)).thenReturn(salarySheetPO);

        Assertions.assertEquals(date, salaryService1.getLatestDateByEmployeeId(1));
    }

    @Test
    public void findAllSalarySheetsTest() {
        Date date1 = new Date();
        Date date2 = new Date();
        SalarySheetPO salarySheetPO1 = SalarySheetPO.builder()
                .id("GZD-20220710-0")
                .staffId(1)
                .staffName("张三")
                .companyBankAccountId(1)
                .rawSalary(BigDecimal.valueOf(10000))
                .tax(BigDecimal.valueOf(2000))
                .actualSalary(BigDecimal.valueOf(8000))
                .state(SalarySheetState.SUCCESS)
                .createTime(date1)
                .build();
        SalarySheetPO salarySheetPO2 = SalarySheetPO.builder()
                .id("GZD-20220610-0")
                .staffId(1)
                .staffName("张三")
                .companyBankAccountId(1)
                .rawSalary(BigDecimal.valueOf(10000))
                .tax(BigDecimal.valueOf(2000))
                .actualSalary(BigDecimal.valueOf(8000))
                .state(SalarySheetState.SUCCESS)
                .createTime(date2)
                .build();
        List<SalarySheetPO> salarySheetPOList = new ArrayList<>();
        salarySheetPOList.add(salarySheetPO1);
        salarySheetPOList.add(salarySheetPO2);

        Mockito.when(salaryDao1.findAllSheet()).thenReturn(salarySheetPOList);

        SalarySheetVO salarySheetVO1 = SalarySheetVO.builder()
                .id("GZD-20220710-0")
                .staffId(1)
                .staffName("张三")
                .companyBankAccountId(1)
                .rawSalary(BigDecimal.valueOf(10000))
                .tax(BigDecimal.valueOf(2000))
                .actualSalary(BigDecimal.valueOf(8000))
                .state(SalarySheetState.SUCCESS)
                .createTime(date1)
                .build();
        SalarySheetVO salarySheetVO2 = SalarySheetVO.builder()
                .id("GZD-20220610-0")
                .staffId(1)
                .staffName("张三")
                .companyBankAccountId(1)
                .rawSalary(BigDecimal.valueOf(10000))
                .tax(BigDecimal.valueOf(2000))
                .actualSalary(BigDecimal.valueOf(8000))
                .state(SalarySheetState.SUCCESS)
                .createTime(date2)
                .build();
        List<SalarySheetVO> salarySheetVOS = new ArrayList<>();
        salarySheetVOS.add(salarySheetVO1);
        salarySheetVOS.add(salarySheetVO2);

        Assertions.assertEquals(salarySheetVOS, salaryService1.findAllSalarySheets());
    }
}
