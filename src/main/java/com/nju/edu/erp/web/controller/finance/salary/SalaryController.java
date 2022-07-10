package com.nju.edu.erp.web.controller.finance.salary;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.user.Role;
import com.nju.edu.erp.enums.sheet_state.finance.SalarySheetState;
import com.nju.edu.erp.model.po.staff.PositionInfoPO;
import com.nju.edu.erp.model.vo.finance.salary.SalarySheetVO;
import com.nju.edu.erp.service.Interface.staff.PositionService;
import com.nju.edu.erp.service.Interface.finance.salary.SalaryService;
import com.nju.edu.erp.service.Interface.staff.StaffService;
import com.nju.edu.erp.utils.DateHelper;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


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
     * 红冲功能的单据制定
     * @param salarySheetVO 单据内容
     */
    @Authorized(roles = {Role.HR, Role.GM, Role.ADMIN})
    @PostMapping("/red-ink-offset-sheet-make")
    public Response makeSalarySheet(@RequestBody SalarySheetVO salarySheetVO) {
        salaryService.redInkOffsetSheetMake(salarySheetVO);
        return Response.buildSuccess();
    }

    /**
     * 制定工资单
     * @param employeeId 员工id
     * @param bankAccountId 员工银行账户id
     */
    //EXPORT: 指定工资单
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
                if (latest == null || !DateHelper.isSameYear(latest, today)) {
                    salaryService.makeSalarySheet(employeeId, bankAccountId);
                    return Response.buildSuccess();
                } else {
                    return Response.buildFailed("B00000", "去年总经理工资单已经制定！");
                }
            case MONTHLY:
                //非总经理每月制定一次工资单，每年制定的是去年的工资单
                today = new Date();
                latest = salaryService.getLatestDateByEmployeeId(employeeId);
                if (latest == null || !DateHelper.isSameYearAndMonth(latest, today)) {
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
    //EXPORT: 工资单一级审批
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
    //EXPORT: 工资单二级审批
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


    /**
     * 返回去年前11个月份的原始工资总额
     * @param staffId 员工编号
     * @return 原始工资总额
     */
    @GetMapping("/get-total-amount")
    public Response getTotalAmountBeforeNovember(int staffId) {
        return Response.buildSuccess(salaryService.getTotalAmountBeforeNovember(staffId));
    }
}
