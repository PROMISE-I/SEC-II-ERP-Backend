package com.nju.edu.erp.service;

import com.nju.edu.erp.model.po.staff.AttendanceRecordPO;
import com.nju.edu.erp.service.Interface.staff.AttendanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AttendanceServiceTest {
    @Autowired
    AttendanceService attendanceService;

    @Test
    @Transactional
    public void clockInTest1(){
        //创造一条打卡记录
        AttendanceRecordPO attendanceRecordPO = new AttendanceRecordPO(1, "2022-07-09");
        attendanceService.insertAttendanceRecord(attendanceRecordPO);
        //应该打卡成功
        assert(attendanceService.checkAttendance(attendanceRecordPO));
    }


    @Test
    @Transactional
    public void clockInTest2(){
        //创造一条打卡记录
        AttendanceRecordPO attendanceRecordPO = new AttendanceRecordPO(1, "2022-07-09");
        attendanceService.insertAttendanceRecord(attendanceRecordPO);
        attendanceRecordPO.setDate("2022-08-09");
        //查询一个未打卡的日期,应该打卡失败
        assert(!attendanceService.checkAttendance(attendanceRecordPO));
    }
}
