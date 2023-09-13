package com.example.demo.auth.handler;

import com.alibaba.fastjson.JSON;
import com.example.demo.auth.model.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 匿名未登录 返回未授权 401
 *
 *
 */
@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("401 未登录 认证失败，无法访问系统资源{}",authException.getMessage());
        //设置response状态码，返回错误信息等
        int code = HttpStatus.UNAUTHORIZED.value();
        response.setStatus(code);
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSON.toJSONString(new ResultVo<>(code, "认证失败，未登录")));
    }
}
