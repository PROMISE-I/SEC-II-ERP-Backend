package com.nju.edu.erp.web.controller.sheets_counting;

import com.nju.edu.erp.model.vo.sheets_counting.OperateSheetVO;
import com.nju.edu.erp.service.Interface.sheets_counting.OperateSheetService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operate-sheet")
public class OperateSheetController {
    private final OperateSheetService operateSheetService;

    @Autowired
    public OperateSheetController(OperateSheetService operateSheetService){
        this.operateSheetService = operateSheetService;
    }

    @GetMapping("/show")
    public Response showSheet(@RequestParam("begin") String begin, @RequestParam("end")String end){
        OperateSheetVO operateSheetVO = new OperateSheetVO();

        operateSheetVO.setBeginDateStr(begin);
        operateSheetVO.setEndDateStr(end);
        operateSheetVO.setFinalIncome(operateSheetService.calculateFinalIncome(begin, end));
        operateSheetVO.setDiscountedAmount(operateSheetService.calculateDiscountedAmount(begin, end));
        operateSheetVO.setCost(operateSheetService.calculateCost(begin, end));
        operateSheetVO.setProfit(operateSheetService.calculateProfit(begin, end));

        return Response.buildSuccess(operateSheetVO);
    }
}
