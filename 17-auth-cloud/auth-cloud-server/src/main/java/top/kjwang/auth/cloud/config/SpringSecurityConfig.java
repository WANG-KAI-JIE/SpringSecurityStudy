package top.kjwang.auth.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import top.kjwang.auth.cloud.handler.JsonAccessDeniedHandler;
import top.kjwang.auth.cloud.handler.JsonAuthenticationEntryPoint;
import top.kjwang.auth.cloud.handler.JsonAuthenticationFailureHandler;
import top.kjwang.auth.cloud.handler.JsonAuthenticationSuccessHandler;

/**
 * @author kjwang
 * @date 2023/7/31
 * @description SpringSecurityConfig
 **/

@Configuration(proxyBeanMethods = false)
public class SpringSecurityConfig {

    /**
     * Spring Security SecurityFilterChain 认证配置
     */
    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                .anyRequest().authenticated()
        );
        // 开启表单登录
        http.formLogin()
                .successHandler(new JsonAuthenticationSuccessHandler())
                .failureHandler(new JsonAuthenticationFailureHandler());
        // 异常配置
        http.exceptionHandling()
                .accessDeniedHandler(new JsonAccessDeniedHandler()) // 权限不足
                .authenticationEntryPoint(new JsonAuthenticationEntryPoint()); // 未登录认证入口
        http.csrf().disable();
        return http.build();
    }

    /**
     * 内存存储用户
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User
                .withUsername("user")
                .password("$2a$10$K/Y1kttXG9aNHOhjneXVku9kxC36uQjc9d36yQXhHjHRwf/ciGT/a")  // BCrypt： 123456
                //.password(new BCryptPasswordEncoder().encode("123456"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


