package com.nju.edu.erp.service;


import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.model.po.PositionInfoPO;

import java.util.List;

public interface PositionService {
    public PositionInfoPO findOneByTitle(Role title);

    public List<PositionInfoPO> findAll();

    public int updateOne(PositionInfoPO positionInfoPO);

}
