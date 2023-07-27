package top.kjwang.auth.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author kjwang
 * @date 2023/7/27 20:17
 * @description 响应状态枚举
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ResultCodeEnum implements StatusCode {

	FAIL(-1,"操作失败"),
	SUCCESS(200,"操作成功");

	private int code;
	private String msg;

	@Override
	public int getCode() {
		return 0;
	}

	@Override
	public String getMsg() {
		return null;
	}
}
