package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.model.po.PositionInfoPO;
import com.nju.edu.erp.service.PositionService;
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

import static com.nju.edu.erp.enums.SalarySendType.ANNUALLY;


@RestController
@RequestMapping(path = "/salary")
public class SalaryController {

    private final SalaryService salaryService;

    private final StaffService staffService;

    private final PositionService positionService;

    @Autowired
    public SalaryController(SalaryService salaryService, StaffService staffService, PositionService positionService) {
        this.salaryService = salaryService;
        this.staffService = staffService;
        this.positionService = positionService;
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
        PositionInfoPO position = positionService.findOneByTitle(employeeRole);
        switch(position.getSalaryPaymentMethod()){
            case ANNUALLY:
                //总经理一年制定一次工资单，每年制定的是去年的工资单
                Date today = new Date();
                Date latest = salaryService.getLatestDateByEmployeeId(employeeId);
                if (DateHelper.isSameYear(latest, today)) {
                    salaryService.makeSalarySheet(employeeId, bankAccountId);
                    return Response.buildSuccess();
                } else {
                    return Response.buildFailed("B00000", "去年总经理工资单已经制定！");
                }
            case MONTHLY:
                //非总经理每月制定一次工资单，每年制定的是去年的工资单
                today = new Date();
                latest = salaryService.getLatestDateByEmployeeId(employeeId);
                if (DateHelper.isSameYearAndMonth(latest, today)) {
                    salaryService.makeSalarySheet(employeeId, bankAccountId);
                    return Response.buildSuccess();
                } else {
                    return Response.buildFailed("B00001", "上个月该员工的工资单已经制定！");
                }
            default:
                return Response.buildFailed("B00002", "该员工的薪资发放方式有误，请联系管理员！");
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
            return Response.buildFailed("000000", "操作失败");//TODO change error code!
        }
    }

    /**
     * 获得所有工资单
     */
    @GetMapping("/get-all-salary-sheets")
    public Response findAllSalarySheet() {
        return Response.buildSuccess(salaryService.findAllSalarySheets());
    }
}
