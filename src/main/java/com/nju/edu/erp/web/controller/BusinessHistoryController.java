package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.model.vo.BusinessHistorySheetVO;
import com.nju.edu.erp.service.BusinessHistoryService;
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

    @Autowired
    public BusinessHistoryController(BusinessHistoryService businessHistoryService){
        this.businessHistoryService = businessHistoryService;
    }

    @GetMapping("/show")
    public Response showSheet(@RequestParam("begin") String begin, @RequestParam("end") String end){

        List<BusinessHistorySheetVO> lst = businessHistoryService.findAll(begin, end);

        return Response.buildSuccess(lst);
    }
}
