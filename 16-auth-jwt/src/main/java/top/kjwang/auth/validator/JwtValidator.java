package top.kjwang.auth.validator;

import java.io.IOException;

/**
 * @author kjwang
 * @date 2023/8/1 10:00
 * @description JwtValidator
 */
public interface JwtValidator {
	/**
	 * 令牌校验，并返回用户唯一标识
	 *
	 * @param token 令牌字符串
	 * @return 用户唯一标识
	 */
	String validate(String token) throws IOException;
}
