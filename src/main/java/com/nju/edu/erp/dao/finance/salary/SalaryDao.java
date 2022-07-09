package com.nju.edu.erp.dao.finance.salary;

import com.nju.edu.erp.enums.sheet_state.finance.SalarySheetState;
import com.nju.edu.erp.model.po.finance.salary.SalarySheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface SalaryDao {
    /**
     * 存入工资单
     * @param salarySheet 工资单
     */
    void saveSheet(SalarySheetPO salarySheet);

    /**
     * 通过员工id和时间查找工资单
     * @param employeeId 员工id
     * @param date 日期
     * @return 工资单
     */
    SalarySheetPO findSheetByEmployeeIdAndDate(int employeeId, Date date);

    /**
     * 查询所有工资单
     * @return 所有工资单
     */
    List<SalarySheetPO> findAllSheet();

    /**
     * 按状态查询所有工资单
     * @return 工资单列表
     */
    List<SalarySheetPO> findAllSheetByState(SalarySheetState state);

    /**
     * 根据员工id查找最近一个工资单
     * @param employeeId 员工id
     * @return 工资单
     */
    SalarySheetPO findLatestByEmployeeId(int employeeId);

    /**
     * 根据工资单id查找工资单
     * @param salarySheetId 工资单id
     * @return 工资单
     */
    SalarySheetPO findSheetById(String salarySheetId);

    /**
     * 修改工资单id的状态
     * @param salarySheetId 工资单id
     * @param state 修改后的状态
     * @return 影响行数
     */
    int updateSheetState(String salarySheetId, SalarySheetState state);

    /**
     * 根据状态和工资单id修改状态
     * @param salarySheetId 工资单id
     * @param prevState 修改前的状态
     * @param state 修改后的状态
     * @return 影响行数
     */
    int updateSheetStateOnPrev(String salarySheetId, SalarySheetState prevState, SalarySheetState state);

    /**
     * 根据年份和月份返回相应创建时间的工资单
     * @param staffId 员工编号
     * @param year 年份
     * @param month 月份
     * @return 工资单
     */
    SalarySheetPO findSheetByStaffIdAndYearAndMonth(int staffId, int year, int month);
}
