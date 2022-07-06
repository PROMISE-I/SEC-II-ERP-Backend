package com.nju.edu.erp.dao.promotion;

import com.nju.edu.erp.model.po.promotion.PresentInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PresentInfoDao {
    List<PresentInfoPO> findAll();

    int updateOne(PresentInfoPO presentInfoPO);

    int insertOne(PresentInfoPO presentInfoPO);
}
