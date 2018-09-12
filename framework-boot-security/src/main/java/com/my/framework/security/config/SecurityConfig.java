package com.my.framework.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-12
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  定义当需要用户登录时候，转到的登录页面。
        http.formLogin()
                // 设置登录页面
                .loginPage("/login.html")
                // 自定义的登录接口
                .loginProcessingUrl("/user/login")
                .and()
                // 定义哪些URL需要被保护、哪些不需要被保护
                .authorizeRequests()
                // 设置所有人都可以访问登录页面
                .antMatchers("/login.html").permitAll()
                .anyRequest()
                // 任何请求,登录后可以访问
                .authenticated()
                .and()
                // 关闭csrf防护
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("user");
    }

}
