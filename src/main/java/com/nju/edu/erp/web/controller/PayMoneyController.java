package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.PayMoneySheetState;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.finance.PayMoneySheetVO;
import com.nju.edu.erp.service.PayMoneyService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/payMoney")
public class PayMoneyController {
    private final PayMoneyService payMoneyService;

    @Autowired
    public PayMoneyController(PayMoneyService payMoneyService) {
        this.payMoneyService = payMoneyService;
    }

    /**
     * 财务人员制定付款单
     * @param userVO 操作员
     * @param payMoneySheetVO 付款单
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.GM, Role.ADMIN})
    @PostMapping("/sheet-make")
    public Response makePayMoneySheet(UserVO userVO, PayMoneySheetVO payMoneySheetVO) {
        payMoneyService.makePayMoneySheet(userVO, payMoneySheetVO);
        return Response.buildSuccess();
    }

    /**
     * 根据状态查看付款单
     * @param state 付款单状态
     */
    @GetMapping("/sheet-show")
    public Response showSheetByState(@RequestParam(value = "state", required = false) PayMoneySheetState state) {
        return Response.buildSuccess(payMoneyService.getPayMoneySheetByState(state));
    }

    /**
     * 财务人员审批
     * @param payMoneySheetId 付款单id
     * @param state 修改后的状态("审批失败"/"待二级审批")
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @GetMapping("/first-approval")
    public Response firstApproval(@RequestParam("payMoneySheetId") String payMoneySheetId,
                                  @RequestParam("state") PayMoneySheetState state) {
        if (state.equals(PayMoneySheetState.FAILURE) || state.equals(PayMoneySheetState.PENDING_LEVEL_2)) {
            payMoneyService.approval(payMoneySheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000", "操作失败");
        }
    }

    /**
     * 总经理审批
     * @param payMoneySheetId 付款单id
     * @param state 修改后的状态("审批失败"/"审批完成")
     */
    @Authorized(roles = {Role.GM, Role.ADMIN})
    @GetMapping("/second-approval")
    public Response secondApproval(@RequestParam("payMoneySheetId") String payMoneySheetId,
                                   @RequestParam("state") PayMoneySheetState state) {
        if (state.equals(PayMoneySheetState.FAILURE) || state.equals(PayMoneySheetState.SUCCESS)) {
            payMoneyService.approval(payMoneySheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000", "操作失败");
        }
    }

    /**
     * 获取所有的付款单
     */
    @GetMapping("/find-all-sheets")
    public Response findAllSheets() {
        return Response.buildSuccess(payMoneyService.findAllSheets());
    }
}
