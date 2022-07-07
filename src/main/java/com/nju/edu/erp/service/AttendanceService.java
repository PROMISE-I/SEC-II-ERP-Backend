package com.nju.edu.erp.service;

import com.nju.edu.erp.model.po.AttendanceRecordPO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


public interface AttendanceService {

    public int insertAttendanceRecord(AttendanceRecordPO attendanceRecordPO);

    public Boolean checkAttendance(AttendanceRecordPO attendanceRecordPO);

    public int getAttendanceTime(int staffId, int year, int month);
}
