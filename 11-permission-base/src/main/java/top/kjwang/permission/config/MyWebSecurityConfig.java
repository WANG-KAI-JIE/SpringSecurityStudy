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
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

/**
 * @author kjwang
 * @date 2023/7/31 09:00
 * @description MyWebSecurityConfig
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class MyWebSecurityConfig {
	/**
	 * 基于内存创建用户
	 */
	@Bean
	UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager();
		// 系统管理员
		detailsManager.createUser(User.withUsername("sysadmin").password("{noop}123456").roles("ADMIN").build());
		// 普通用户
		detailsManager.createUser(User.withUsername("user").password("{noop}123456").roles("USER").build());
		return detailsManager;
	}

//	@Bean
//	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		// authorizeHttpRequests：指定多个授权规则，按照顺序
//		http.authorizeHttpRequests()
//				// permitAll，指定放行（不需要登录即可访问）路径
//				.requestMatchers("/resource/**","/signup","/about").permitAll()
//				// 这些请求必须拥有 ADMIN 角色
//				.requestMatchers("/user/save","/user/update","/user/del").hasRole("ADMIN")
//				// 以 /db/ 开头的请求，必须同时拥有 ADMIN、DBA 角色
//				.requestMatchers("/db/**").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') and hasRole('DBA')"))
//				// 其他任何请求必须认证
//				.anyRequest()
//				.authenticated();
//		// 开启表单登录
//		http.formLogin();
//		// 开启 Basic 认证
//		http.httpBasic();
//		// 开启 CSRF
//		http.csrf().disable();
//		return http.build();
//	}

	/**
	 * 基于方法 AOP
	 */
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		// authorizeHttpRequests：指定多个授权规则，按照顺序
		http.authorizeHttpRequests()
				// 以下请求必须拥有 ADMIN 角色
				.requestMatchers("/user/save","/user/del","/user/update").hasRole("ADMIN")
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
