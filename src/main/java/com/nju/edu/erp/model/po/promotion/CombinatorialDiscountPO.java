package com.nju.edu.erp.model.po.promotion;

import com.nju.edu.erp.model.vo.promotion.CombinatorialDiscountVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * 组合商品降价信息的 PO
 */
public class CombinatorialDiscountPO {
    public CombinatorialDiscountPO(CombinatorialDiscountVO combinatorialDiscountVO){
        this.id = combinatorialDiscountVO.getId();
        this.productOneId = combinatorialDiscountVO.getProductOneId();
        this.productTwoId = combinatorialDiscountVO.getProductTwoId();
        this.discountAmount = combinatorialDiscountVO.getDiscountAmount();
        this.beginDate = combinatorialDiscountVO.getBeginDate();
        this.endDate = combinatorialDiscountVO.getEndDate();
    }
    /**
     * 数据库中的自增 Id
     */
    Integer id;
    /**
     * 商品 1 的商品 id
     */
    String productOneId;
    /**
     * 商品 2 的商品 id
     */
    String productTwoId;
    /**
     * 折让金额
     */
    BigDecimal discountAmount;
    /**
     * 开始日期
     */
    String beginDate;
    /**
     * 结束日期
     */
    String endDate;
}
