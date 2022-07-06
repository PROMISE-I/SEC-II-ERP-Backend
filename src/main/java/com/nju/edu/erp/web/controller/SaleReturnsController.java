package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.sale.SaleIODetailFilterConditionVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetVO;
import com.nju.edu.erp.service.SaleReturnsService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping(path = "/sale-returns")
public class SaleReturnsController {

    private final SaleReturnsService saleReturnsService;

    @Autowired
    public SaleReturnsController(SaleReturnsService saleReturnsService){
        this.saleReturnsService = saleReturnsService;
    }

    /**
     * 销售人员制定销售退货单
     */
    @Authorized(roles = {Role.SALE_STAFF, Role.SALE_MANAGER, Role.GM, Role.ADMIN})
    @PostMapping(value = "sheet-make")
    public Response makeSaleOrder(UserVO userVO, @RequestBody SaleReturnsSheetVO saleReturnsSheetVO) {
        saleReturnsService.makeSaleReturnsSheet(userVO, saleReturnsSheetVO);
        return Response.buildSuccess();
    }

    /**
     * 销售经理审批
     * @param saleReturnsSheetId 销售退货单id
     * @param state 修改后的状态("审批失败"/"待二级审批")
     */
    @GetMapping(value = "/first-approval")
    @Authorized(roles = {Role.SALE_MANAGER, Role.GM, Role.ADMIN})
    public Response firstApproval(@RequestParam("saleReturnsSheetId") String saleReturnsSheetId,
                                  @RequestParam("state") SaleReturnsSheetState state) {
        if (state.equals(SaleReturnsSheetState.FAILURE) || state.equals(SaleReturnsSheetState.PENDING_LEVEL_2)){
            saleReturnsService.approval(saleReturnsSheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000", "操作失败");
        }
    }

    /**
     * 总经理审批
     * @param saleReturnsSheetId 销售退货单id
     * @param state 修改后的状态("审批失败"/"审批完成")
     */
    @Authorized (roles = {Role.GM, Role.ADMIN})
    @GetMapping(value = "/second-approval")
    public Response secondApproval(@RequestParam("saleReturnsSheetId") String saleReturnsSheetId,
                                   @RequestParam("state") SaleReturnsSheetState state)  {
        if(state.equals(SaleReturnsSheetState.FAILURE) || state.equals(SaleReturnsSheetState.SUCCESS)) {
            saleReturnsService.approval(saleReturnsSheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000","操作失败"); // code可能得改一个其他的
        }
    }

    /**
     * 根据状态查看销售退货单
     */
    @GetMapping(value = "/sheet-show")
    public Response showSheetByState(@RequestParam(value = "state", required = false) SaleReturnsSheetState state)  {
        return Response.buildSuccess(saleReturnsService.getSaleReturnsSheetByState(state));
    }

    /**
     * 查看销售明细表：根据筛选条件选择销售退货单对应的销售明细
     * @param condition 筛选条件
     * @return 销售退货单对应的销售明细
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.GM, Role.ADMIN})
    @PostMapping(value = "/saleReturnsDetail")
    public Response getSaleReturnsDetailByCondition(@RequestBody SaleIODetailFilterConditionVO condition) throws ParseException {
        return Response.buildSuccess(saleReturnsService.getSaleReturnsDetailByCondition(condition));
    }

    /**
     * 查看销售明细表：根据时间区间选择销售退货单对应的销售明细
     * @param beginTimeStr 开始时间字符串
     * @param endTimeStr 结束时间字符串
     * @return 销售退货单对应的销售明细
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.GM, Role.ADMIN})
    @GetMapping(value = "/saleReturnsDetail-byRange")
    public Response getSaleReturnsDetailByRange(@RequestParam(value = "beginTimeStr") String beginTimeStr,
                                                @RequestParam(value = "endTimeStr") String endTimeStr) throws ParseException {
        return Response.buildSuccess(saleReturnsService.getSaleReturnsDetailByRange(beginTimeStr, endTimeStr));
    }
}
