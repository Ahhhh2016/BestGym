package com.example.demo.service.serviceImpl;

import com.example.demo.constants.CommonConstant;
import com.example.demo.domain.User;
import com.example.demo.dao.UserMapper;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 鐢ㄦ埛 服务实现类
 * </p>
 *
 * @author 
 * @since 2022-04-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserOne(Integer id) {
        return userMapper.getUserOne(id);
    }

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户对象信息
     *
     */
    public User findUserByUsername(String username) {
        User sysUser = userMapper.findUserByUsername(username);
        if (sysUser == null){
            return null;
        }
        return sysUser;
    }

    @Override
    public void registerUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus(0);
        userMapper.addUser(user);
    }

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 添加用户
     */
}
