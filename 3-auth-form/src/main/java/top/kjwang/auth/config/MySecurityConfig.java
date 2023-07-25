package top.kjwang.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import top.kjwang.auth.handler.JsonAuthenticationFailureHandler;
import top.kjwang.auth.handler.JsonAuthenticationSuccessHandler;

/**
 * @author kjwang
 * @date 2023/7/25 21:25
 * @description Security 配置类
 */

@Configuration
@EnableWebSecurity
public class MySecurityConfig {
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		// 配置所有的Http请求必须认证
		http.authorizeHttpRequests()
				.requestMatchers("/login.html").permitAll()
				.anyRequest().authenticated();
		// 开启表单登录
		http.formLogin()
//				.loginPage("/login.html")      // 自定义登录页面（注意要同步配置 loginProcessingUrl）
				.loginProcessingUrl("/custom/login") // 自定义登录处理 URL
//				.successForwardUrl("/index.html") // 自定义登录成功后转发的地址（请求转发，地址栏不变）
//				.defaultSuccessUrl("/index.html") // 自定义登录成功后重定向的地址（重定向，地址栏变），会优先跳转到登录前访问的页面，也可以设置总是跳转到该地址
//				.failureForwardUrl("/failure.html") // 自定义登录失败后转发的地址（请求转发，地址栏不变）
//				.failureUrl("failure.html") // 自定义登录失败后重定向的地址（重定向，地址栏变）
				.usernameParameter("name") // 自定义用户名参数名称
				.passwordParameter("pass") // 自定义密码参数名称
				.successHandler(new JsonAuthenticationSuccessHandler())
				.failureHandler(new JsonAuthenticationFailureHandler());
		// 开启Basic认证
		http.httpBasic();
		// 关闭 CSRF
		http.csrf().disable();
		return http.build();
	}
}
