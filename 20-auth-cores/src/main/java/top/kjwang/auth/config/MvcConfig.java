package top.kjwang.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author kjwang
 * @date 2023/8/3 10:14
 * @description MvcConfig
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOriginPatterns("*")
				.allowedMethods("POST","GET","PUT","OPTIONS","DELETE")
				.maxAge(3600)
				.allowCredentials(true);
	}
}
