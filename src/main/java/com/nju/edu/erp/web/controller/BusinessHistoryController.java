package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.model.vo.BusinessHistoryQueryVO;
import com.nju.edu.erp.model.vo.BusinessHistorySheetVO;
import com.nju.edu.erp.service.BusinessHistoryService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business-history")
public class BusinessHistoryController {

    private final BusinessHistoryService businessHistoryService;

    @Autowired
    public BusinessHistoryController(BusinessHistoryService businessHistoryService){
        this.businessHistoryService = businessHistoryService;
    }

    @PostMapping("/show")
    public Response showSheet(@RequestBody BusinessHistoryQueryVO businessHistoryQueryVO){

        List<BusinessHistorySheetVO> lst = businessHistoryService.findAll(businessHistoryQueryVO);

        return Response.buildSuccess(lst);
    }
}
