package com.nju.edu.erp.web.controller.staff;

import com.nju.edu.erp.model.po.staff.AttendanceRecordPO;
import com.nju.edu.erp.model.vo.staff.AttendanceRecordVO;
import com.nju.edu.erp.service.Interface.staff.AttendanceService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService){
        this.attendanceService = attendanceService;
    }

    //EXPORT: 员工打卡
    @PostMapping("/clockIn")
    public Response clockIn(@RequestBody AttendanceRecordVO attendanceRecordVO){
        Integer staffId = attendanceRecordVO.getStaffId();
        String date = attendanceRecordVO.getDate();

        AttendanceRecordPO attendanceRecordPO = new AttendanceRecordPO(staffId, date);
        attendanceService.insertAttendanceRecord(attendanceRecordPO);
        return Response.buildSuccess();
    }

    //EXPORT: 查询员工是否打卡
    @PostMapping("/check")
    public Response checkAttendance(@RequestBody AttendanceRecordVO attendanceRecordVO){
        Integer staffId = attendanceRecordVO.getStaffId();
        String date = attendanceRecordVO.getDate();

        AttendanceRecordPO attendanceRecordPO = new AttendanceRecordPO(staffId, date);
        return Response.buildSuccess(attendanceService.checkAttendance(attendanceRecordPO));
    }
}
