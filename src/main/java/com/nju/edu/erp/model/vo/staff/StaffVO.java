package com.nju.edu.erp.model.vo.staff;

import com.nju.edu.erp.enums.user.Role;
import com.nju.edu.erp.model.po.staff.StaffPO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffVO {
    public StaffVO(StaffPO staffPO){
        BeanUtils.copyProperties(staffPO, this);
    }
    /**
     * 员工编号
     */
    Integer id;
    /**
     * 姓名
     */
    String name;
    /**
     * 性别
     */
    String gender;
    /**
     * 出生日期
     */
    String birthday;
    /**
     * 手机号
     */
    String phone;
    /**
     * 职位
     */
    Role position;

    /**
     * 工资卡账户
     */
    BigDecimal balance;
}
