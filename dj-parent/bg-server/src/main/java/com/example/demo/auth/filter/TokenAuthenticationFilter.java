package com.example.demo.auth.filter;

import com.alibaba.fastjson.JSON;
import com.example.demo.auth.model.ResultVo;
import com.example.demo.auth.security.UserDetailsImpl;
import com.example.demo.auth.service.RedisService;
import com.example.demo.auth.service.TokenService;
import com.example.demo.constants.CommonConstant;
import com.example.demo.utils.SecurityUtils;
import com.example.demo.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * token过滤器 验证token有效性
 */
@Component
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisService redisService;

    /**
     * 过滤不需要拦截 /actuator/health
     *
     * @param request
     * @return
     * @throws ServletException
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        System.out.println(request.getRequestURI());
        if (request.getRequestURI().equalsIgnoreCase("/actuator/health")) {
            return true;
        }
        if (request.getRequestURI().equalsIgnoreCase("/doc.html") ||
                request.getRequestURI().equalsIgnoreCase("/swagger-ui.html") ||
                request.getRequestURI().contains("/webjars") ||
                request.getRequestURI().equalsIgnoreCase("/v2/api-docs") ||
                request.getRequestURI().contains("/configuration") ||
                request.getRequestURI().equalsIgnoreCase("/swagger-resources") ||
                request.getRequestURI().equalsIgnoreCase("/user/register") ||
                request.getRequestURI().equalsIgnoreCase("/captcha")||
                request.getRequestURI().equalsIgnoreCase("/user/getSmsCode")) {
            return true;
        }
        return false;
    }

    /**
     * 验证token
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String internalToken = resolveToken(request);
        // 请求的URI
        String url = request.getRequestURI();
        // 对于 Token 为空的不需要去查 Redis
        if (StringUtils.isNotEmpty(internalToken)) {
            // 先判断是否为刷新token请求
            if (url.equals(CommonConstant.REFRESH_TOKEN_URL)) {

                // 获取请求里refresh_token
                String refreshToken = request.getParameter("refreshToken");
                // 从redis 中 查询刷新 key 是否存在，若存在进行刷新生成新token 并返回token ，若不存在 返回401 重新登录
                UserDetailsImpl details = (UserDetailsImpl) redisService.getHashValue(CommonConstant.REFRESH_TOKEN_KEY + refreshToken, "info");
                // 删除还是有问题，若是平凡的刷新token，每次带的token都是之前的导致，生成新的无法删除
                redisService.remove(CommonConstant.ACCESS_TOKEN_KEY + internalToken);
                if (StringUtils.isNotNull(details) && StringUtils.isNull(SecurityUtils.getAuthentication())) {
                    String newToken = tokenService.refreshToken(details);
                    this.buildAuthentication(newToken, details);
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("accessToken", newToken);
                    response.setContentType("application/json; charset=utf-8");
                    response.getWriter().write(JSON.toJSONString(new ResultVo<>(200, "刷新token成功", hashMap)));
                    return;
                } else {
                    log.info("Token 已过期，请重新登录：{}");
                    int code = HttpStatus.UNAUTHORIZED.value();
                    response.setStatus(code);
                    response.setContentType("application/json; charset=utf-8");
                    response.getWriter().write(JSON.toJSONString(new ResultVo<>(code, "Token 已过期，请重新登录")));
                    return;
                }
            } else {
                // 从redis 中 查询token 是否存在，若存在继续执行，若不存在 返回403
                //List<Object> list = redisService.getHashValue(CommonConstant.ACCESS_TOKEN_KEY + internalToken);
                UserDetailsImpl details = (UserDetailsImpl) redisService.getHashValue(CommonConstant.ACCESS_TOKEN_KEY + internalToken, "info");
                if (StringUtils.isNotNull(details) && StringUtils.isNull(SecurityUtils.getAuthentication())) {
                    Object token = redisService.getHashValue(CommonConstant.ACCESS_TOKEN_KEY + internalToken, "token");
                    //UserDetailsImpl details = (UserDetailsImpl) list.get(1);
                    this.buildAuthentication(token.toString(), details);
                } else {
                    log.info("Token 已失效：{}");
                    int code = HttpStatus.FORBIDDEN.value();
                    response.setStatus(code);
                    response.setContentType("application/json; charset=utf-8");
                    response.getWriter().write(JSON.toJSONString(new ResultVo<>(code, "Token 已失效")));
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 获取Authorization请求头
     *
     * @return token
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(tokenService.getTokenHeader());
        if (StringUtils.isNotEmpty(bearerToken) && bearerToken.startsWith(CommonConstant.TOKEN_PREFIX)) {
            // 去掉令牌前缀
            return bearerToken.replace(CommonConstant.TOKEN_PREFIX, "");
        } else {
            log.debug("非法Token：{}", bearerToken);
        }
        return null;
    }

    /**
     * 构建认证对象
     */
    private void buildAuthentication(String token, UserDetailsImpl userDetails) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
