package com.nju.edu.erp.service;

import com.nju.edu.erp.enums.sheetState.GiveAwaySheetState;
import com.nju.edu.erp.model.po.promotion.GiveAwaySheetPO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.promotion.GiveAwaySheetVO;

import java.util.List;

/**
 * @author WFS
 * @date 2022/7/9 14:50
 */
public interface GiveAwayService {
    void makeSheet(UserVO userVO, GiveAwaySheetVO giveAwaySheetVO);

    List<GiveAwaySheetVO> getSheetByState(GiveAwaySheetState state);

    void approval(String sheetId, GiveAwaySheetState state);

    GiveAwaySheetPO getSheetBySaleSheetId(String saleSheetId);
}
