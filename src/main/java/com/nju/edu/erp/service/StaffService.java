package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.po.StaffPO;

import java.util.List;

public interface StaffService {
    public int createStaff(StaffPO staffPO);

    public int deleteStaffById(Integer staffId);

    public List<StaffPO> findAll();

    public int updateOne(StaffPO staffPO);

    Role getRoleByEmployeeId(int employeeId);

    String getNameByStaffId(int staffId);
}
