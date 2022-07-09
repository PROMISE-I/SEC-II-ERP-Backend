package com.nju.edu.erp.service.Interface.sale;

import com.nju.edu.erp.enums.sheet_state.sale.SaleSheetState;
import com.nju.edu.erp.model.po.customer.CustomerPurchaseAmountPO;
import com.nju.edu.erp.model.po.sale.io_detail.SaleIODetailPO;
import com.nju.edu.erp.model.vo.sale.io_detail.SaleIODetailFilterConditionVO;
import com.nju.edu.erp.model.vo.sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.user.UserVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

@Service
public interface SaleService {

    /**
     * 指定销售单
     * @param userVO
     * @param saleSheetVO
     */
    void makeSaleSheet(UserVO userVO, SaleSheetVO saleSheetVO);

    /**
     * 根据单据状态获取销售单
     * @param state
     * @return
     */
    List<SaleSheetVO> getSaleSheetByState(SaleSheetState state);

    /**
     * 审批单据
     * @param saleSheetId
     * @param state
     */
    void approval(String saleSheetId, SaleSheetState state);

    /**
     * 获取某个销售人员某段时间内消费总金额最大的客户(不考虑退货情况,销售单不需要审批通过,如果这样的客户有多个，仅保留一个)
     * @param salesman 销售人员的名字
     * @param beginDateStr 开始时间字符串
     * @param endDateStr 结束时间字符串
     * @return
     */
    CustomerPurchaseAmountPO getMaxAmountCustomerOfSalesmanByTime(String salesman,String beginDateStr,String endDateStr);

    /**
     * 根据销售单Id搜索销售单信息
     * @param saleSheetId 销售单Id
     * @return 销售单全部信息
     */
    SaleSheetVO getSaleSheetById(String saleSheetId);

    /**
     * 查看销售明细表：根据筛选条件选择销售单对应的销售明细
     * @param condition 筛选条件
     * @return 销售单对应的销售明细
     */
    List<SaleIODetailPO> getSaleDetailByCondition(SaleIODetailFilterConditionVO condition) throws ParseException;

    /**
     * 查看销售明细表：根据时间区间选择销售单对应的销售明细
     * @param beginTimeStr 开始时间字符串
     * @param endTimeStr 结束时间字符串
     * @return 销售单对应的销售明细
     */
    List<SaleIODetailPO> getSaleDetailByRange(String beginTimeStr, String endTimeStr) throws ParseException;

    /**
     * 返回相同年份和月份的某个销售员的销售总额
     * @param year 年份
     * @param month 月份
     * @return 销售总额
     */
    BigDecimal getTotalSaleAmountByMonthAndYearAndSalesman(Integer year, Integer month, String salesman);

    /**
     * 查看单据是否存在
     * @param sheetId 单据编号
     * @return 是否存在, true 表示存在
     */
    boolean isSheetExists(String sheetId);
}
