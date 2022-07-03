package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.model.vo.BusinessHistorySheetVO;
import com.nju.edu.erp.model.vo.sale.SaleSheetVO;
import com.nju.edu.erp.web.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/business-history")
public class BusinessHistoryController {

    @GetMapping("/show")
    public Response showSheet(@RequestParam("begin") String begin, @RequestParam("end") String end){
        //TODO: MOCK NOW, TO BE IMPLEMENTED
        List<BusinessHistorySheetVO> lst = new ArrayList<>();
        lst.add(new BusinessHistorySheetVO("sale-sheet", new SaleSheetVO()));
        return Response.buildSuccess(lst);
    }
}
