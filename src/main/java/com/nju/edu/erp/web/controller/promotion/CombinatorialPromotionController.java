package com.nju.edu.erp.web.controller.promotion;

import com.nju.edu.erp.model.vo.promotion.combinatorial.CombinatorialDiscountVO;
import com.nju.edu.erp.service.Interface.promotion.CombinatorialPromotionService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/combinatorial-discount")
/**
 * 组合降价策略的 controller
 */
public class CombinatorialPromotionController {

    private final CombinatorialPromotionService combinatorialPromotionService;

    @Autowired
    public CombinatorialPromotionController(CombinatorialPromotionService combinatorialPromotionService){
        this.combinatorialPromotionService = combinatorialPromotionService;
    }

    @GetMapping("/findAll")
    public Response findAll(){

        return Response.buildSuccess(combinatorialPromotionService.findAll());
    }

    @GetMapping("/findByPair")
    public Response findByPair(@RequestParam("productOneId") String productOneId, @RequestParam("productTwoId") String productTwoId){
        return Response.buildSuccess(combinatorialPromotionService.findByPair(productOneId, productTwoId));
    }

    @PostMapping("/create")
    public Response createOne(@RequestBody CombinatorialDiscountVO combinatorialDiscountVO){
        combinatorialPromotionService.createOne(combinatorialDiscountVO);
        return Response.buildSuccess();
    }

    @PostMapping("/update")
    public Response updateOne(@RequestBody CombinatorialDiscountVO combinatorialDiscountVO){
        combinatorialPromotionService.updateOne(combinatorialDiscountVO);
        return Response.buildSuccess();
    }

    @GetMapping("/delete")
    public Response deleteOne(@RequestParam("id") Integer id){
        combinatorialPromotionService.deleteOne(id);
        return Response.buildSuccess();
    }

}
