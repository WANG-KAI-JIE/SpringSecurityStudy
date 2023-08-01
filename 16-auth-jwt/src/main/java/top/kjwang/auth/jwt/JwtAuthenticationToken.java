package top.kjwang.auth.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.io.Serial;
import java.util.Collection;

/**
 * @author kjwang
 * @date 2023/8/1 10:07
 * @description JwtAuthenticationToken
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
	@Serial
	private static final long serialVersionUID = 600L;
	private final Object principal;
	private Object credentials;

	public JwtAuthenticationToken(Object principal, Object credentials) {
		super((Collection)null);
		this.principal = principal;
		this.credentials = credentials;
		this.setAuthenticated(false);
	}

	public JwtAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		super.setAuthenticated(true);
	}

	public static JwtAuthenticationToken unauthenticated(Object principal, Object credentials) {
		return new JwtAuthenticationToken(principal, credentials);
	}

	public static JwtAuthenticationToken authenticated(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		return new JwtAuthenticationToken(principal, credentials, authorities);
	}

	public Object getCredentials() {
		return this.credentials;
	}
	public Object getPrincipal() {
		return this.principal;
	}

	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		Assert.isTrue(!isAuthenticated, "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		super.setAuthenticated(false);
	}

	public void eraseCredentials() {
		super.eraseCredentials();
		this.credentials = null;
	}
}
