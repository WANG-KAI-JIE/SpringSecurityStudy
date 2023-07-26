package top.kjwang.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import top.kjwang.auth.handler.JsonLogoutSuccessHandler;
import top.kjwang.auth.handler.MyLogoutHandler;

/**
 * @author kjwang
 * @date 2023/7/26 16:48
 * @description Security 配置类
 */

@Configuration
@EnableWebSecurity
public class MySecurityConfig {
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		// 配置所有的 Http 请求必须认证
		http.authorizeHttpRequests()
				.requestMatchers("/login.html","/demo.html")
				.permitAll()
				.anyRequest()
				.authenticated();

		// 开启表单登录
		http.formLogin();

		// 表单注销登录，默认是开启的
		http.logout()
//				自定义注销请求 URL（和 logout 配置只会生效一个）
//				.logoutRequestMatcher(new OrRequestMatcher(
//						new AntPathRequestMatcher("/aaa","GET"),
//						new AntPathRequestMatcher("/bbb","GET")));
				.clearAuthentication(true) // 清理 Authentication，默认 true
				.deleteCookies("xxx","yyy") // 删除某些指定 cookie，添加一个 CookieClearingLogoutHandler
				.invalidateHttpSession(true) // 设置当前登录用户 Session（保存登录后的用户信息）无效，默认true
				.logoutUrl("/custom/logout") // 自定义注销登录请求处理路径
				.logoutSuccessUrl("/demo.html")
				.addLogoutHandler(new MyLogoutHandler()) // 自定义注销处理器
				.logoutSuccessHandler(new JsonLogoutSuccessHandler()); // 自定义注销成功处理器

		// 开启Basic认证
		http.httpBasic();

		// 关闭 CSRF
		http.csrf().disable();
		return http.build();
	}
}