package com.nju.edu.erp.model.po.promotion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CombinatorialDiscountPO {
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
}
