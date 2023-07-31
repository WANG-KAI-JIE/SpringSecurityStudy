package top.kjwang.permission.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author kjwang
 * @date 2023/7/31 10:24
 * @description MyWebSecurityConfig
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MyWebSecurityConfig {
	/**
	 * 基于内存创建用户
	 */
	@Bean
	UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager();
		// 系统管理员
		detailsManager.createUser(User.withUsername("sysadmin").password("{noop}123456").roles("ADMIN").build());
		detailsManager.createUser(User.withUsername("admin").password("{noop}123456").roles("ADMIN").build());
		// 普通用户
		detailsManager.createUser(User.withUsername("user").password("{noop}123456").roles("USER").build());
		return detailsManager;
	}

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
				.anyRequest().authenticated();
		// 开启表单登录
		http.formLogin();
		// 开启 Basic 认证
		http.httpBasic();
		// 关闭 CSRF
		http.csrf().disable();
		return http.build();
	}
}
