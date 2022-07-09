package com.nju.edu.erp.dao.staff;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserToStaffDao {

    public Integer findStaffIdByUserId(@Param("userId") Integer userId);

    public Integer findUserIdByStaffId(@Param("staffId") Integer staffId);

    public void insertRecord(@Param("userId") Integer userId, @Param("staffId") Integer staffId);

}
