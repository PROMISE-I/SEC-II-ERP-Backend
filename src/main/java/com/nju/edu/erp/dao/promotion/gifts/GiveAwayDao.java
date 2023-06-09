package com.nju.edu.erp.dao.promotion.gifts;

import com.nju.edu.erp.enums.sheet_state.gifts.GiveAwaySheetState;
import com.nju.edu.erp.model.po.promotion.gifts.GiveAwaySheetContentPO;
import com.nju.edu.erp.model.po.promotion.gifts.GiveAwaySheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WFS
 * @date 2022/7/9 17:21
 */
@Mapper
@Repository
public interface GiveAwayDao {
    GiveAwaySheetPO getLatestSheet();

    void save(GiveAwaySheetPO toSave);

    void saveBatch(List<GiveAwaySheetContentPO> contentPOS);

    List<GiveAwaySheetPO> findAll();

    List<GiveAwaySheetPO> findByState(GiveAwaySheetState state);

    GiveAwaySheetPO findSheetById(String sheetId);

    int updateSheetState(String sheetId, GiveAwaySheetState state);

    int updateSheetStateOnPrev(String sheetId, GiveAwaySheetState prevState, GiveAwaySheetState state);

    List<GiveAwaySheetContentPO> findContentById(String sheetId);

    GiveAwaySheetPO findSheetBySaleSheetId(String saleSheetId);
}
