package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.web.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business-history")
public class BusinessHistoryController {

    @GetMapping("/show")
    public Response showSheet(@RequestParam("begin") String begin, @RequestParam("end") String end, @RequestParam("type") String type){
        return Response.buildSuccess();
    }
}
