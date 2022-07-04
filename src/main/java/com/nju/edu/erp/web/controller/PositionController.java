package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.model.po.PositionInfoPO;
import com.nju.edu.erp.model.vo.PositionInfoVO;
import com.nju.edu.erp.service.PositionService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/position")
public class PositionController {
    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService){
        this.positionService = positionService;
    }

    @PostMapping("/update")
    public Response updatePositionInfo(@RequestBody PositionInfoVO positionInfoVO){
        PositionInfoPO positionInfoPO = new PositionInfoPO(positionInfoVO);
        positionService.updateOne(positionInfoPO);

        return Response.buildSuccess();
    }

    @GetMapping("/findAll")
    public Response findAll(){
        return Response.buildSuccess(positionService.findAll());
    }

    @GetMapping("/findByTitle")
    public Response findOneByTitle(@RequestParam String title){
        return Response.buildSuccess(positionService.findOneByTitle(title));
    }
}
