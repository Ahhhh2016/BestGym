package com.example.demo.auth.config.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
public class SwaggerAuthorizeConfigProvide implements AuthorizeConfigProvide {

    /**
     * 不需要验证的路径
     * swagger 、springboot-admin.....
     *
     * @param config
     */
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(
                "/actuator/**", // 不对springboot-admin监控的请求进行权限校验
                "/instances/**", // 不对springboot-admin监控的实例信息请求进行权限校验、
                "/doc.html", //放行swagger
                "/swagger-ui.html/**",
                "/webjars/**",
                "/webjars/**",
                "/v2/api-docs",
                "/configuration/**",
                "/swagger-resources",
                "/user/checkSmsCode",
                "/user/getSmsCode",
                "/user/register",
                "/captcha",
                "/user/error")
                .permitAll()
                .anyRequest().authenticated()
        ;
    }
}
