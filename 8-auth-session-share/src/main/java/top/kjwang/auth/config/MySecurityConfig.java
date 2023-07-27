package top.kjwang.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author kjwang
 * @date 2023/7/27 15:20
 * @description MySecurityConfig
 */

@Configuration
@EnableWebSecurity
public class MySecurityConfig {
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		// 配置所有的 Http 请求必须认证
		http.authorizeHttpRequests()
				.requestMatchers("/login.html").permitAll()
				.anyRequest().authenticated();
		// 开启表单登录
		http.formLogin();
		// 开启Basic认证
		http.httpBasic();
		// 关闭 CSRF
		http.csrf().disable();
		// 开启记住我
		http.rememberMe();
		http.sessionManagement(session -> session
				.maximumSessions(1) // 用户最大会话数为 1，后面的登录就会自动踢掉前面的登录
				.maxSessionsPreventsLogin(true) // 当前已登录时，阻止其他登录
		);
		return http.build();
	}
}
