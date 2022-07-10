package com.nju.edu.erp.utils;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author WFS
 * @date 2022/7/10 12:12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class IdGeneratorTest {
    @Test
    public void generateSheetIdTest1() {
        String id = "XSD-20220710-00000";
        String res = "XSD-20220710-00001";
        Assertions.assertEquals(res, IdGenerator.generateSheetId(id, "XSD"));
    }

    @Test
    public void generateSheetIdTest2() {
        String res = "XSD-20220710-00000";
        Assertions.assertEquals(res, IdGenerator.generateSheetId(null, "XSD"));
    }

    @Test
    public void generateSalarySheetIdTest() {
        String id = "GZD-202207-1";
        Assertions.assertEquals(id, IdGenerator.generateSalarySheetId(1));
    }
}
