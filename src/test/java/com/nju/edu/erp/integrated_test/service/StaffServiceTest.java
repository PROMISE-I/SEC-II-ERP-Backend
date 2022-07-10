package com.nju.edu.erp.integrated_test.service;

import com.nju.edu.erp.dao.user.UserDao;
import com.nju.edu.erp.dao.staff.UserToStaffDao;
import com.nju.edu.erp.enums.user.Role;
import com.nju.edu.erp.model.po.staff.StaffPO;
import com.nju.edu.erp.model.po.user.User;
import com.nju.edu.erp.service.Interface.staff.StaffService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StaffServiceTest {
    @Autowired
    StaffService staffService;

    @Autowired
    UserDao userDao;

    @Autowired
    UserToStaffDao userToStaffDao;

    @Transactional
    @Test
    public void createTest(){
        StaffPO staffPO = StaffPO.builder()
                .name("zhangsan")
                .birthday("2002-09-09")
                .balance(BigDecimal.valueOf(0))
                .gender("男")
                .phone("123456")
                .position(Role.GM)
                .build();
        staffService.createStaff(staffPO);
        User user = userDao.findByUsername(staffPO.getName() + staffPO.getId());
        Integer userId = userToStaffDao.findUserIdByStaffId(staffPO.getId());
        assert (user != null && user.getPassword().equals("123456"));
        assert (userId != null);
    }

    @Test
    @Transactional
    public void deleteTest(){
        StaffPO staffPO = StaffPO.builder()
                .name("zhangsan")
                .birthday("2002-09-09")
                .balance(BigDecimal.valueOf(0))
                .gender("男")
                .phone("123456")
                .position(Role.GM)
                .build();
        staffService.createStaff(staffPO);
        String username = "zhangsan" + staffPO.getId();
        Integer staffId = staffPO.getId();
        staffService.deleteStaffById(staffId);
        assert (staffService.findStaffById(staffId) == null);
        assert (userDao.findByUsername(username) == null);
        assert (userToStaffDao.findUserIdByStaffId(staffId) == null);
    }

    @Test
    @Transactional
    public void findRoleTest(){
        StaffPO staffPO = StaffPO.builder()
                .name("zhangsan")
                .birthday("2002-09-09")
                .balance(BigDecimal.valueOf(0))
                .gender("男")
                .phone("123456")
                .position(Role.GM)
                .build();
        staffService.createStaff(staffPO);
        Integer staffId = staffPO.getId();
        Role role = staffService.getRoleByEmployeeId(staffId);
        assert (role.equals(Role.GM));
    }

    @Test
    @Transactional
    public void addBalanceTest(){
        StaffPO staffPO = StaffPO.builder()
                .name("zhangsan")
                .birthday("2002-09-09")
                .balance(BigDecimal.valueOf(0))
                .gender("男")
                .phone("123456")
                .position(Role.GM)
                .build();
        staffService.createStaff(staffPO);
        Integer staffId = staffPO.getId();
        StaffPO s = staffService.findStaffById(staffId);
        assert (s.getBalance().compareTo(BigDecimal.valueOf(0)) == 0);
        staffService.addBalanceByStaffId(BigDecimal.valueOf(1000.00), staffId);
        s = staffService.findStaffById(staffId);
        assert(s.getBalance().compareTo(BigDecimal.valueOf(1000.00)) == 0);
    }
}
