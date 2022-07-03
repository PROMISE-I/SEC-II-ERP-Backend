package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.service.SalaryService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping(path = "/salary")
public class SalaryController {

    private final SalaryService salaryService;

    @Autowired
    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    /**
     * 制定工资单
     * @param employeeId 员工id
     * @param bankAccountId 员工银行账户id
     */
    @Authorized(roles = {Role.HR, Role.GM, Role.ADMIN})
    @GetMapping("/sheet-make")
    public Response makeSalarySheet(@RequestParam(value = "employeeId") int employeeId,
                                    @RequestParam(value = "bankAccountId") int bankAccountId) {
        Date today = new Date();
        //根据制定时间检查
        salaryService.makeSalarySheet(employeeId, bankAccountId);
        return Response.buildSuccess();
    }

    /**
     * 根据员工id返回当月的薪资
     * @param employeeId 员工id
     */
    @GetMapping("/get-salary")
    public Response getSalaryByEmployeeId(@RequestParam(value = "employeeId") int employeeId) {
        return Response.buildSuccess(salaryService.getSalaryByEmployeeId(employeeId));
    }

    /**
     * 根据状态返回相应的工资单
     * @param state 工资单状态
     */
    @GetMapping("/sheet-show")
    public Response showSheetByState(@RequestParam(value = "state") SalarySheetState state) {
        return Response.buildSuccess(salaryService.getSalarySheetByState(state));
    }
}
