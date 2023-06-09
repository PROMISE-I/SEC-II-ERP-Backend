package com.nju.edu.erp.dao.staff;

import com.nju.edu.erp.enums.user.Role;
import com.nju.edu.erp.model.po.staff.PositionInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PositionDao {
    public PositionInfoPO findOneByTitle(@Param("title") Role title);

    public List<PositionInfoPO> findAll();

    public int updateOne(PositionInfoPO positionInfoPO);
}
