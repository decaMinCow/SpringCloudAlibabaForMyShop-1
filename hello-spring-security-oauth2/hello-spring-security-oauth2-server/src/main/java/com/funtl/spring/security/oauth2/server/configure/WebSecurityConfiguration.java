package com.funtl.spring.security.oauth2.server.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 开启web安全
// 默认开启了一个拦截器，所有的请求都会经过它，所以就可以判断请求是否经过认证
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    // 密码加密方式
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 配置默认的加密方式
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义认证与授权
        auth.userDetailsService(userDetailsService());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 将 check_token 暴露出去，否则资源服务器访问时报 403 错误
        // 不需要授权，直接可以访问
        web.ignoring().antMatchers("/oauth/check_token");
    }
}
