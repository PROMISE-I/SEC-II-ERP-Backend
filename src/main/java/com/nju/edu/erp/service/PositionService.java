package com.nju.edu.erp.service;


import com.nju.edu.erp.model.po.PositionInfoPO;

import java.util.List;

public interface PositionService {
    public PositionInfoPO findOneByTitle(String title);

    public List<String> findAll();

    public int updateOne(PositionInfoPO positionInfoPO);

}
