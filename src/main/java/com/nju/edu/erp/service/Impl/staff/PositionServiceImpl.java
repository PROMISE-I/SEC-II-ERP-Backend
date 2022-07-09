package com.nju.edu.erp.service.Impl.staff;

import com.nju.edu.erp.dao.staff.PositionDao;
import com.nju.edu.erp.enums.user.Role;
import com.nju.edu.erp.model.po.staff.PositionInfoPO;
import com.nju.edu.erp.service.Interface.staff.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    private final PositionDao positionDao;

    @Autowired
    public PositionServiceImpl(PositionDao positionDao){
        this.positionDao = positionDao;
    }

    @Override
    public PositionInfoPO findOneByTitle(Role title) {
        return positionDao.findOneByTitle(title);
    }

    @Override
    public List<PositionInfoPO> findAll() {
        return positionDao.findAll();
    }

    @Override
    public int updateOne(PositionInfoPO positionInfoPO) {
        return positionDao.updateOne(positionInfoPO);
    }
}
