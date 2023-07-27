package top.kjwang.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisIndexedHttpSession;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

/**
 * @author kjwang
 * @date 2023/7/27 17:08
 * @description SpringSessionBackedSessionRigestryConfig
 */

@Configuration
@RequiredArgsConstructor
@EnableRedisIndexedHttpSession // 开启支持索引保存会话
public class SpringSessionBackedSessionRigestryConfig {

	// @EnableRedisIndexedHttpSession 会自定注册 FindByIndexNameSessionRepository
	// 我们只需要注入进来即可
	private final FindByIndexNameSessionRepository<? extends Session> sessionRepository;

	// 使用 SpringSessionBackedSessionRegistry，会自动替换默认的 SessionRegistry
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SpringSessionBackedSessionRegistry<>(sessionRepository);
	}
}
