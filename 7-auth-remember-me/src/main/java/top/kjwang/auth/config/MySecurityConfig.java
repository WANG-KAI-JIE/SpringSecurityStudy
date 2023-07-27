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
		http.rememberMe()
				.alwaysRemember(true) // 始终开启记住我
				.useSecureCookie(true) // 是否只支持 https
				.rememberMeCookieDomain("127.0.0.1") // 可以访问该 cookie 的域名
				.rememberMeCookieName("my-cookie-name") // 配置自定义 Cookie 名，默认 remember-me
				.rememberMeParameter("my-param") // 传递的参数名
				.key("123456") // key，默认为UUID
				.tokenValiditySeconds(60 * 60 * 24 * 7); // 记住我有效时间，默认为2周，这里设置为7天
		return http.build();
	}
}
