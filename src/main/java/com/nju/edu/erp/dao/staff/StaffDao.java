package com.nju.edu.erp.dao.staff;

import com.nju.edu.erp.model.po.staff.StaffPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StaffDao {
    public int createStaff(StaffPO staffPO);

    public int deleteOneById(@Param("id") Integer staffId);

    public List<StaffPO> findAll();

    public int updateOne(StaffPO staffPO);

    StaffPO getStaffById(int staffId);


    List<StaffPO> findAllExceptGM();
}
