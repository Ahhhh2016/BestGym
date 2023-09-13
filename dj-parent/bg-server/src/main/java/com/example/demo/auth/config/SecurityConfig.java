package com.example.demo.auth.config;

import com.example.demo.auth.config.authorize.SwaggerAuthorizeConfigProvide;
import com.example.demo.auth.filter.LoginAuthFilter;
import com.example.demo.auth.filter.TokenAuthenticationFilter;
import com.example.demo.auth.handler.CustomAccessDeniedHandler;
import com.example.demo.auth.handler.CustomAuthenticationEntryPoint;
import com.example.demo.auth.handler.CustomLogoutSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//表示开启全局方法注解，可在指定方法上面添加注解指定权限，需含有指定权限才可调用(基于表达式的权限控制)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private CustomAuthenticationEntryPoint authenticationEntryPoint;

//    @Autowired
//    private VerifyCodeValid verifyCodeValid;
    @Autowired
    private CustomLogoutSuccessHandlerImpl logoutSuccessHandler;

    @Autowired
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Autowired
    private SwaggerAuthorizeConfigProvide swaggerAuthorizeConfigProvide;

    @Bean
    public LoginAuthFilter loginAuthFilter() throws Exception {
        return new LoginAuthFilter("/auth/login",authenticationManager());
    }

    //spring security 进行验证授权
    //
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /***
     * 创建授权管理认证对象
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // CSRF禁用，因为不使用session
                //登录后,访问没有权限处理类
//                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
                //匿名访问,没有权限的处理类
                //基于token 无状态登录，取消session管理
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //过滤请求
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/login", "/activityOrder/setUpOrder").permitAll()
                .antMatchers(HttpMethod.GET, "/acaptcha").permitAll()
                //对于登录login 验证码captchaImage 允许匿名访问
                .antMatchers("/login", "/captchaImage", "/swagger-ui.html/**", "/swagger-resources/**").anonymous()
                // 放行druid-admin*/
                .antMatchers("/druid/**").anonymous()
                //添加跨域设置仍然有跨域问题（只解决了其他接口跨域问题）
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .and()
                //设置跨域, 如果不设置, 即使配置了filter, 也不会生效
                .cors()
                .and()
                .headers().frameOptions().disable()
                .and()
//                .addFilterBefore(verifyCodeValid,UsernamePasswordAuthenticationFilter.class)
                // 登录请求认证过滤
                .addFilterBefore(loginAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                // 添加效验token过滤
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        //退出登录
        http.logout().logoutUrl("/auth/logout").logoutSuccessHandler(logoutSuccessHandler);
        //  放行swagger 、springbootadmin.......
        swaggerAuthorizeConfigProvide.config(http.authorizeRequests());
    }

    /*@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**");
    }
    .antMatchers("/swagger-ui.html").anonymous()
                .antMatchers("/swagger-resources/**").anonymous() // 放行swagger*/
}