package com.nju.edu.erp.dao.finance.salary;

import com.nju.edu.erp.model.po.finance.salary.YearEndAwardsPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WFS
 * @date 2022/7/8 11:06
 */
@Mapper
@Repository
public interface YearEndAwardsDao {
    /**
     * 在数据库中新增一条年终奖记录
     * @param yearEndAwardsPO 年终奖记录
     */
    void insert(YearEndAwardsPO yearEndAwardsPO);

    /**
     * 更新年终奖记录
     * @param yearEndAwardsPO 新的年终奖记录
     */
    void update(YearEndAwardsPO yearEndAwardsPO);

    /**
     * 获得所有年终奖记录
     * @return 年终奖记录列表
     */
    List<YearEndAwardsPO> findAll();

    /**
     * 判断去年是否已经制定年终奖
     * @param staffId 员工编号
     * @param year 年份
     */
    YearEndAwardsPO findByStaffIdAndYear(Integer staffId, Integer year);
}
