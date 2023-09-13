package com.example.demo.auth.security;

import com.example.demo.domain.User;
import com.example.demo.auth.service.AuthoritiesService;
import com.example.demo.service.UserService;
import com.example.demo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 登录会自动调用此方法，将登录用户对象返回给security
 * 从数据库获取用户信息
 *
 *  c
 */
@Service(value = "customUserDetailsServiceImpl")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthoritiesService authoritiesService;

    /*
     * 通过username获取用户信息
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.err.println("============================DB 获取用户信息===============");
        // 通过用户名查询用户信息
        User sysUser = userService.findUserByUsername(username);
        if (StringUtils.isNull(sysUser)) {
            throw new UsernameNotFoundException("user by name not found");
        }
        Set<GrantedAuthority> userPermissions = authoritiesService.getAuthority(sysUser);
        UserDetailsImpl userDetails = new UserDetailsImpl(sysUser, userPermissions);
        return userDetails;
    }
}
