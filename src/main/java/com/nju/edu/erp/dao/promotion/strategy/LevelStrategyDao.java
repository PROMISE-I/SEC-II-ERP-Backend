package com.nju.edu.erp.dao.promotion.strategy;

import com.nju.edu.erp.model.po.promotion.strategy.level.LevelPromotionStrategyPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LevelStrategyDao {
    List<LevelPromotionStrategyPO>  findAll();

    List<LevelPromotionStrategyPO> findByLevel(@Param("level") Integer level);

    int updateOne(LevelPromotionStrategyPO levelPromotionStrategyPO);
}
