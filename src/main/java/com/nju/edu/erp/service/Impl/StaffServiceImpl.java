package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.StaffDao;
import com.nju.edu.erp.dao.UserDao;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.model.po.User;
import com.nju.edu.erp.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    private final StaffDao staffDao;
    private final UserDao userDao;

    @Autowired
    public StaffServiceImpl(StaffDao staffDao, UserDao userDao){
        this.staffDao = staffDao;
        this.userDao = userDao;
    }
    @Override
    public int createStaff(StaffPO staffPO) {
        User user = new User(null, staffPO.getName(),"123456", staffPO.getPosition());

        userDao.createUser(user);
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
        StaffPO staff = staffDao.getStaffById(employeeId);
        return staff.getPosition();
    }

    @Override
    public String getNameByStaffId(int staffId) {
        StaffPO staff = staffDao.getStaffById(staffId);
        return staff.getName();
    }

    @Override
    public void addBalanceByStaffId(BigDecimal amount, int staffId) {
        StaffPO staff = staffDao.getStaffById(staffId);
        staff.setBalance(staff.getBalance().add(amount));
        staffDao.updateOne(staff);
    }
}
