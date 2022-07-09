package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.enums.sheetState.GiveAwaySheetState;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.promotion.GiveAwaySheetVO;
import com.nju.edu.erp.service.GiveAwayService;
import org.springframework.stereotype.Service;

/**
 * @author WFS
 * @date 2022/7/9 14:51
 */
@Service
public class GiveAwayServiceImpl implements GiveAwayService {
    @Override
    public void makeSheet(UserVO userVO, GiveAwaySheetVO giveAwaySheetVO) {

    }

    @Override
    public Object getSheetByState(GiveAwaySheetState state) {
        return null;
    }

    @Override
    public void approval(String sheetId, GiveAwaySheetState state) {

    }
}
