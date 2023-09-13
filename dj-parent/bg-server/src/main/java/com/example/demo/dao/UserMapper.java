package com.example.demo.dao;

import com.example.demo.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 鐢ㄦ埛 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2022-04-08
 */
public interface UserMapper extends BaseMapper<User> {
    User getUserOne(@Param("id") Integer id);

    User findUserByUsername(String username);

    void addUser(User user);
}
