package top.kjwang.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author kjwang
 * @date 2023/8/1 09:47
 * @description 响应状态枚举
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ResultCodeEnum {

	JWT_TOKEN_FORMAT_ERROR(401,"非法令牌"),
	AUTHTICATION_SUCCESS(200,"登录成功");

	private int code;
	private String msg;
}