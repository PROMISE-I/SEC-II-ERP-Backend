package com.nju.edu.erp.dao.finance.sheets_counting;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Mapper
@Repository
public interface OperateSheetDao {

    public BigDecimal findSaleIncome(@Param("begin") String begin, @Param("end") String end);

    public BigDecimal findPurchaseCost(@Param("begin") String begin, @Param("end") String end);

    public BigDecimal findDiscountedAmount(@Param("begin") String begin, @Param("end") String end);

    public BigDecimal findSaleReturnCost(@Param("begin") String begin, @Param("end") String end);

    public BigDecimal findProductPresentCost(@Param("begin") String begin, @Param("end") String end);

    public BigDecimal findHumanResourceCost(@Param("begin") String begin, @Param("end") String end);

    public BigDecimal findPurchaseReturnCost(@Param("begin") String begin, @Param("end") String end);
}
