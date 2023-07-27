package top.kjwang.auth.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author kjwang
 * @date 2023/7/27 20:20
 * @description R
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {

	/**
	 * 状态码
	 */
	private Integer code;

	/**
	 * 返回信息
	 */
	private String msg;

	/**
	 * 数据
	 */
	private T data;

	public static <T> R<T> response(Integer code,String msg,T data) {
		R<T> result = new R<>();
		result.setCode(code);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}

	public static <T> R<T> success() {
		return response(ResultCodeEnum.SUCCESS.getCode(),ResultCodeEnum.SUCCESS.getMsg(), null);
	}

	public static <T> R<T> fail() {
		return response(ResultCodeEnum.FAIL.getCode(),ResultCodeEnum.FAIL.getMsg(), null);
	}
}
