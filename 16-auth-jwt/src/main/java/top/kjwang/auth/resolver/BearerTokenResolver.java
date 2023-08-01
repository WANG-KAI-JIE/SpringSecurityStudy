package top.kjwang.auth.resolver;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author kjwang
 * @date 2023/8/1 09:58
 * @description BearerTokenResolver
 */

@FunctionalInterface
public interface BearerTokenResolver {
	String resolve(HttpServletRequest request);
}
