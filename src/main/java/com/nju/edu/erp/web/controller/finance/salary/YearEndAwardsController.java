package com.nju.edu.erp.web.controller.finance.salary;

import com.nju.edu.erp.auth.Authorized;
import com.nju.edu.erp.enums.user.Role;
import com.nju.edu.erp.model.vo.user.UserVO;
import com.nju.edu.erp.model.vo.finance.salary.YearEndAwardsVO;
import com.nju.edu.erp.service.Interface.staff.StaffService;
import com.nju.edu.erp.service.Interface.finance.salary.YearEndAwardsService;
import com.nju.edu.erp.utils.DateHelper;
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

    private final StaffService staffService;

    @Autowired
    public YearEndAwardsController(YearEndAwardsService yearEndAwardsService, StaffService staffService) {
        this.yearEndAwardsService = yearEndAwardsService;
        this.staffService = staffService;
    }

    /**
     * 总经理制定年终奖
     * @param yearEndAwardsVO 年终奖信息
     */
    @Authorized(roles = {Role.GM, Role.ADMIN})
    @PostMapping("/awards-make")
    public Response makeAwards(UserVO userVO, @RequestBody YearEndAwardsVO yearEndAwardsVO) {
        yearEndAwardsVO.setYear(DateHelper.getLastYear());

        if (staffService.getRoleByEmployeeId(yearEndAwardsVO.getStaffId()).equals(Role.GM)) return Response.buildFailed("C00000", "总经理没有年终奖!");
        else if (yearEndAwardsService.hasMade(yearEndAwardsVO.getStaffId(), yearEndAwardsVO.getYear())){
            return Response.buildFailed("C00001", "该员工去年已经制定过年终奖了！请不要重复制定");
        }else {
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

    /**
     * 查询某个员工某年的年终奖
     * @param staffId 员工id
     * @param year 年份
     * @return 年终奖
     */
    @GetMapping("/find-by-staffId-year")
    public Response findByStaffIdAndYear(@RequestParam(value = "staffId") int staffId,
                                         @RequestParam(value = "year") int year) {
        return Response.buildSuccess(yearEndAwardsService.getYearEndAwardsByStaffId(staffId, year));
    }
}
