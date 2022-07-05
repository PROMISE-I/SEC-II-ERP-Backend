package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.ReceiveMoneySheetState;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.finance.ReceiveMoneySheetVO;
import com.nju.edu.erp.service.ReceiveMoneyService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/receiveMoney")
public class ReceiveMoneyController {

    private final ReceiveMoneyService receiveMoneyService;

    @Autowired
    public ReceiveMoneyController(ReceiveMoneyService receiveMoneyService) {
        this.receiveMoneyService = receiveMoneyService;
    }

    /**
     * 财务人员制定收款单
     * @param userVO 操作员
     * @param receiveMoneySheetVO 收款单
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.GM, Role.ADMIN})
    @PostMapping("/sheet-make")
    public Response makeReceiveMoneySheet(UserVO userVO, ReceiveMoneySheetVO receiveMoneySheetVO) {
        receiveMoneyService.makeReceiveMoneySheet(userVO, receiveMoneySheetVO);
        return Response.buildSuccess();
    }

    /**
     * 根据状态查看收款单
     * @param state 收款单状态
     */
    @GetMapping("/sheet-show")
    public Response showSheetByState(@RequestParam(value = "state", required = false) ReceiveMoneySheetState state) {
        return Response.buildSuccess(receiveMoneyService.getReceiveMoneySheetByState(state));
    }

    /**
     * 财务人员审批
     * @param receiveMoneySheetId 收款单id
     * @param state 修改后的状态("审批失败"/"待二级审批")
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.ADMIN})
    @GetMapping("/first-approval")
    public Response firstApproval(@RequestParam("receiveMoneySheetId") String receiveMoneySheetId,
                                  @RequestParam("state") ReceiveMoneySheetState state) {
        if (state.equals(ReceiveMoneySheetState.FAILURE) || state.equals(ReceiveMoneySheetState.PENDING_LEVEL_2)) {
            receiveMoneyService.approval(receiveMoneySheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000", "操作失败");
        }
    }

    /**
     * 总经理审批
     * @param receiveMoneySheetId 收款单id
     * @param state 修改后的状态("审批失败"/"审批完成")
     */
    @Authorized(roles = {Role.GM, Role.ADMIN})
    @GetMapping("/second-approval")
    public Response secondApproval(@RequestParam("receiveMoneySheetId") String receiveMoneySheetId,
                                  @RequestParam("state") ReceiveMoneySheetState state) {
        if (state.equals(ReceiveMoneySheetState.FAILURE) || state.equals(ReceiveMoneySheetState.SUCCESS)) {
            receiveMoneyService.approval(receiveMoneySheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000", "操作失败");
        }
    }

    /**
     * 获取所有的收款单
     */
    @GetMapping("find-all-sheets")
    public Response findAllSheets() {
        return Response.buildSuccess(receiveMoneyService.findAllSheet());
    }
}
