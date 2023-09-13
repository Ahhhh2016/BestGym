package com.example.demo.service;

import com.example.demo.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 鐢ㄦ埛 服务类
 * </p>
 *
 * @author 
 * @since 2022-04-08
 */
public interface UserService extends IService<User> {
    User getUserOne(Integer id);
    User findUserByUsername(String username);


    void registerUser(User user);
}
