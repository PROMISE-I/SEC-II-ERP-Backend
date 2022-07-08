package com.nju.edu.erp.web.controller;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.finance.YearEndAwardsVO;
import com.nju.edu.erp.service.YearEndAwardsService;
import com.nju.edu.erp.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author WFS
 * @date 2022/7/7 19:17
 */
@RestController
@RequestMapping(path = "/year-end-awards")
public class YearEndAwardsController {

    private final YearEndAwardsService yearEndAwardsService;

    @Autowired
    public YearEndAwardsController(YearEndAwardsService yearEndAwardsService) {
        this.yearEndAwardsService = yearEndAwardsService;
    }

    /**
     * 总经理制定年终奖
     * @param yearEndAwardsVO 年终奖信息
     */
    @Authorized(roles = {Role.GM, Role.ADMIN})
    @PostMapping("/awards-make")
    public Response makeAwards(UserVO userVO, @RequestBody YearEndAwardsVO yearEndAwardsVO) {
        if (userVO.getRole().equals(Role.GM)) return Response.buildFailed("C00000", "总经理没有年终奖");
        else {
            yearEndAwardsService.makeYearEndAwards(yearEndAwardsVO);
            return Response.buildSuccess();
        }
    }

    /**
     * 总经理更新年终奖
     * @param yearEndAwardsVO 年终奖信息
     */
    @Authorized(roles = {Role.GM, Role.ADMIN})
    @PostMapping("/awards-update")
    public Response updateAwards(@RequestBody YearEndAwardsVO yearEndAwardsVO) {
        yearEndAwardsService.updateYearEndAwards(yearEndAwardsVO);
        return Response.buildSuccess();
    }

    /**
     * 获取所有年终奖的信息
     */
    @GetMapping("/find-all")
    public Response findAll() {
        return Response.buildSuccess(yearEndAwardsService.findAll());
    }
}
