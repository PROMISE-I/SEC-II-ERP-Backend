package com.nju.edu.erp.integrated_test.dao;

import com.nju.edu.erp.dao.finance.sheets_counting.OperateSheetDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperateSheetTest {

    @Autowired
    OperateSheetDao operateSheetDao;

    @Test
    public void findFinalDiscountedAmountTest(){
        String begin = "2022-04-01";
        String end = "2022-09-09";
        BigDecimal ans = operateSheetDao.findDiscountedAmount(begin, end);
        BigDecimal expected = BigDecimal.valueOf(268400.00);

        assert (ans.compareTo(expected) == 0);
    }
}
