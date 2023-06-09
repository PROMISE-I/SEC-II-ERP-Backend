package com.nju.edu.erp.dao.promotion.gifts;

import com.nju.edu.erp.model.po.promotion.gifts.PresentInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PresentInfoDao {
    List<PresentInfoPO> findAll();

    int updateOne(PresentInfoPO presentInfoPO);

    int insertOne(PresentInfoPO presentInfoPO);

    List<PresentInfoPO> findPresentInfoByLevel(@Param("level") Integer level);
}
