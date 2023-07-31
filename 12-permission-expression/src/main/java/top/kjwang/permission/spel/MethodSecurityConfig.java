package top.kjwang.permission.spel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;

/**
 * @author kjwang
 * @date 2023/7/31 10:19
 * @description MethodSecurityConfig
 */

@Configuration
public class MethodSecurityConfig {

	@Bean
	protected MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
		return new CustomMethodSecurityExpressionHandler();
	}
}
