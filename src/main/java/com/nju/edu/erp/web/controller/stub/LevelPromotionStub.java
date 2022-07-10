package com.nju.edu.erp.web.controller.stub;

import com.nju.edu.erp.model.vo.promotion.level.LevelPromotionStrategyVO;
import com.nju.edu.erp.web.Response;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/stub-level-promotion")
public class LevelPromotionStub {
    @GetMapping("/findAll")
    public Response findAll(){
        LevelPromotionStrategyVO levelPromotionStrategyVO = LevelPromotionStrategyVO.builder()
                .id(1)
                .level(1)
                .beginDate("2022-07-01")
                .endDate("2022-08-01")
                .coupon(BigDecimal.valueOf(1000.00))
                .discount(BigDecimal.valueOf(0.80))
                .build();
        List<LevelPromotionStrategyVO> lst = new ArrayList<>();
        lst.add(levelPromotionStrategyVO);
        return Response.buildSuccess(lst);
    }


    @GetMapping("/findByLevel")
    public Response findByLevel(@RequestParam Integer level){
        LevelPromotionStrategyVO levelPromotionStrategyVO = LevelPromotionStrategyVO.builder()
                .id(1)
                .level(level)
                .beginDate("2022-07-01")
                .endDate("2022-08-01")
                .coupon(BigDecimal.valueOf(1000.00))
                .discount(BigDecimal.valueOf(0.80))
                .build();

        return Response.buildSuccess(levelPromotionStrategyVO);
    }


    @PostMapping("/update")
    public Response update(@RequestBody LevelPromotionStrategyVO levelPromotionStrategyVO){
        return Response.buildSuccess();
    }
}
