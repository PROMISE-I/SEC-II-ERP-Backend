package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.service.SalaryService;
import com.nju.edu.erp.service.StaffService;
import com.nju.edu.erp.utils.DateHelper;
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

    private final StaffService staffService;

    @Autowired
    public SalaryController(SalaryService salaryService, StaffService staffService) {
        this.salaryService = salaryService;
        this.staffService = staffService;
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
        Role employeeRole = staffService.getRoleByEmployeeId(employeeId);
        if (employeeRole == Role.GM) {
            //总经理一年制定一次工资单
            if (DateHelper.isGMSalaryDay()) {
                Date today = new Date();
                Date latest = salaryService.getLatestDateByEmployeeId(employeeId);
                if (DateHelper.isSameAboveMonth(latest, today)) {
                    salaryService.makeSalarySheet(employeeId, bankAccountId);
                    return Response.buildSuccess();
                } else {
                    return Response.buildFailed("B00000", "今年总经理工资单已经制定！");
                }
            } else {
                return Response.buildFailed("B00001", "本月非制定总经理工资单的月份！");
            }
        } else {
            //非总经理每月制定一次工资单
            if (DateHelper.isSalaryDay()) {
                Date today = new Date();
                Date latest = salaryService.getLatestDateByEmployeeId(employeeId);
                if (DateHelper.isSameAboveMonth(latest, today)) {
                    salaryService.makeSalarySheet(employeeId, bankAccountId);
                    return Response.buildSuccess();
                } else {
                    return Response.buildFailed("B00002", "本月该员工的工资单已经制定！");
                }
            } else {
                return Response.buildFailed("B00003", "本日非制定非总经理工资单的日期！");
            }
        }
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

    /**
     * 人力资源人员审批
     * @param salarySheetId 工资单id
     * @param state 修改后的状态("审批失败"/"待二级审批")
     */
    @Authorized(roles = {Role.HR, Role.ADMIN})
    @GetMapping("/first-approval")
    public Response firstApproval(@RequestParam(value = "salarySheetId") String salarySheetId,
                                  @RequestParam(value = "state") SalarySheetState state) {
        if (state.equals(SalarySheetState.FAILURE) || state.equals(SalarySheetState.PENDING_LEVEL_2)) {
            salaryService.approval(salarySheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000", "操作失败");
        }
    }

    /**
     * 总经理审批
     * @param salarySheetId 工资单id
     * @param state 修改后的状态("审批失败"/"审批完成")
     */
    @Authorized (roles = {Role.GM, Role.ADMIN})
    @GetMapping(value = "/second-approval")
    public Response secondApproval(@RequestParam(value = "salarySheetId") String salarySheetId,
                                   @RequestParam(value = "state") SalarySheetState state) {
        if (state.equals(SalarySheetState.FAILURE) || state.equals(SalarySheetState.SUCCESS)) {
            salaryService.approval(salarySheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000", "操作失败");
        }
    }


}
