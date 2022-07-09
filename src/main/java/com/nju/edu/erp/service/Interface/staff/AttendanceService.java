package com.nju.edu.erp.service.Interface.staff;

import com.nju.edu.erp.model.po.staff.AttendanceRecordPO;


public interface AttendanceService {

    public int insertAttendanceRecord(AttendanceRecordPO attendanceRecordPO);

    public Boolean checkAttendance(AttendanceRecordPO attendanceRecordPO);

    public int getAttendanceTime(int staffId, int year, int month);
}
