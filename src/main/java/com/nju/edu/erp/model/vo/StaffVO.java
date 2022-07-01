package com.nju.edu.erp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffVO {
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
}
