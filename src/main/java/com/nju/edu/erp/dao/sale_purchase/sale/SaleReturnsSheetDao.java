package com.nju.edu.erp.dao.sale_purchase.sale;

import com.nju.edu.erp.enums.sheet_state.sale.SaleReturnsSheetState;
import com.nju.edu.erp.model.po.sale_purchase.sale.io_detail.SaleIODetailFilterConditionPO;
import com.nju.edu.erp.model.po.sale_purchase.sale.io_detail.SaleIODetailPO;
import com.nju.edu.erp.model.po.sale_purchase.sale.returns.SaleReturnsSheetContentPO;
import com.nju.edu.erp.model.po.sale_purchase.sale.returns.SaleReturnsSheetPO;
import com.nju.edu.erp.model.po.warehouse.output.WarehouseOutputSheetContentPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface SaleReturnsSheetDao {
    /**
     * 获取最近一条销售退货单
     * @return 最近一条销售退货单
     */
    SaleReturnsSheetPO getLatest();

    /**
     * 存入一条销售退货记录
     * @param toSave 一条销售退货记录
     * @return 影响的行数
     */
    int save(SaleReturnsSheetPO toSave);

    /**
     * 把销售退货单上的具体内容存入数据库
     * @param sRContentPOList 销售退货单上的具体内容
     */
    void saveBatch(List<SaleReturnsSheetContentPO> sRContentPOList);

    /**
     * 返回所有销售退货单
     * @return 销售退货单列表
     */
    List<SaleReturnsSheetPO> findAll();

    /**
     * 根据state返回销售退货单
     * @param state 销售退货单状态
     * @return 销售退货单
     */
    List<SaleReturnsSheetPO> findAllByState(SaleReturnsSheetState state);

    /**
     * 根据 saleReturnsSheetId 找到条目，并更新其状态为state
     * @param saleReturnsSheetId 销售退货单id
     * @param state 销售退货单状态
     * @return 影响的行数
     */
    int updateState(String saleReturnsSheetId, SaleReturnsSheetState state);

    /**
     * 根据 saleReturnsSheetId 和 prevState 找到条目， 并更新其状态为state
     * @param saleReturnsSheetId 销售退货单id
     * @param prevState 销售退货单之前的状态
     * @param state 销售退货单状态
     * @return 影响的条目数
     */
    int updateStateV2(String saleReturnsSheetId, SaleReturnsSheetState prevState, SaleReturnsSheetState state);

    /**
     * 通过saleReturnsSheetId找到对应条目
     * @param saleReturnsSheetId 销售退货单id
     * @return
     */
    SaleReturnsSheetPO findOneById(String saleReturnsSheetId);

    /**
     * 通过saleReturnsSheetId找到对应的content条目
     * @param saleReturnsSheetId
     * @return
     */
    List<SaleReturnsSheetContentPO> findContentBySaleReturnsSheetId(String saleReturnsSheetId);

    /**
     * 通过saleReturnsSheetId找到退的货的对应所有批次
     * @param saleReturnsSheetId
     * @return
     */
    List<WarehouseOutputSheetContentPO> findAllWarehouseOutputSheetContentBySaleReturnsSheetIdAndPid(String saleReturnsSheetId, String pid);

    /**
     * 查看销售明细表：根据筛选条件选择销售退货单对应的销售明细
     * @param condition 筛选条件
     * @return 销售退货单对应的销售明细
     */
    List<SaleIODetailPO> getSaleReturnsDetailByCondition(SaleIODetailFilterConditionPO condition);

    /**
     * 查看销售明细表：根据时间区间选择销售退货单对应的销售明细
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return 销售退货单对应的销售明细
     */
    List<SaleIODetailPO> getSaleReturnsDetailByRange(Date beginDate, Date endDate);

    /**
     * 返回相同年份和月份的某个销售员的销售退货总额
     * @param year 年份
     * @param month 月份
     * @return 销售退货总额
     */
    BigDecimal getTotalSaleReturnsAmountByMonthAndYearAndSalesman(int year, int month, String salesman);
}
