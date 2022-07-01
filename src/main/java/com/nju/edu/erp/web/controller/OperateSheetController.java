package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.web.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operate-sheet")
public class OperateSheetController {

    @GetMapping("/show")
    public Response showSheet(String begin, String end){

        return Response.buildSuccess();
    }
}
