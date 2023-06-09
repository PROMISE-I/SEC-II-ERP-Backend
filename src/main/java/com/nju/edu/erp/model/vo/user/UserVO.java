package com.nju.edu.erp.model.vo.user;


import com.nju.edu.erp.enums.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户身份
     */
    private Role role;

    /**
     * 用户密码
     */
    private String password;

}
