package com.nju.edu.erp.service.stub;

import com.nju.edu.erp.model.vo.finance.salary.YearEndAwardsVO;
import com.nju.edu.erp.service.Interface.finance.salary.YearEndAwardsService;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WFS
 * @date 2022/7/10 2:20
 */
public class YearEndAwardsServiceStub implements YearEndAwardsService {
    @Override
    public void makeYearEndAwards(YearEndAwardsVO yearEndAwardsVO) {}

    @Override
    public void updateYearEndAwards(YearEndAwardsVO yearEndAwardsVO) {}

    @Override
    public List<YearEndAwardsVO> findAll() {
        List<YearEndAwardsVO> res = new ArrayList<>();
        YearEndAwardsVO yearEndAwardsVO = YearEndAwardsVO.builder()
                .staffId(1)
                .year(2022)
                .amount(BigDecimal.valueOf(10000))
                .build();
        res.add(yearEndAwardsVO);
        return res;
    }

    @Override
    public boolean hasMade(Integer staffId, Integer year) {
        return false;
    }

    @Override
    public BigDecimal getYearEndAwardsByStaffId(int staffId, int year) {
        return BigDecimal.valueOf(10000);
    }
}
