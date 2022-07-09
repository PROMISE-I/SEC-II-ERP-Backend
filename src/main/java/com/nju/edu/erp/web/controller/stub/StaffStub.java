package com.nju.edu.erp.web.controller.stub;

import com.nju.edu.erp.enums.user.Role;
import com.nju.edu.erp.model.vo.staff.StaffVO;
import com.nju.edu.erp.web.Response;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stub-staff")
public class StaffStub {

    @PostMapping("/create")
    public Response createStaff(@RequestBody StaffVO staffVO){

        return Response.buildSuccess();
    }


    @GetMapping("/findAll")
    public Response findAll(){
        StaffVO staffVO = StaffVO.builder()
                .id(1)
                .name("sky")
                .phone("123")
                .balance(BigDecimal.TEN)
                .gender("男")
                .birthday("2002-09-09")
                .position(Role.FINANCIAL_STAFF)
                .build();
        List<StaffVO> lst = new ArrayList<>();
        lst.add(staffVO);
        return Response.buildSuccess(lst);
    }


    @GetMapping("/findAllExceptGM")
    public Response findAllExceptGM() {
        StaffVO staffVO = StaffVO.builder()
                .id(1)
                .name("67")
                .phone("1234")
                .balance(BigDecimal.TEN)
                .gender("男")
                .birthday("2002-09-07")
                .position(Role.FINANCIAL_STAFF)
                .build();
        List<StaffVO> lst = new ArrayList<>();
        lst.add(staffVO);
        return Response.buildSuccess(lst);
    }


    @PostMapping("/update")
    public Response updateOne(@RequestBody StaffVO staffVO){

        return Response.buildSuccess();
    }

    @GetMapping("/delete")
    public Response deleteById(@RequestParam Integer id){

        return Response.buildSuccess();
    }

    @GetMapping("/findById")
    public Response findById(@RequestParam Integer staffId){
        StaffVO staffVO = StaffVO.builder()
                .id(1)
                .name("sky")
                .phone("123")
                .balance(BigDecimal.TEN)
                .gender("男")
                .birthday("2002-09-09")
                .position(Role.FINANCIAL_STAFF)
                .build();
        return Response.buildSuccess(staffVO);
    }
}
