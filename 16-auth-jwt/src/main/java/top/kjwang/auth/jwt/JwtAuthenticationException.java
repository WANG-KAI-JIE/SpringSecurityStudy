package top.kjwang.auth.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * @author kjwang
 * @date 2023/8/1 09:31
 * @description JwtAuthenticationException
 */
public class JwtAuthenticationException extends AuthenticationException {
	public JwtAuthenticationException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public JwtAuthenticationException(String msg) {
		super(msg);
	}
}
