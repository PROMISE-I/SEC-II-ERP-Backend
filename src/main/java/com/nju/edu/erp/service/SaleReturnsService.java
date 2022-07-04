package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
import com.nju.edu.erp.model.po.SaleIODetailPO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.sale.SaleIODetailFilterConditionVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetVO;

import java.text.ParseException;
import java.util.List;

// 制定销售退货单 + 销售经理审批/总经理二级审批 + 更新客户表 + 更新库存
public interface SaleReturnsService {

    /**
     * 制定销售退货单
     * @param userVO 制定单据人员
     * @param saleReturnsSheetVO 销售退货单
     */
    void makeSaleReturnsSheet(UserVO userVO, SaleReturnsSheetVO saleReturnsSheetVO);

    /**
     * 根据状态获取销售退货单(state == null 则获取所有销售退货单)
     * @param state 销售退货单的状态
     * @return 销售退货单
     */
    List<SaleReturnsSheetVO> getSaleReturnsSheetByState(SaleReturnsSheetState state);

    /**
     * 根据销售退货单id进行审批(state == "待二级审批"/"审批完成"/"审批失败")
     * @param saleReturnsSheetId 销售退货单id
     * @param state 销售退货单修改后的状态
     */
    void approval(String saleReturnsSheetId, SaleReturnsSheetState state);

    /**
     * 查看销售明细表：根据筛选条件选择销售退货单单对应的销售明细
     * @param condition 筛选条件
     * @return 销售退货单对应的销售明细
     */
    List<SaleIODetailPO> getSaleReturnsDetailByCondition(SaleIODetailFilterConditionVO condition) throws ParseException;
}
