package com.nju.edu.erp.model.vo.promotion.combinatorial;

import com.nju.edu.erp.model.po.promotion.strategy.combinatorial.CombinatorialDiscountPO;
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
 * 组合商品降价信息的 VO
 */
public class CombinatorialDiscountVO {
    public CombinatorialDiscountVO(CombinatorialDiscountPO combinatorialDiscountPO){
        this.id = combinatorialDiscountPO.getId();
        this.productOneId = combinatorialDiscountPO.getProductOneId();
        this.productTwoId = combinatorialDiscountPO.getProductTwoId();
        this.discountAmount = combinatorialDiscountPO.getDiscountAmount();
        this.beginDate = combinatorialDiscountPO.getBeginDate();
        this.endDate = combinatorialDiscountPO.getEndDate();
    }
    /**
     * 唯一标识 id
     */
    Integer id;
    /**
     * 商品 1 的商品 id
     */
    String productOneId;
    /**
     * 商品 1 的商品名称
     */
    String productOneName;
    /**
     * 商品 2 的商品 id
     */
    String productTwoId;
    /**
     * 商品 2 的商品名称
     */
    String productTwoName;
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
