package top.kjwang.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author kjwang
 * @date 2023/7/25
 * @description MySecurityConfig
 **/

@Configuration
// 开启 Spring Security
@EnableWebSecurity
public class MySecurityConfig {
	/**
	 * 密码编码器
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}