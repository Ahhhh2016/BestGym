package com.example.demo.auth.handler;

import com.alibaba.fastjson.JSON;
import com.example.demo.auth.model.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 没有权限，拒绝访问
 *
 *
 */
@Component
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("403 无权限，无法访问系统资源{}",accessDeniedException.getMessage());
        //设置response状态码，返回错误信息等
        int code = HttpStatus.FORBIDDEN.value();
        response.setStatus(code);
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSON.toJSONString(new ResultVo<>(code, accessDeniedException.getMessage())));
    }
}
