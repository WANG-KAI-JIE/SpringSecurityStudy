package top.kjwang.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author kjwang
 * @date 2023/8/3 10:08
 * @description SpringSecurityConfig
 */

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		// 配置所有的 Http 请求必须认证
		http.authorizeHttpRequests((authorize) -> authorize
				.anyRequest().authenticated()
		);
		http.cors(withDefaults());
		// 开启表单登录
		http.formLogin();
		http.csrf().disable();
		return http.build();
	}
}
