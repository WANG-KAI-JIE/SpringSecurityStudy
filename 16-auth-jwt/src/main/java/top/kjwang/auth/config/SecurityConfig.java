package top.kjwang.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import top.kjwang.auth.jwt.JwtTokenAuthenticationSuccessHandler;

import static top.kjwang.auth.config.JwtAuthenticationConfigurer.jwtAuth;

/**
 * @author kjwang
 * @date 2023/8/1 09:52
 * @description SecurityConfig
 */

@Configuration
public class SecurityConfig {

	/**
	 * 前后端分离
	 */
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		// 配置所有的 Http 请求必须认证
		http.authorizeHttpRequests()
				.anyRequest().authenticated();
		// 开启表单登录
		http.formLogin().successHandler(new JwtTokenAuthenticationSuccessHandler());
		http.csrf().disable();
		// 开启 JWT （默认关闭）
		http.apply(jwtAuth());
		return http.build();
	}

	/**
	 * 内存存储用户
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails userDetails = User.withDefaultPasswordEncoder()
				.username("user")
				.password("123456")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(userDetails);
	}
}
