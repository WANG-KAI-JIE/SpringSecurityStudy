package top.kjwang.auth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author kjwang
 * @date 2023/7/27 20:39
 * @description 验证码校验异常类
 */
public class CaptchaVerifyException extends AuthenticationException {
	public CaptchaVerifyException(String msg,Throwable cause) {
		super(msg, cause);
	}

	public CaptchaVerifyException(String msg) {
		super(msg);
	}
}
