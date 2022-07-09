package com.nju.edu.erp.service.Interface.promotion;

import com.nju.edu.erp.model.po.promotion.strategy.total_price.TotalPricePromotionContentPO;
import com.nju.edu.erp.model.po.promotion.strategy.total_price.TotalPricePromotionPO;
import com.nju.edu.erp.model.vo.user.UserVO;
import com.nju.edu.erp.model.vo.promotion.total_price.TotalPricePromotionVO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author WFS
 * @date 2022/7/8 19:29
 */
public interface TotalPricePromotionService {
    /**
     * 总经理制定一个总价的促销策略
     * @param userVO
     * @param totalPricePromotionVO
     */
    void makePromotion(UserVO userVO, TotalPricePromotionVO totalPricePromotionVO);

    /**
     * 获取所有总价的促销策略
     * @return
     */
    List<TotalPricePromotionVO> findAll();

    /**
     * 根据促销策略id设置总价的促销策略
     * @param id
     * @return
     */
    TotalPricePromotionVO findById(String id);

    /**
     * 根据促销策略id伤处一个总价的促销策略
     * @param id
     */
    void deleteById(String id);

    /**
     * 得到超过rawTotalAmount和在today内的所有折扣中优惠卷价格最高的优惠卷金额
     * @param today 时间
     * @param rawTotalAmount 总价
     * @return 优惠卷金额
     */
    BigDecimal getVoucherAmountByDateAndThreshold(Date today, BigDecimal rawTotalAmount);

    /**
     * 根据总价促销策略的id找到赠品内容
     * @param id 总价促销策略的id
     * @return 赠品内容列表
     */
    List<TotalPricePromotionContentPO> findContentByTotalPricePromotionId(String id);

    /**
     * 得到超过rawTotalAmount和在today内的所有折扣中优惠卷价格最高的促销策略
     * @param today 时间
     * @param rawTotalAmount 总价
     * @return 总价促销策略
     */
    TotalPricePromotionPO getPromotionByDateAndThreshold(Date today, BigDecimal rawTotalAmount);
}
