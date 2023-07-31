package top.kjwang.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author kjwang
 * @date 2023/7/31
 * @description OAuth2LoginSecurityConfig
 **/
@Configuration
@EnableWebSecurity
public class OAuth2LoginSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // authorizeHttpRequests：指定多个授权规则，按照顺序
        http.authorizeHttpRequests()
                .anyRequest().authenticated();
        // 开启表单登录
        http.formLogin();
        // 开启 OAuth2 登录
        http.oauth2Login();
        // 开启Basic认证
        http.httpBasic();
        // 关闭 CSRF
        http.csrf().disable();
        return http.build();
    }
}