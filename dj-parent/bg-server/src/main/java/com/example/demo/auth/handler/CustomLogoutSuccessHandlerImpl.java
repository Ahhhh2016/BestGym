package com.example.demo.auth.handler;

import com.alibaba.fastjson.JSON;
import com.example.demo.auth.model.ResultVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.auth.model.LogOut;
import com.example.demo.auth.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功
 *
 *  ynagyu
 */
@Configuration
@Slf4j
public class CustomLogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 传入accessToken 与 refreshToken 删除redis中的key 便可
        log.info("退出成功{}");
        LogOut logOut = new ObjectMapper().readValue(request.getInputStream(), LogOut.class);
        System.out.println(logOut.toString());
        tokenService.removeToken(logOut.getAccessToken(), logOut.getRefreshToken());
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSON.toJSONString(new ResultVo<>(HttpStatus.OK.value(), "退出成功")));
    }
}
