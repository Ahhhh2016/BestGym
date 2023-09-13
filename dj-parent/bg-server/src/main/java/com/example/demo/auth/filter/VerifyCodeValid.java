//package com.example.demo.auth.filter;
//
//import com.example.demo.auth.model.LoginUser;
//import com.example.demo.auth.service.RedisService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import net.bytebuddy.asm.Advice;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.util.PathMatcher;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.annotation.Resource;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author beifang
// * @Date 2022-05-14 21:04
// **/
//@Component
//@Slf4j
//public class VerifyCodeValid extends OncePerRequestFilter {
//
//    private static final PathMatcher PATH_MATCHER = new AntPathMatcher();
//
//    @Resource
//    private RedisService redisService;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        if (isProtectedUrl(request)) {
//            String code = request.getParameter("code");
//            System.out.println("inputcode :"+code);
//            // 判断
//            String redisCode = redisService.get("verityCode");
//            System.out.println("HHHH"+redisCode);
//            if (!code.equals(redisCode)) {
//                //手动设置异常
//                request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", new Exception("验证码输入错误"));
//                // 转发到错误Url
//                request.getRequestDispatcher("/user/error").forward(request, response);
//            } else {
//                filterChain.doFilter(request, response);
//            }
//        } else {
//            filterChain.doFilter(request, response);
//        }
//
//    }
//
//    // 拦截 /login的POST请求
//    private boolean isProtectedUrl(HttpServletRequest request) {
//        return "POST".equals(request.getMethod()) && PATH_MATCHER.match("/auth/login", request.getServletPath());
//    }
//}
