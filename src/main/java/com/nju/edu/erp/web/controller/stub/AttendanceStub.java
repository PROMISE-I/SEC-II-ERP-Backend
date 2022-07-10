package com.nju.edu.erp.web.controller.stub;

import com.nju.edu.erp.model.po.staff.AttendanceRecordPO;
import com.nju.edu.erp.model.vo.staff.AttendanceRecordVO;
import com.nju.edu.erp.web.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 暂时替代 AttendanceController 的桩程序
 */
@RequestMapping("/stub-attendance")
@RestController
public class AttendanceStub {

    @PostMapping("/clockIn")
    public Response clockIn(@RequestBody AttendanceRecordVO attendanceRecordVO){

        return Response.buildSuccess();
    }

    @PostMapping("/check")
    public Response checkAttendance(@RequestBody AttendanceRecordVO attendanceRecordVO){

        return Response.buildSuccess(Boolean.TRUE);
    }
}
