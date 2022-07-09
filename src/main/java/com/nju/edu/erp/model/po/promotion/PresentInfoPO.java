package com.nju.edu.erp.model.po.promotion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PresentInfoPO {
    /**
     * 唯一 Id
     */
    Integer id;

    /**
     * 对应的优惠级别
     */
    Integer level;

    /**
     * 赠送的产品的 id
     */
    String pid;

    /**
     * 赠送的产品个数
     */
    Integer quantity;

}
