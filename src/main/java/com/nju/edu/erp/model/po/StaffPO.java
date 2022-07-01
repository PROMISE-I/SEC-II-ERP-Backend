package com.nju.edu.erp.model.po;

import com.nju.edu.erp.model.vo.StaffVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffPO {
    public StaffPO(StaffVO staffVO){
        BeanUtils.copyProperties(staffVO, this);
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
    String position;
    /**
     * 工资账户余额
     */
    BigDecimal balance;
}
