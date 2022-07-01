package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.model.po.StaffPO;
import com.nju.edu.erp.model.vo.StaffVO;
import com.nju.edu.erp.service.StaffService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService){
        this.staffService = staffService;
    }

    @PostMapping("/create")
    public Response createStaff(@RequestBody StaffVO staffVO){
        StaffPO staffPO = new StaffPO(staffVO);

        staffService.createStaff(staffPO);
        return Response.buildSuccess();
    }

    @GetMapping("/findAll")
    public Response findAll(){

        return Response.buildSuccess(staffService.findAll());
    }

    @PostMapping("/update")
    public Response updateOne(@RequestBody StaffVO staffVO){
        StaffPO staffPO = new StaffPO(staffVO);

        staffService.updateOne(staffPO);

        return Response.buildSuccess();
    }

    @GetMapping("/delete")
    public Response deleteById(@RequestParam Integer id){
        staffService.deleteStaffById(id);

        return Response.buildSuccess();
    }
}
