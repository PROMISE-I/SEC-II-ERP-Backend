package com.nju.edu.erp.service.Interface.promotion.gifts;

import com.nju.edu.erp.enums.sheet_state.gifts.GiveAwaySheetState;
import com.nju.edu.erp.model.po.promotion.gifts.GiveAwaySheetPO;
import com.nju.edu.erp.model.vo.user.UserVO;
import com.nju.edu.erp.model.vo.promotion.grifts.GiveAwaySheetVO;

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

    List<GiveAwaySheetVO> findAll();
}
