package com.nju.edu.erp.model.vo.promotion.grifts;

import com.nju.edu.erp.enums.sheet_state.gifts.GiveAwaySheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author WFS
 * @date 2022/7/9 10:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GiveAwaySheetVO {
    /**
     * 赠送单单据编号，格式：ZSD-yyyyMMdd-xxxxx，前端传null
     */
    private String id;

    /**
     * 赠送单所属销售单的单据编号
     */
    private String saleSheetId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 单据状态，前端传null
     */
    private GiveAwaySheetState state;

    /**
     * 创建时间，前端传null
     */
    private Date createTime;

    /**
     * 赠送商品总价，前端传null
     */
    private BigDecimal totalAmount;

    /**
     * 赠品清单内容
     */
    private List<GiveAwaySheetContentVO> contentVOList;
}
