package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.model.vo.BusinessHistorySheetVO;
import com.nju.edu.erp.service.*;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/business-history")
public class BusinessHistoryController {

    private final BusinessHistoryService businessHistoryService;

    private final SaleService saleService;

    private final SaleReturnsService saleReturnsService;

    private final PurchaseService purchaseService;

    private final PurchaseReturnsService purchaseReturnsService;

    private final PayMoneyService payMoneyService;

    private final ReceiveMoneyService receiveMoneyService;

    private final SalaryService salaryService;

    @Autowired
    public BusinessHistoryController(BusinessHistoryService businessHistoryService, SaleService saleService, SaleReturnsService saleReturnsService, PurchaseService purchaseService, PurchaseReturnsService purchaseReturnsService, PayMoneyService payMoneyService, ReceiveMoneyService receiveMoneyService, SalaryService salaryService){
        this.businessHistoryService = businessHistoryService;
        this.saleService = saleService;
        this.saleReturnsService = saleReturnsService;
        this.purchaseService = purchaseService;
        this.purchaseReturnsService = purchaseReturnsService;
        this.payMoneyService = payMoneyService;
        this.receiveMoneyService = receiveMoneyService;
        this.salaryService = salaryService;
    }

    @GetMapping("/show")
    public Response showSheet(@RequestParam("begin") String begin, @RequestParam("end") String end){

        List<BusinessHistorySheetVO> lst = businessHistoryService.findAll(begin, end);

        return Response.buildSuccess(lst);
    }

    /**
     * 查询这个编号的单据是否已经存在
     * @param sheetId 单据编号
     */
    @GetMapping("/sheet-exists")
    public Response isSheetExists(@RequestParam("sheetId") String sheetId) {
        String prefix = sheetId.split("-")[0];
        switch (prefix) {
            case "XSD":
                return Response.buildSuccess(saleService.isSheetExists(sheetId));
            case "XSTHD":
                return Response.buildSuccess(saleReturnsService.isSheetExists(sheetId));
            case "JHD":
                return Response.buildSuccess(purchaseService.isSheetExists(sheetId));
            case "JHTHD":
                return Response.buildSuccess(purchaseReturnsService.isSheetExists(sheetId));
            case "FKD":
                return Response.buildSuccess(payMoneyService.isSheetExists(sheetId));
            case "SKD":
                return Response.buildSuccess(receiveMoneyService.isSheetExists(sheetId));
            case "GZD":
                return Response.buildSuccess(salaryService.isSheetExists(sheetId));
            default:
                return Response.buildFailed("E00000", "单据编号格式错误");
        }
    }
}
