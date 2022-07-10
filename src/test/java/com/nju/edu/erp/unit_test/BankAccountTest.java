package com.nju.edu.erp.unit_test;

import com.nju.edu.erp.dao.finance.account.BankAccountDao;
import com.nju.edu.erp.model.po.finance.bank_account.BankAccountPO;
import com.nju.edu.erp.model.vo.finance.bank_account.BankAccountVO;
import com.nju.edu.erp.service.Impl.finance.bank_account.BankAccountServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountTest {

    @InjectMocks
    BankAccountServiceImpl bankAccountService1;

    @Mock
    private BankAccountDao bankAccountDao1;

    @Test
    public void findAccountByIdTest() {
        BankAccountPO bankAccountPO = BankAccountPO.builder()
                .id(1)
                .name("中国银行")
                .amount(BigDecimal.valueOf(1000000))
                .build();
        Mockito.when(bankAccountDao1.findBankAccountById(1)).thenReturn(bankAccountPO);

        BankAccountVO bankAccountVO = BankAccountVO.builder()
                .id(1)
                .name("中国银行")
                .amount(BigDecimal.valueOf(1000000))
                .build();

        Assertions.assertEquals(bankAccountVO, bankAccountService1.findAccountById(1));
    }

    @Test
    public void findAllAccountTest() {
        BankAccountPO bankAccountPO1 = BankAccountPO.builder()
                .id(1)
                .name("中国银行")
                .amount(BigDecimal.valueOf(1000000))
                .build();
        BankAccountPO bankAccountPO2 = BankAccountPO.builder()
                .id(2)
                .name("中国银行南京分行")
                .amount(BigDecimal.valueOf(2000000))
                .build();
        List<BankAccountPO> bankAccountPOS = new ArrayList<>();
        bankAccountPOS.add(bankAccountPO1);
        bankAccountPOS.add(bankAccountPO2);

        Mockito.when(bankAccountDao1.findAll()).thenReturn(bankAccountPOS);

        BankAccountVO bankAccountVO1 = BankAccountVO.builder()
                .id(1)
                .name("中国银行")
                .amount(BigDecimal.valueOf(1000000))
                .build();
        BankAccountVO bankAccountVO2 = BankAccountVO.builder()
                .id(2)
                .name("中国银行南京分行")
                .amount(BigDecimal.valueOf(2000000))
                .build();
        List<BankAccountVO> bankAccountVOS = new ArrayList<>();
        bankAccountVOS.add(bankAccountVO1);
        bankAccountVOS.add(bankAccountVO2);
        Assertions.assertEquals(bankAccountVOS, bankAccountService1.findAllAccount());
    }

    @Test
    public void findBankAccountByAmbiguousNameTest() {
        BankAccountPO bankAccountPO1 = BankAccountPO.builder()
                .id(1)
                .name("中国银行")
                .amount(BigDecimal.valueOf(1000000))
                .build();
        BankAccountPO bankAccountPO2 = BankAccountPO.builder()
                .id(2)
                .name("中国银行南京分行")
                .amount(BigDecimal.valueOf(2000000))
                .build();
        List<BankAccountPO> bankAccountPOS = new ArrayList<>();
        bankAccountPOS.add(bankAccountPO1);
        bankAccountPOS.add(bankAccountPO2);

        Mockito.when(bankAccountDao1.findBankAccountByAmbiguousName("银行")).thenReturn(bankAccountPOS);

        BankAccountVO bankAccountVO1 = BankAccountVO.builder()
                .id(1)
                .name("中国银行")
                .amount(BigDecimal.valueOf(1000000))
                .build();
        BankAccountVO bankAccountVO2 = BankAccountVO.builder()
                .id(2)
                .name("中国银行南京分行")
                .amount(BigDecimal.valueOf(2000000))
                .build();
        List<BankAccountVO> bankAccountVOS = new ArrayList<>();
        bankAccountVOS.add(bankAccountVO1);
        bankAccountVOS.add(bankAccountVO2);

        Assertions.assertEquals(bankAccountVOS, bankAccountService1.findBankAccountByAmbiguousName("银行"));
    }
}
