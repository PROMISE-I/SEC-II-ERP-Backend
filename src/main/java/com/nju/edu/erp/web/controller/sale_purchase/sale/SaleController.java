package com.nju.edu.erp.web.controller.sale_purchase.sale;


import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.user.Role;
import com.nju.edu.erp.enums.sheet_state.sale.SaleSheetState;
import com.nju.edu.erp.model.po.sale_purchase.customer.CustomerPurchaseAmountPO;
import com.nju.edu.erp.model.vo.sale_purchase.sale.io_detail.SaleIODetailFilterConditionVO;
import com.nju.edu.erp.model.vo.sale_purchase.sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.user.UserVO;
import com.nju.edu.erp.service.Interface.sale_purchase.sale.SaleService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping(path = "/sale")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    /**
     * 销售人员制定销售单
     */
    @Authorized (roles = {Role.SALE_STAFF, Role.SALE_MANAGER, Role.GM, Role.ADMIN})
    @PostMapping(value = "/sheet-make")
    public Response makeSaleOrder(UserVO userVO, @RequestBody SaleSheetVO saleSheetVO)  {
        saleService.makeSaleSheet(userVO, saleSheetVO);
        return Response.buildSuccess();
    }

    /**
     * 根据状态查看销售单
     */
    @GetMapping(value = "/sheet-show")
    public Response showSheetByState(@RequestParam(value = "state", required = false) SaleSheetState state)  {
        return Response.buildSuccess(saleService.getSaleSheetByState(state));
    }

    /**
     * 销售经理审批
     * @param saleSheetId 进货单id
     * @param state 修改后的状态("审批失败"/"待二级审批")
     */
    @GetMapping(value = "/first-approval")
    @Authorized (roles = {Role.SALE_MANAGER, Role.ADMIN})
    public Response firstApproval(@RequestParam("saleSheetId") String saleSheetId,
                                  @RequestParam("state") SaleSheetState state)  {
        if(state.equals(SaleSheetState.FAILURE) || state.equals(SaleSheetState.PENDING_LEVEL_2)) {
            saleService.approval(saleSheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000","操作失败"); // code可能得改一个其他的
        }
    }

    /**
     * 总经理审批
     * @param saleSheetId 进货单id
     * @param state 修改后的状态("审批失败"/"审批完成")
     */
    @Authorized (roles = {Role.GM, Role.ADMIN})
    @GetMapping(value = "/second-approval")
    public Response secondApproval(@RequestParam("saleSheetId") String saleSheetId,
                                   @RequestParam("state") SaleSheetState state)  {
        if(state.equals(SaleSheetState.FAILURE) || state.equals(SaleSheetState.SUCCESS)) {
            saleService.approval(saleSheetId, state);
            return Response.buildSuccess();
        } else {
            return Response.buildFailed("000000","操作失败"); // code可能得改一个其他的
        }
    }

    /**
     * 获取某个销售人员某段时间内消费总金额最大的客户(不考虑退货情况,销售单不需要审批通过,如果这样的客户有多个,仅保留一个)
     * @param salesman 销售人员的名字
     * @param beginDateStr 开始时间字符串 格式：“yyyy-MM-dd HH:mm:ss”，如“2022-05-12 11:38:30”
     * @param endDateStr 结束时间字符串 格式：“yyyy-MM-dd HH:mm:ss”，如“2022-05-12 11:38:30”
     * @return
     */
    @GetMapping("/maxAmountCustomer")
    @Authorized(roles = {Role.SALE_MANAGER,Role.GM, Role.ADMIN})
    public Response getMaxAmountCustomerOfSalesmanByTime(@RequestParam String salesman, @RequestParam String beginDateStr, @RequestParam String endDateStr){
        CustomerPurchaseAmountPO ans=saleService.getMaxAmountCustomerOfSalesmanByTime(salesman,beginDateStr,endDateStr);
        return Response.buildSuccess(ans);
    }

    /**
     * 根据销售单Id搜索销售单信息
     * @param id 销售单Id
     * @return 销售单全部信息
     */
    @GetMapping(value = "/find-sheet")
    public Response findBySheetId(@RequestParam(value = "id") String id)  {
        return Response.buildSuccess(saleService.getSaleSheetById(id));
    }

    /**
     * 查看销售明细表：根据筛选条件选择销售单对应的销售明细
     * @param condition 筛选条件
     * @return 销售单对应的销售明细
     */
    //EXPORT: 查看销售明细表
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.GM, Role.ADMIN})
    @PostMapping(value = "/saleDetail")
    public Response getSaleDetailByCondition(@RequestBody SaleIODetailFilterConditionVO condition) throws ParseException {
        return Response.buildSuccess(saleService.getSaleDetailByCondition(condition));
    }

    /**
     * 查看销售明细表：根据时间区间选择销售单对应的销售明细
     * @param beginTimeStr 开始时间字符串
     * @param endTimeStr 结束时间字符串
     * @return 销售单对应的销售明细
     */
    @Authorized(roles = {Role.FINANCIAL_STAFF, Role.GM, Role.ADMIN})
    @GetMapping(value = "/saleDetail-byRange")
    public Response getSaleDetailByRange(@RequestParam(value = "beginTimeStr") String beginTimeStr,
                                         @RequestParam(value = "endTimeStr") String endTimeStr) throws ParseException {
        return Response.buildSuccess(saleService.getSaleDetailByRange(beginTimeStr, endTimeStr));
    }

}
