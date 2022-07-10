package com.nju.edu.erp.service.stub;

import com.nju.edu.erp.enums.user.Role;
import com.nju.edu.erp.model.po.staff.StaffPO;
import com.nju.edu.erp.service.Interface.staff.StaffService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StaffServiceStub implements StaffService {
    @Override
    public int createStaff(StaffPO staffPO) {
        return 1;
    }

    @Override
    public int deleteStaffById(Integer staffId) {
        return 1;
    }

    @Override
    public List<StaffPO> findAll() {
        List<StaffPO> res = new ArrayList<>();
        StaffPO staffPO = StaffPO.builder()
                .id(1)
                .name("张三")
                .gender("男")
                .birthday("2002-01-01")
                .phone("12345678901")
                .position(Role.GM)
                .balance(BigDecimal.valueOf(100000))
                .build();
        res.add(staffPO);
        return res;
    }

    @Override
    public int updateOne(StaffPO staffPO) {
        return 1;
    }

    @Override
    public Role getRoleByEmployeeId(int employeeId) {
        return Role.GM;
    }

    @Override
    public String getNameByStaffId(int staffId) {
        return "张三";
    }

    @Override
    public StaffPO findStaffById(Integer staffId) {
        StaffPO staffPO = StaffPO.builder()
                .id(1)
                .name("张三")
                .gender("男")
                .birthday("2002-01-01")
                .phone("12345678901")
                .position(Role.GM)
                .balance(BigDecimal.valueOf(100000))
                .build();
        return staffPO;
    }

    @Override
    public void addBalanceByStaffId(BigDecimal amount, int staffId) {

    }

    @Override
    public List<StaffPO> findAllExceptGM() {
        List<StaffPO> res = new ArrayList<>();
        StaffPO staffPO = StaffPO.builder()
                .id(1)
                .name("张三")
                .gender("男")
                .birthday("2002-01-01")
                .phone("12345678901")
                .position(Role.GM)
                .balance(BigDecimal.valueOf(100000))
                .build();
        res.add(staffPO);
        return res;
    }
}
