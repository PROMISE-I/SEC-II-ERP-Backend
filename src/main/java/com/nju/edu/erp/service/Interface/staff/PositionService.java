package com.nju.edu.erp.service.Interface.staff;


import com.nju.edu.erp.enums.user.Role;
import com.nju.edu.erp.model.po.staff.PositionInfoPO;

import java.util.List;

public interface PositionService {
    public PositionInfoPO findOneByTitle(Role title);

    public List<PositionInfoPO> findAll();

    public int updateOne(PositionInfoPO positionInfoPO);

}
