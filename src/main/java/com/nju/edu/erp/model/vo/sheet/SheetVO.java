package com.nju.edu.erp.model.vo.sheet;

import com.nju.edu.erp.enums.sheetState.SheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WFS
 * @date 2022/7/8 18:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SheetVO {
    /**
     * 单据编号
     */
    private String id;

    /**
     * 单据状态
     */
    private SheetState state;

    /**
     * 备注
     */
    private String remark;
}
