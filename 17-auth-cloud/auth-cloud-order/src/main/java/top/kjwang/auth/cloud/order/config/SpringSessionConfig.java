package top.kjwang.auth.cloud.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

/**
 * @author kjwang
 * @date 2023/7/31
 * @description SpringSessionConfig
 **/
@Configuration
public class SpringSessionConfig {

    @Bean
    public HttpSessionIdResolver sessionIdResolver() {
        // X-Auth-Token
        HeaderHttpSessionIdResolver xAuthTokenResolver = HeaderHttpSessionIdResolver.xAuthToken();
        // Authentication-Info
        HeaderHttpSessionIdResolver authenticationInfoResolver = HeaderHttpSessionIdResolver.authenticationInfo();
        // Authorization
        HeaderHttpSessionIdResolver customResolver = new HeaderHttpSessionIdResolver("Authorization");
        return xAuthTokenResolver;
    }

}
