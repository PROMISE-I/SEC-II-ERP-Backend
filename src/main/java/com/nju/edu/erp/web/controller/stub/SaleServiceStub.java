package com.nju.edu.erp.web.controller.stub;

import com.nju.edu.erp.enums.sheet_state.sale.SaleSheetState;
import com.nju.edu.erp.model.po.sale_purchase.customer.CustomerPO;
import com.nju.edu.erp.model.po.sale_purchase.customer.CustomerPurchaseAmountPO;
import com.nju.edu.erp.model.po.sale_purchase.sale.io_detail.SaleIODetailPO;
import com.nju.edu.erp.model.vo.sale_purchase.sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.sale_purchase.sale.io_detail.SaleIODetailFilterConditionVO;
import com.nju.edu.erp.model.vo.user.UserVO;
import com.nju.edu.erp.service.Interface.sale_purchase.sale.SaleService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 作为替代 SaleService 的桩程序
 */
public class SaleServiceStub implements SaleService {

    @Override
    public void makeSaleSheet(UserVO userVO, SaleSheetVO saleSheetVO) {

    }

    @Override
    public List<SaleSheetVO> getSaleSheetByState(SaleSheetState state) {
        List<SaleSheetVO> ans = new ArrayList<>();
        SaleSheetVO saleSheetVO1 = SaleSheetVO.builder()
                .id("XSD-20220523-00000")
                .state(state)
                .salesman("xiaoshou")
                .discount(BigDecimal.valueOf(0.8))
                .voucherAmount(BigDecimal.valueOf(1000.00))
                .operator("xiaoshou")
                .finalAmount(BigDecimal.valueOf(79000.00))
                .rawTotalAmount(BigDecimal.valueOf(100000.00))
                .supplier(2)
                .build();
        ans.add(saleSheetVO1);
        return ans;
    }

    @Override
    public void approval(String saleSheetId, SaleSheetState state) {

    }

    @Override
    public CustomerPurchaseAmountPO getMaxAmountCustomerOfSalesmanByTime(String salesman, String beginDateStr, String endDateStr) {
        CustomerPO customerPO = CustomerPO.builder()
                .id(1)
                .address("南京大学仙林校区")
                .operator("sky")
                .email("123@qq.com")
                .level(1)
                .payable(BigDecimal.ZERO)
                .lineOfCredit(BigDecimal.ZERO)
                .build();
        CustomerPurchaseAmountPO customerPurchaseAmountPO = CustomerPurchaseAmountPO.builder()
                .customerPO(customerPO)
                .totalFinalAmount(BigDecimal.valueOf(1000.00))
                .build();
        return customerPurchaseAmountPO;
    }

    @Override
    public SaleSheetVO getSaleSheetById(String saleSheetId) {
        SaleSheetVO saleSheetVO1 = SaleSheetVO.builder()
                .id(saleSheetId)
                .state(SaleSheetState.SUCCESS)
                .salesman("xiaoshou")
                .discount(BigDecimal.valueOf(0.8))
                .voucherAmount(BigDecimal.valueOf(1000.00))
                .operator("xiaoshou")
                .finalAmount(BigDecimal.valueOf(79000.00))
                .rawTotalAmount(BigDecimal.valueOf(100000.00))
                .supplier(2)
                .build();
        return saleSheetVO1;
    }

    @Override
    public List<SaleIODetailPO> getSaleDetailByCondition(SaleIODetailFilterConditionVO condition) throws ParseException {
        List<SaleIODetailPO> lst = new ArrayList<>();
        lst.add(new SaleIODetailPO());

        return lst;
    }

    @Override
    public List<SaleIODetailPO> getSaleDetailByRange(String beginTimeStr, String endTimeStr) throws ParseException {
        List<SaleIODetailPO> lst = new ArrayList<>();
        lst.add(new SaleIODetailPO());

        return lst;
    }

    @Override
    public BigDecimal getTotalSaleAmountByMonthAndYearAndSalesman(Integer year, Integer month, String salesman) {
        return BigDecimal.valueOf(1000.00);
    }

    @Override
    public boolean isSheetExists(String sheetId) {
        return false;
    }
}
