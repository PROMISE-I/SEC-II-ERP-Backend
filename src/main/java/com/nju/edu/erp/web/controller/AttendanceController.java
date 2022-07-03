package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.model.po.AttendanceRecordPO;
import com.nju.edu.erp.model.vo.AttendanceRecordVO;
import com.nju.edu.erp.service.AttendanceService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService){
        this.attendanceService = attendanceService;
    }

    @PostMapping("/clockIn")
    public Response clockIn(@RequestBody AttendanceRecordVO attendanceRecordVO){
        Integer staffId = attendanceRecordVO.getStaffId();
        String date = attendanceRecordVO.getDate();

        AttendanceRecordPO attendanceRecordPO = new AttendanceRecordPO(staffId, date);
        attendanceService.insertAttendanceRecord(attendanceRecordPO);
        return Response.buildSuccess();
    }

    @PostMapping("/check")
    public Response checkAttendance(@RequestBody AttendanceRecordVO attendanceRecordVO){
        Integer staffId = attendanceRecordVO.getStaffId();
        String date = attendanceRecordVO.getDate();

        AttendanceRecordPO attendanceRecordPO = new AttendanceRecordPO(staffId, date);
        return Response.buildSuccess(attendanceService.checkAttendance(attendanceRecordPO));
    }
}
