package top.kjwang.auth.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.kjwang.auth.filter.CaptchaVerifyFilter;
import top.kjwang.auth.handler.JsonAuthenticationFailureHandler;
import top.kjwang.auth.handler.JsonAuthenticationSuccessHandler;

/**
 * @author kjwang
 * @date 2023/7/27 20:33
 * @description Security 配置类
 */

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		// 配置所有的 Http 请求必须认证
		http.authorizeHttpRequests()
				.requestMatchers("/generateCaptcha").permitAll()
				.anyRequest().authenticated();
		// 开启表单登录
		http.formLogin()
				.successHandler(new JsonAuthenticationSuccessHandler())
				.failureHandler(new JsonAuthenticationFailureHandler());
		// 开启 Basic 认证
		http.httpBasic();
		http.addFilterBefore(
				new CaptchaVerifyFilter(new JsonAuthenticationFailureHandler(),stringRedisTemplate),
				UsernamePasswordAuthenticationFilter.class);
		// 关闭 CSRF
		http.csrf().disable();
		return http.build();
	}
}
