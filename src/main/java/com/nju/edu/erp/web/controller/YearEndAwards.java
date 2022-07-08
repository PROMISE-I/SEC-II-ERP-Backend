package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.web.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WFS
 * @date 2022/7/7 19:17
 */
@RestController
@RequestMapping(path = "/year-end-awards")
public class YearEndAwards {

//    private YearEndAwardsService yearEndAwardsService;

    @PostMapping("/sheet-make")
    public Response makeSheet() {
        return Response.buildSuccess();
    }
}
