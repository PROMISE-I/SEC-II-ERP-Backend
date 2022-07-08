package com.nju.edu.erp.service.sheet;

import com.nju.edu.erp.enums.sheetState.SheetState;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.sheet.SheetVO;

import java.util.List;

/**
 * @author WFS
 * @date 2022/7/8 17:56
 */
public interface SheetService {
    /**
     * 制定单据
     * @param userVO 操作员信息
     * @param sheetVO 单据信息
     */
    void makeSheet(UserVO userVO, SheetVO sheetVO);

    /**
     * 根据状态查询单据
     * @param state 单据状态
     * @return 单据列表
     */
    List<SheetVO> getSheetByState(SheetState state);

    /**
     * 审批单据
     * @param sheetId 单据编号
     * @param state 审批后的状态
     */
    void approval(String sheetId, SheetState state);
}
