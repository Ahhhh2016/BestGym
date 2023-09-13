package com.example.demo.auth.service;

import com.example.demo.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户权限处理
 *
 *
 */
@Service
public class AuthoritiesService {

    /**
     * 获取用户权限
     *
     * @param user
     * @return Set<GrantedAuthority>
     *
     */
    public Set<GrantedAuthority> getAuthority(User user) {
        // 权限集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        //根据用户获取权限集合
        // 根据角色查找菜单权限
//        List<SysMenu> menus = new ArrayList<>();
//        if (user.getIsAdmin()) {
//            // 查找所有菜单权限，因为角色一般是一个，这里只会执行一次
//            menus = menuService.findAllList(new SysMenu());
//        } else {
//            // 根据角色查询菜单权限
//            List<SysRoleVo> roleList = user.getRoleList();
//            if (CollectionUtils.isNotEmpty(roleList)) {
//                for (SysRoleVo role : roleList) {
//                    // 这里 如果是员工，根据员工角色code 去查询权限，而不是继续查询系统权限了
//                    // 0 为企业 非零  为员工
//                    if ("0".equals(user.getIsEnterpriseAndStaff())) {
//                        List<SysMenu> roleMenus = menuService.findMenuByRole(role.getRoleCode());
//                        if (CollectionUtils.isNotEmpty(roleMenus))
//                            menus.addAll(roleMenus);
//                    } else {
//                        List<SysMenu> roleMenus = menuService.findMenuByStaffRole(role.getRoleCode());
//                        if (CollectionUtils.isNotEmpty(roleMenus))
//                            menus.addAll(roleMenus);
//                    }
//                    // 权限如果前缀是ROLE_，security就会认为这是个角色信息，而不是权限，例如ROLE_ADMIN就是ADMIN角色，MENU:ADD就是MENU:ADD权限
//                    authorities.add(new GrantedAuthorityImpl(role.getRoleCode()));
//                }
//            }
//        }
//        if (CollectionUtils.isNotEmpty(menus)) {
//            // 菜单权限
//            List<GrantedAuthority> authorityList = menus.stream()
//                    .filter(menu -> MenuConstant.MENU_TYPE_PERMISSION.equals(menu.getMenuType()))
//                    .map(menu -> new GrantedAuthorityImpl(menu.getPermission())).collect(Collectors.toList());
//            authorities.addAll(authorityList);
//        }
        return authorities;
    }
}
