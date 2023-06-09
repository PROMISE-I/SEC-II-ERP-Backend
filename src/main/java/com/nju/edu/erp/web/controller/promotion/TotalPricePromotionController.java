package com.nju.edu.erp.web.controller.promotion;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.user.Role;
import com.nju.edu.erp.model.vo.user.UserVO;
import com.nju.edu.erp.model.vo.promotion.total_price.TotalPricePromotionVO;
import com.nju.edu.erp.service.Interface.promotion.TotalPricePromotionService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author WFS
 * @date 2022/7/8 18:56
 */
@RestController
@RequestMapping(path = "total-price-promotion")
public class TotalPricePromotionController {

    private final TotalPricePromotionService totalPricePromotionService;

    @Autowired
    public TotalPricePromotionController(TotalPricePromotionService totalPricePromotionService) {
        this.totalPricePromotionService = totalPricePromotionService;
    }

    /**
     * 制定一个总价促销策略
     * @param userVO
     * @param totalPricePromotionVO
     * @return
     */
    //EXPORT: 指定促销策略, 根据总价的促销策略
    @Authorized(roles = {Role.GM, Role.ADMIN})
    @PostMapping("/promotion-make")
    public Response makePromotion(UserVO userVO, @RequestBody TotalPricePromotionVO totalPricePromotionVO) {
        totalPricePromotionService.makePromotion(userVO, totalPricePromotionVO);
        return Response.buildSuccess();
    }

    /**
     * 返回所有总价促销策略
     * @return
     */
    @GetMapping("/find-all")
    public Response findAll() {
        return Response.buildSuccess(totalPricePromotionService.findAll());
    }

    /**
     * 根据id返回总价促销策略
     * @param id
     * @return
     */
    @GetMapping("/find-by-id")
    public Response findById(@RequestParam(value = "id") String id) {
        return Response.buildSuccess(totalPricePromotionService.findById(id));
    }

    /**
     * 删除一个促销策略
     * @param id
     * @return
     */
    //EXPORT: 删除促销策略
    @Authorized(roles = {Role.GM, Role.ADMIN})
    @GetMapping("/promotion-delete")
    public Response delete(@RequestParam(value = "id") String id) {
        totalPricePromotionService.deleteById(id);
        return Response.buildSuccess();
    }
}
