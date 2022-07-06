package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.model.vo.StaffVO;

import java.math.BigDecimal;
import java.util.List;

public interface StaffService {
    public int createStaff(StaffPO staffPO);

    public int deleteStaffById(Integer staffId);

    public List<StaffPO> findAll();

    public int updateOne(StaffPO staffPO);

    Role getRoleByEmployeeId(int employeeId);

    String getNameByStaffId(int staffId);

    StaffPO findStaffById(Integer staffId);

    void addBalanceByStaffId(BigDecimal amount, int staffId);
}
