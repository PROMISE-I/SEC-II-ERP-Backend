package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.po.PositionInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PositionDao {
    public PositionInfoPO findOneByTitle(@Param("title") Role title);

    public List<String> findAll();

    public int updateOne(PositionInfoPO positionInfoPO);
}
