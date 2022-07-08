package com.nju.edu.erp.dao;


import com.nju.edu.erp.model.po.PurchaseReturnsSheetPO;
import com.nju.edu.erp.model.po.PurchaseSheetPO;
import com.nju.edu.erp.model.po.SaleReturnsSheetPO;
import com.nju.edu.erp.model.po.SaleSheetPO;
import com.nju.edu.erp.model.po.finance.PayMoneySheetPO;
import com.nju.edu.erp.model.po.finance.ReceiveMoneySheetPO;
import com.nju.edu.erp.model.po.finance.SalarySheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BusinessHistoryDao {
    //销售类
    public List<SaleSheetPO> findAllSaleSheetByInterval(@Param("begin") String begin,@Param("end") String end, @Param("operator") String operator, @Param("customer") Integer customer);

    public List<SaleReturnsSheetPO> findAllSaleReturnsSheetByInterval(@Param("begin") String begin, @Param("end") String end, @Param("operator") String operator, @Param("customer") Integer customer);

    //进货类
    public List<PurchaseSheetPO> findAllPurchaseSheetByInterval(@Param("begin") String begin,@Param("end") String end, @Param("operator") String operator);

    public List<PurchaseReturnsSheetPO> findAllPurchaseReturnsSheetByInterval(@Param("begin") String begin, @Param("end") String end, @Param("operator") String operator);

    //财务类

    public List<PayMoneySheetPO> findAllPayMoneySheetByInterval(@Param("begin") String begin, @Param("end") String end, @Param("operator") String operator, @Param("customer") Integer customer);

    public List<ReceiveMoneySheetPO> findAllReceiveMoneySheetByInterval(@Param("begin") String begin, @Param("end") String end, @Param("operator") String operator, @Param("customer") Integer customer);

    public List<SalarySheetPO> findAllSalarySheetByInterval(@Param("begin") String begin, @Param("end") String end);
    //库存类
    //TODO: ADD INTERFACES FOR PRESENT SHEET
}
