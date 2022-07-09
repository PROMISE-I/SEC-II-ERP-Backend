package com.nju.edu.erp.service.Impl.staff;

import com.nju.edu.erp.dao.staff.AttendanceDao;
import com.nju.edu.erp.model.po.staff.AttendanceRecordPO;
import com.nju.edu.erp.service.Interface.staff.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceDao attendanceDao;

    @Autowired
    public AttendanceServiceImpl(AttendanceDao attendanceDao){
        this.attendanceDao = attendanceDao;
    }
    @Override
    public int insertAttendanceRecord(AttendanceRecordPO attendanceRecordPO) {
        return attendanceDao.insertAttendanceRecord(attendanceRecordPO);
    }

    @Override
    public Boolean checkAttendance(AttendanceRecordPO attendanceRecordPO) {
        //根据日期和员工号查询的结果不为 null 则说明已经打卡成功
        return attendanceDao.findByDayAndStaff(attendanceRecordPO) != null;
    }

    @Override
    public int getAttendanceTime(int staffId, int year, int month) {
        return attendanceDao.getAttendanceTime(staffId, year, month);
    }
}
