package com.nju.edu.erp.dao;


import com.nju.edu.erp.model.po.PurchaseReturnsSheetPO;
import com.nju.edu.erp.model.po.PurchaseSheetPO;
import com.nju.edu.erp.model.po.SaleReturnsSheetPO;
import com.nju.edu.erp.model.po.SaleSheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BusinessHistoryDao {
    //销售类
    public List<SaleSheetPO> findAllSaleSheetByInterval(@Param("begin") String begin,@Param("end") String end);

    public List<SaleReturnsSheetPO> findAllSaleReturnsSheetByInterval(@Param("begin") String begin, @Param("end") String end);

    //进货类
    public List<PurchaseSheetPO> findAllPurchaseSheetByInterval(@Param("begin") String begin,@Param("end") String end);

    public List<PurchaseReturnsSheetPO> findAllPurchaseReturnsSheetByInterval(@Param("begin") String begin, @Param("end") String end);

    //财务类
    //TODO: ADD INTERFACES
    //库存类
    //TODO: ADD INTERFACES
}
