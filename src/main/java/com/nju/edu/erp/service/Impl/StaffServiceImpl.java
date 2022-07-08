package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.StaffDao;
import com.nju.edu.erp.dao.UserDao;
import com.nju.edu.erp.dao.UserToStaffDao;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.model.po.User;
import com.nju.edu.erp.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    private final StaffDao staffDao;
    private final UserDao userDao;
    private final UserToStaffDao userToStaffDao;

    @Autowired
    public StaffServiceImpl(StaffDao staffDao, UserDao userDao, UserToStaffDao userToStaffDao){
        this.staffDao = staffDao;
        this.userDao = userDao;
        this.userToStaffDao = userToStaffDao;
    }

    @Transactional
    @Override
    public int createStaff(StaffPO staffPO) {
        int affected = staffDao.createStaff(staffPO);
        String userName = staffPO.getName() + staffPO.getId();
        User user = new User(null, userName,"123456", staffPO.getPosition());
        userDao.createUser(user);
        int userId = user.getId();
        int staffId = staffPO.getId();
        userToStaffDao.insertRecord(userId, staffId);

        return affected;
    }

    @Override
    @Transactional
    public int deleteStaffById(Integer staffId) {
        int userId = userToStaffDao.findUserIdByStaffId(staffId);
        userDao.deleteById(userId);
        int affected = staffDao.deleteOneById(staffId);
        return affected;
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
    public StaffPO findStaffById(Integer staffId) {

        return staffDao.getStaffById(staffId);
    }

    @Override
    public void addBalanceByStaffId(BigDecimal amount, int staffId) {
        StaffPO staff = staffDao.getStaffById(staffId);
        staff.setBalance(staff.getBalance().add(amount));
        staffDao.updateOne(staff);
    }

    @Override
    public List<StaffPO> findAllExceptGM() {
        return staffDao.findAllExceptGM();
    }
}
