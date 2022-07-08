package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.finance.YearEndAwardsDao;
import com.nju.edu.erp.model.po.finance.YearEndAwardsPO;
import com.nju.edu.erp.model.vo.finance.YearEndAwardsVO;
import com.nju.edu.erp.service.YearEndAwardsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WFS
 * @date 2022/7/8 11:02
 */
@Service
public class YearEndAwardsServiceImpl implements YearEndAwardsService {

    private final YearEndAwardsDao yearEndAwardsDao;

    @Autowired
    public YearEndAwardsServiceImpl(YearEndAwardsDao yearEndAwardsDao) {
        this.yearEndAwardsDao = yearEndAwardsDao;
    }

    @Override
    public void makeYearEndAwards(YearEndAwardsVO yearEndAwardsVO) {
        YearEndAwardsPO yearEndAwardsPO = new YearEndAwardsPO();

        BeanUtils.copyProperties(yearEndAwardsVO, yearEndAwardsPO);
        yearEndAwardsDao.insert(yearEndAwardsPO);
    }

    @Override
    public void updateYearEndAwards(YearEndAwardsVO yearEndAwardsVO) {
        YearEndAwardsPO yearEndAwardsPO = new YearEndAwardsPO();

        BeanUtils.copyProperties(yearEndAwardsVO, yearEndAwardsPO);
        yearEndAwardsDao.update(yearEndAwardsPO);
    }

    @Override
    public List<YearEndAwardsVO> findAll() {
        List<YearEndAwardsVO> res = new ArrayList<>();
        List<YearEndAwardsPO> all = yearEndAwardsDao.findAll();
        for (YearEndAwardsPO po : all) {
            YearEndAwardsVO vo = new YearEndAwardsVO();
            BeanUtils.copyProperties(po, vo);
            res.add(vo);
        }

        return res;
    }

    @Override
    public boolean hasMade(Integer staffId, Integer year) {
        return yearEndAwardsDao.findByStaffIdAndYear(staffId, year) != null;
    }
}
