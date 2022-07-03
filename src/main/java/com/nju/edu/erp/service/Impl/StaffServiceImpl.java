package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.StaffDao;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    private final StaffDao staffDao;

    @Autowired
    public StaffServiceImpl(StaffDao staffDao){
        this.staffDao = staffDao;
    }
    @Override
    public int createStaff(StaffPO staffPO) {
        return staffDao.createStaff(staffPO);
    }

    @Override
    public int deleteStaffById(Integer staffId) {
        return staffDao.deleteOneById(staffId);
    }

    @Override
    public List<StaffPO> findAll() {
        return staffDao.findAll();
    }

    @Override
    public int updateOne(StaffPO staffPO) {
        return staffDao.updateOne(staffPO);
    }

    @Override
    public Role getRoleByEmployeeId(int employeeId) {
        return staffDao.getRoleByEmployeeId(employeeId);
    }
}