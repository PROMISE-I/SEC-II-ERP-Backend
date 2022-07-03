package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.StaffDao;
import com.nju.edu.erp.dao.UserDao;
import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.model.po.User;
import com.nju.edu.erp.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
