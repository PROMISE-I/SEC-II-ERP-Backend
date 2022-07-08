package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.finance.YearEndAwardsVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WFS
 * @date 2022/7/8 10:56
 */

public interface YearEndAwardsService {

    /**
     * 总经理制定年终奖
     * @param yearEndAwardsVO 年终奖信息
     */
    void makeYearEndAwards(YearEndAwardsVO yearEndAwardsVO);

    /**
     * 总经理更新年终奖
     * @param yearEndAwardsVO 年终奖信息
     */
    void updateYearEndAwards(YearEndAwardsVO yearEndAwardsVO);

    /**
     * 获取所有年终奖的信息
     */
    List<YearEndAwardsVO> findAll();

    /**
     * 判断去年是否已经制定年终奖
     * @param staffId 员工编号
     * @param year 年份
     */
    boolean hasMade(Integer staffId, Integer year);
}
