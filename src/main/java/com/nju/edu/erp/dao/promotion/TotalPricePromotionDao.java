package com.nju.edu.erp.dao.promotion;

import com.nju.edu.erp.model.po.promotion.TotalPricePromotionContentPO;
import com.nju.edu.erp.model.po.promotion.TotalPricePromotionPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WFS
 * @date 2022/7/8 21:14
 */

@Mapper
@Repository
public interface TotalPricePromotionDao {

    /**
     * 获得最近的一个总价促销策略
     * @return 总价促销策略
     */
    TotalPricePromotionPO getLatest();

    /**
     * 将一个总价促销策略存入数据库
     * @param toSave 总价促销策略
     */
    void save(TotalPricePromotionPO toSave);

    /**
     * 将总价促销策略内容存入数据库
     * @param cpos 总价促销策略内容列表
     */
    void saveBatch(List<TotalPricePromotionContentPO> cpos);

    /**
     * 返回所有总价促销策略
     * @return 总价促销策略列表
     */
    List<TotalPricePromotionPO> findAll();

    /**
     * 返回对应id的总价促销策略
     * @param id 编号
     * @return 总价促销策略
     */
    TotalPricePromotionPO findById(String id);

    /**
     * 删除对应id的总价促销策略
     * @param id 编号
     */
    void deleteById(String id);

    /**
     * 返回对应总价促销策略id的具体内容
     * @param id 编号
     * @return 总价促销策略内容列表
     */
    List<TotalPricePromotionContentPO> findContentByTotalPricePromotionId(String id);

    /**
     * 删除对应id的总价促销策略具体内容
     * @param id 编号
     */
    void deleteContentById(String id);
}
