package com.nju.edu.erp.dao;

import com.nju.edu.erp.model.po.PositionInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PositionDao {
    public PositionInfoPO findOneByTitle(@Param("title") String title);

    public List<String> findAll();

    public int updateOne(PositionInfoPO positionInfoPO);
}
