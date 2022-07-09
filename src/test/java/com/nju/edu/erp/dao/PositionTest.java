package com.nju.edu.erp.dao;

import com.nju.edu.erp.dao.staff.PositionDao;
import com.nju.edu.erp.enums.user.Role;
import com.nju.edu.erp.enums.salary_strategy.SalaryCalculateType;
import com.nju.edu.erp.enums.salary_strategy.SalarySendType;
import com.nju.edu.erp.model.po.staff.PositionInfoPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PositionTest {
    @Autowired
    PositionDao positionDao;

    @Test
    public void findAllTest(){
        //正确应该返回所有岗位信息
        List<PositionInfoPO> expected = new ArrayList<>();
        expected.add(new PositionInfoPO(Role.FINANCIAL_STAFF, BigDecimal.valueOf(5000.00), BigDecimal.valueOf(15000.00), 1, SalaryCalculateType.PLAIN_STAFF_PAY, SalarySendType.MONTHLY, BigDecimal.valueOf(0.15)));
        expected.add(new PositionInfoPO(Role.GM, BigDecimal.valueOf(20000.00), BigDecimal.valueOf(100000.00), 5, SalaryCalculateType.MANAGE_STAFF_PAY, SalarySendType.ANNUALLY, BigDecimal.valueOf(0.30)));
        expected.add(new PositionInfoPO(Role.HR, BigDecimal.valueOf(6000.00), BigDecimal.valueOf(15000.00), 2, SalaryCalculateType.PLAIN_STAFF_PAY, SalarySendType.MONTHLY, BigDecimal.valueOf(0.15)));
        expected.add(new PositionInfoPO(Role.INVENTORY_MANAGER, BigDecimal.valueOf(5000.00), BigDecimal.valueOf(15000.00), 1, SalaryCalculateType.PLAIN_STAFF_PAY, SalarySendType.MONTHLY, BigDecimal.valueOf(0.15)));
        expected.add(new PositionInfoPO(Role.SALE_MANAGER, BigDecimal.valueOf(10000.00), BigDecimal.valueOf(40000.00), 3, SalaryCalculateType.COMMISSION_STAFF_PAY, SalarySendType.MONTHLY, BigDecimal.valueOf(0.30)));
        expected.add(new PositionInfoPO(Role.SALE_STAFF, BigDecimal.valueOf(5000.00), BigDecimal.valueOf(10000.00), 1, SalaryCalculateType.COMMISSION_STAFF_PAY, SalarySendType.MONTHLY, BigDecimal.valueOf(0.10)));
        List<PositionInfoPO> ans = positionDao.findAll();
        assert (expected.size() == ans.size());
        //逐个检查每个岗位信息是否出现
        for(PositionInfoPO p : expected){
            boolean success = false;
            for(PositionInfoPO q : ans){
                if(p.equals(q)){
                    success = true;
                    break;
                }
            }
            assert (success);
        }
    }

    @Test
    public void findByTitleTest(){
        PositionInfoPO expected = new PositionInfoPO(Role.FINANCIAL_STAFF, BigDecimal.valueOf(5000.00), BigDecimal.valueOf(15000.00), 1, SalaryCalculateType.PLAIN_STAFF_PAY, SalarySendType.MONTHLY, BigDecimal.valueOf(0.15));
        PositionInfoPO ans = positionDao.findOneByTitle(Role.FINANCIAL_STAFF);
        assert (ans.equals(expected));
    }

    @Test
    @Transactional
    public void updateTest(){
        PositionInfoPO expected = new PositionInfoPO(Role.FINANCIAL_STAFF, BigDecimal.valueOf(5000.00), BigDecimal.valueOf(15000.00), 1, SalaryCalculateType.PLAIN_STAFF_PAY, SalarySendType.MONTHLY, BigDecimal.valueOf(0.15));
        //修改级别
        expected.setLevel(2);
        positionDao.updateOne(expected);
        PositionInfoPO ans = positionDao.findOneByTitle(Role.FINANCIAL_STAFF);
        assert (ans.equals(expected));
    }


}
