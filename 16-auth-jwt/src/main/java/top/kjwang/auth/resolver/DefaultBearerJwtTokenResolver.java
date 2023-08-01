package top.kjwang.auth.resolver;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import top.kjwang.auth.jwt.JwtAuthenticationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kjwang
 * @date 2023/8/1 09:59
 * @description DefaultBearerJwtTokenResolver
 */
public final class DefaultBearerJwtTokenResolver implements BearerTokenResolver {

	private static final Pattern AUTHORIZATION_PATTERN = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-._~+/]+=*)$",
			Pattern.CASE_INSENSITIVE);

	private String bearerTokenHeaderName = HttpHeaders.AUTHORIZATION;

	public String resolve(final HttpServletRequest request) {
		return resolveFromAuthorizationHeader(request);
	}

	public void setBearerTokenHeaderName(String bearerTokenHeaderName) {
		this.bearerTokenHeaderName = bearerTokenHeaderName;
	}

	private String resolveFromAuthorizationHeader(HttpServletRequest request) {
		String authorization = request.getHeader(this.bearerTokenHeaderName);
		if (!StringUtils.startsWithIgnoreCase(authorization, "bearer")) {
			return null;
		}
		Matcher matcher = AUTHORIZATION_PATTERN.matcher(authorization);
		if (!matcher.matches()) {
			throw new JwtAuthenticationException("令牌格式错误");
		}
		return matcher.group("token");
	}
}
