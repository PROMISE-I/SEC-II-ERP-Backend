package com.nju.edu.erp.service;

import com.nju.edu.erp.model.po.AttendanceRecordPO;
import org.springframework.stereotype.Service;


public interface AttendanceService {

    public int insertAttendanceRecord(AttendanceRecordPO attendanceRecordPO);

    public Boolean checkAttendance(AttendanceRecordPO attendanceRecordPO);

}
