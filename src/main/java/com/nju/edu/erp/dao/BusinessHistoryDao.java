package com.nju.edu.erp.dao;


import com.nju.edu.erp.model.po.PurchaseReturnsSheetPO;
import com.nju.edu.erp.model.po.PurchaseSheetPO;
import com.nju.edu.erp.model.po.SaleReturnsSheetPO;
import com.nju.edu.erp.model.po.SaleSheetPO;
import com.nju.edu.erp.model.po.finance.*;
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

    List<ReceiveMoneySheetPO> findAllReceiveMoneySheetByInterval(@Param("begin") String begin, @Param("end") String end,@Param("customer") Integer customer);

    List<PayMoneyTransferListPO> findPaymentTransferListByPayMoneySheetId(@Param("id") String id);

    List<ReceiveMoneyTransferListPO> findReceiveMoneyTransferListByReceiveMoneySheetId(@Param("id") String id);

    List<SalarySheetPO> findAllSalarySheetByInterval(@Param("begin") String begin, @Param("end") String end);
    //库存类
    //TODO: ADD INTERFACES FOR PRESENT SHEET

}
