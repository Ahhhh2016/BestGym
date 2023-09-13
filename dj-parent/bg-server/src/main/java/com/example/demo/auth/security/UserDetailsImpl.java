package com.example.demo.auth.security;

import com.example.demo.constants.SecurityConstant;
import com.example.demo.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    /**
     * 用户唯一标识id
     */
    private Integer userId;

    /**
     * 企业id
     */
    private String companyId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 启用禁用状态
     */
    private Integer status;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 企业还是员工（0：企业账户，上级id：员工账户）
     */
    private String isEnterpriseAndStaff;


    /**
     * 权限列表
     */
    private Set<GrantedAuthority> authorities;

    public UserDetailsImpl(User sysUser, Set<GrantedAuthority> authorities) {
        this.userId = sysUser.getId();
        this.username = sysUser.getUsername();
        this.password = sysUser.getPassword();
        this.status = sysUser.getStatus();
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     */
    @Override
    public boolean isEnabled() {
        return SecurityConstant.NORMAL.equals(this.getStatus());
    }
}
