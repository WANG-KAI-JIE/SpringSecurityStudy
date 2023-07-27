package top.kjwang.auth.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import top.kjwang.auth.handler.JsonAuthenticationFailureHandler;
import top.kjwang.auth.handler.JsonAuthenticationSuccessHandler;

import static top.kjwang.auth.sms.SmsLoginConfigurer.smsLogin;

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
				.requestMatchers("/sms/send/captcha","/sms/login","/login").permitAll()
				.anyRequest().authenticated();
		// 开启表单登录
		http.formLogin()
				.successHandler(new JsonAuthenticationSuccessHandler())
				.failureHandler(new JsonAuthenticationFailureHandler());

		// 开启短信验证码登录（默认关闭）
		// smsLogin()来自：import static top.kjwang.auth.sms.SmsLoginConfigurer.smsLogin;
		http.apply(smsLogin())
				.successHandler(new JsonAuthenticationSuccessHandler()) // 成功处理器
				.failureHandler(new JsonAuthenticationFailureHandler()) // 失败处理器
				.phoneParameter("phone") // 手机号参数名称
				.smsCodeParameter("smsCode"); // 验证码参数名称
		// 禁用写法：http.apply(smsLogin()).disable();

		// 开启 Basic 认证
		http.httpBasic();
		// 关闭 CSRF
		http.csrf().disable();
		return http.build();
	}
}
