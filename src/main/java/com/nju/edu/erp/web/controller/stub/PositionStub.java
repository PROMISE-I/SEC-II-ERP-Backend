package com.nju.edu.erp.web.controller.stub;

import com.nju.edu.erp.enums.salary_strategy.SalaryCalculateType;
import com.nju.edu.erp.enums.salary_strategy.SalarySendType;
import com.nju.edu.erp.enums.user.Role;
import com.nju.edu.erp.model.po.staff.PositionInfoPO;
import com.nju.edu.erp.model.vo.staff.PositionInfoVO;
import com.nju.edu.erp.web.Response;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stub-position")
public class PositionStub {
    @PostMapping("/update")
    public Response updatePositionInfo(@RequestBody PositionInfoVO positionInfoVO){
        return Response.buildSuccess();
    }

    @GetMapping("/findAll")
    public Response findAll(){
        PositionInfoPO po = PositionInfoPO.builder()
                .level(1)
                .baseSalary(BigDecimal.valueOf(1000.00))
                .salaryCalculateMethod(SalaryCalculateType.COMMISSION_STAFF_PAY)
                .title(Role.FINANCIAL_STAFF)
                .salaryPaymentMethod(SalarySendType.MONTHLY)
                .specialSalary(BigDecimal.valueOf(10000.00))
                .tax(BigDecimal.valueOf(0.10))
                .build();

        List<PositionInfoPO> lst = new ArrayList<>();
        lst.add(po);

        return Response.buildSuccess(lst);
    }

    @GetMapping("/findByTitle")
    public Response findOneByTitle(@RequestParam Role title){
        PositionInfoPO po = PositionInfoPO.builder()
                .level(1)
                .baseSalary(BigDecimal.valueOf(1000.00))
                .salaryCalculateMethod(SalaryCalculateType.COMMISSION_STAFF_PAY)
                .title(Role.FINANCIAL_STAFF)
                .salaryPaymentMethod(SalarySendType.MONTHLY)
                .specialSalary(BigDecimal.valueOf(10000.00))
                .tax(BigDecimal.valueOf(0.10))
                .build();
        return Response.buildSuccess(po);
    }
}
