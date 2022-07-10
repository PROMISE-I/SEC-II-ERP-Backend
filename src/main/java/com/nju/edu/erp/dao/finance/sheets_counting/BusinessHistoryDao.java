package com.nju.edu.erp.dao.finance.sheets_counting;


import com.nju.edu.erp.model.po.sale_purchase.purchase.returns.PurchaseReturnsSheetPO;
import com.nju.edu.erp.model.po.sale_purchase.purchase.PurchaseSheetPO;
import com.nju.edu.erp.model.po.sale_purchase.sale.returns.SaleReturnsSheetPO;
import com.nju.edu.erp.model.po.sale_purchase.sale.SaleSheetPO;
import com.nju.edu.erp.model.po.finance.pay_money.PayMoneySheetPO;
import com.nju.edu.erp.model.po.finance.pay_money.PayMoneyTransferListPO;
import com.nju.edu.erp.model.po.finance.receive_money.ReceiveMoneySheetPO;
import com.nju.edu.erp.model.po.finance.receive_money.ReceiveMoneyTransferListPO;
import com.nju.edu.erp.model.po.finance.salary.SalarySheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BusinessHistoryDao {
    //销售类
    List<SaleSheetPO> findAllSaleSheetByInterval(@Param("begin") String begin,@Param("end") String end, @Param("salesman") String salesman, @Param("customer") Integer customer);

    List<SaleReturnsSheetPO> findAllSaleReturnsSheetByInterval(@Param("begin") String begin, @Param("end") String end, @Param("salesman") String salesman, @Param("customer") Integer customer);

    //进货类
    List<PurchaseSheetPO> findAllPurchaseSheetByInterval(@Param("begin") String begin,@Param("end") String end);

    List<PurchaseReturnsSheetPO> findAllPurchaseReturnsSheetByInterval(@Param("begin") String begin, @Param("end") String end);

    //财务类

    List<PayMoneySheetPO> findAllPayMoneySheetByInterval(@Param("begin") String begin, @Param("end") String end, @Param("customer") Integer customer);

    List<ReceiveMoneySheetPO> findAllReceiveMoneySheetByInterval(@Param("begin") String begin, @Param("end") String end, @Param("customer") Integer customer);

    List<PayMoneyTransferListPO> findPaymentTransferListByPayMoneySheetId(@Param("id") String id);

    List<ReceiveMoneyTransferListPO> findReceiveMoneyTransferListByReceiveMoneySheetId(@Param("id") String id);

    List<SalarySheetPO> findAllSalarySheetByInterval(@Param("begin") String begin, @Param("end") String end);
    //库存类

}
