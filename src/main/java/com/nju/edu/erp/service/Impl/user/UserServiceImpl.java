package com.nju.edu.erp.service.Impl.user;

import com.auth0.jwt.interfaces.Claim;
import com.nju.edu.erp.config.JwtConfig;
import com.nju.edu.erp.dao.user.UserDao;
import com.nju.edu.erp.dao.staff.UserToStaffDao;
import com.nju.edu.erp.enums.user.Role;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.user.User;
import com.nju.edu.erp.model.vo.user.UserVO;
import com.nju.edu.erp.service.Interface.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserToStaffDao userToStaffDao;

    private final JwtConfig jwtConfig;

    @Autowired
    public UserServiceImpl(UserDao userDao, JwtConfig jwtConfig, UserToStaffDao userToStaffDao) {
        this.userDao = userDao;
        this.jwtConfig = jwtConfig;
        this.userToStaffDao = userToStaffDao;
    }


    @Override
    public Map<String, String> login(UserVO userVO) {
        User user = userDao.findByUsernameAndPassword(userVO.getName(), userVO.getPassword());
        if (null == user ) {
            throw new MyServiceException("A0000", "用户名或密码错误");
        }
        Integer userId = user.getId();
        Integer staffId = userToStaffDao.findStaffIdByUserId(userId);
        Map<String, String> authToken = new HashMap<>();
        String token = jwtConfig.createJWT(user);
        authToken.put("token", token);
        authToken.put("staffId", String.valueOf(staffId));
        return authToken;
    }

    @Override
    public void register(UserVO userVO) {
        User user = userDao.findByUsername(userVO.getName());
        if (user != null) {
            throw new MyServiceException("A0001", "用户名已存在");
        }
        User userSave = new User();
        BeanUtils.copyProperties(userVO, userSave);
        userDao.createUser(userSave);
    }

    @Override
    public UserVO auth(String token) {
        Map<String, Claim> claims = jwtConfig.parseJwt(token);
        UserVO userVO = UserVO.builder()
                .name(claims.get("name").as(String.class))
                .role(Role.valueOf(claims.get("role").as(String.class)))
                .build();
        return userVO;
    }
}
