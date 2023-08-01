package top.kjwang.auth.validator;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import top.kjwang.auth.jwt.AuthenticationConstants;
import top.kjwang.auth.jwt.JwtAuthenticationException;

import java.io.IOException;

/**
 * @author kjwang
 * @date 2023/8/1 10:02
 * @description HutoolJwtValidator
 */
public class HutoolJwtValidator implements JwtValidator {
	@Override
	public String validate(String token) {
		// 验证
		boolean verify = JWTUtil.verify(token, AuthenticationConstants.JWT_KEY.getBytes());
		if (!verify) {
			throw new JwtAuthenticationException("非法令牌");
		}
		// 过期
		final JWT jwt = JWTUtil.parseToken(token);
		long expireTime = (long) jwt.getPayload("expire_time");
		if (System.currentTimeMillis() > expireTime) {
			throw new JwtAuthenticationException("令牌已失效");
		}
		// 返回
		return (String) jwt.getPayload("username");
	}
}
