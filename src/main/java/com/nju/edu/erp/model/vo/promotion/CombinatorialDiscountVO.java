package com.nju.edu.erp.model.vo.promotion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CombinatorialDiscountVO {
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
}
