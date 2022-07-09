package com.nju.edu.erp.dao.staff;

import com.nju.edu.erp.model.po.staff.AttendanceRecordPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AttendanceDao {

    public int insertAttendanceRecord(AttendanceRecordPO attendanceRecordPO);

    public AttendanceRecordPO findByDayAndStaff(AttendanceRecordPO attendanceRecordPO);

    int getAttendanceTime(int staffId, int year, int month);
}
