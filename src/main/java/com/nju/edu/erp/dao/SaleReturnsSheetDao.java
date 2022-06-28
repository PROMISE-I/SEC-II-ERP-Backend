package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
import com.nju.edu.erp.model.po.SaleReturnsSheetContentPO;
import com.nju.edu.erp.model.po.SaleReturnsSheetPO;

import java.util.List;

public interface SaleReturnsSheetDao {
    SaleReturnsSheetPO getLatest();

    void save(SaleReturnsSheetPO saleReturnsSheetPO);

    void saveBatch(List<SaleReturnsSheetContentPO> sRContentPOList);

    List<SaleReturnsSheetPO> findAll();

    List<SaleReturnsSheetPO> findAllByState(SaleReturnsSheetState state);

    List<SaleReturnsSheetContentPO> findContentBySaleReturnsSheetId(String id);

    SaleReturnsSheetPO findOneById(String saleReturnsSheetId);

    int updateState(String saleReturnsSheetId, SaleReturnsSheetState state);

    int updateStateV2(String saleReturnsSheetId, SaleReturnsSheetState prevState, SaleReturnsSheetState state);

    List<Integer> findAllBatchIdBySaleReturnsSheetId(String saleReturnsSheetId);
}
