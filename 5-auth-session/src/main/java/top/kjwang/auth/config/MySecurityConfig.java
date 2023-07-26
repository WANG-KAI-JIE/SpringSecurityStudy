package top.kjwang.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * @author kjwang
 * @date 2023/7/26 17:47
 * @description Security 配置类
 */

@Configuration
@EnableWebSecurity
public class MySecurityConfig {
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		// 配置所有的 Http 请求必须认证
		http.authorizeHttpRequests()
				.requestMatchers("/login.html")
				.permitAll()
				.anyRequest()
				.authenticated();
		// 开启表单登录
		http.formLogin();

		// 会话创建策略
		http.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // 创建策略
				.invalidSessionUrl("/login-view?error=INVALID_SESSION") // 失效跳转路径
				.maximumSessions(1) // 用户最大会话数为 1，后面的登录就会自动踢掉前面的登录
				.maxSessionsPreventsLogin(true) // 当前已登录时，组织其他登录
		);

		// 开启 Basic 认证
		http.httpBasic();

		// 关闭 CSRF
		http.csrf().disable();
		return http.build();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.logout(logout -> logout
				.deleteCookies("JSESSIONID")
		);
		return http.build();
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}
}
