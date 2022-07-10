package com.nju.edu.erp.web.controller.finance.sheets_counting;

import com.nju.edu.erp.model.vo.finance.sheets_counting.BusinessHistoryQueryVO;
import com.nju.edu.erp.model.vo.finance.sheets_counting.BusinessHistorySheetVO;
import com.nju.edu.erp.service.Interface.finance.PayMoneyService;
import com.nju.edu.erp.service.Interface.finance.ReceiveMoneyService;
import com.nju.edu.erp.service.Interface.sale_purchase.purchase.PurchaseReturnsService;
import com.nju.edu.erp.service.Interface.sale_purchase.purchase.PurchaseService;
import com.nju.edu.erp.service.Interface.sale_purchase.sale.SaleReturnsService;
import com.nju.edu.erp.service.Interface.sale_purchase.sale.SaleService;
import com.nju.edu.erp.service.Interface.finance.salary.SalaryService;
import com.nju.edu.erp.service.Interface.finance.sheets_counting.BusinessHistoryService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //EXPORT: 查看经营里程表
    @PostMapping("/show")
    public Response showSheet(@RequestBody BusinessHistoryQueryVO businessHistoryQueryVO){

        List<BusinessHistorySheetVO> lst = businessHistoryService.findAll(businessHistoryQueryVO);

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
