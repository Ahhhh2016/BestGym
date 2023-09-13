package com.example.demo.auth.filter;

import com.alibaba.fastjson.JSON;
import com.example.demo.auth.model.ResultVo;
import com.example.demo.auth.security.UserDetailsImpl;
import com.example.demo.auth.service.RedisService;
import com.example.demo.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.auth.model.LoginUser;
import com.example.demo.auth.service.TokenService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * 验证用户名与密码，验证成功生成token 并返回给客户端
 *
 *
 */
@Slf4j
public class LoginAuthFilter extends AbstractAuthenticationProcessingFilter {

    // spring web 启动顺序 Listener -> Filter -> servlet 所有此次需要在WebSecurityConfigurerAdapter中提前加载，不然@Autowired 还没注入成功，获取为null
    @Autowired
    private TokenService tokenService;

    public LoginAuthFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        // 修改默认登录拦截路径
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
    }

    @Autowired
    private RedisService redisService;
    /*
     * 接受并解析用户凭证
     * */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // 从流中获取登录信息
            LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
            // 可效验验证码
            String verityCode = redisService.get("verityCode");

            if(!loginUser.getCode().equals(verityCode)){
                throw new BadCredentialsException("verification error");
            }else {
                // 该方法会去调用CustomUserDetailServiceImpl.loadUserByUsername
                return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /*
     * 验证成功，此方法将会被调用，返回token  TOKEN
     *
     * */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authResult.getPrincipal();
        String token = tokenService.createToken(userDetailsImpl);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("accessToken", token);
        hashMap.put("refreshToken", token);
        String username = userDetailsImpl.getUsername();
        hashMap.put("username", username);

        int code = HttpStatus.OK.value();
        response.setStatus(code);
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSON.toJSONString(new ResultVo<>(code, "登录成功", hashMap)));
    }

    /*
     * setelFlag
     * */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.error("认证失败: {}", failed.getMessage());
        int code = HttpStatus.UNAUTHORIZED.value();
        response.setStatus(code);
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSON.toJSONString(new ResultVo<>(code, failed.getMessage(), "")));
    }
}
