package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.model.vo.promotion.LevelPromotionStrategyVO;
import com.nju.edu.erp.service.LevelPromotionService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/level-promotion")
public class LevelPromotionController {
    private final LevelPromotionService levelPromotionService;

    @Autowired
    public LevelPromotionController(LevelPromotionService levelPromotionService){
        this.levelPromotionService = levelPromotionService;
    }

    /**
     * show all promotion strategy
     * @return 销售策略的列表
     */
    @GetMapping("/findAll")
    public Response findAll(){
        List<LevelPromotionStrategyVO> lst = levelPromotionService.findAll();

        return Response.buildSuccess(lst);
    }

    /**
     * show promotion strategy for certain level
     * @param level
     * @return 某个级别对应销售策略
     */
    @GetMapping("/findByLevel")
    public Response findByLevel(@RequestParam Integer level){
        LevelPromotionStrategyVO l = levelPromotionService.findByLevel(level);

        return Response.buildSuccess(l);
    }

    /**
     * 更新促销策略
     * @param levelPromotionStrategyVO
     * @return
     */
    @PostMapping("/update")
    public Response update(@RequestBody LevelPromotionStrategyVO levelPromotionStrategyVO){
        levelPromotionService.updateOne(levelPromotionStrategyVO);
        return Response.buildSuccess();
    }

}
