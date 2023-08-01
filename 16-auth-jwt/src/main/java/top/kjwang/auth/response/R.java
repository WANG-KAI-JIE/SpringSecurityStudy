package top.kjwang.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * @author kjwang
 * @date 2023/8/1 09:46
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

	public static Object response(ResultCodeEnum resultCodeEnum, Map<String, Object> result) {
		if (resultCodeEnum.getCode() == 200) {
			return response(resultCodeEnum.AUTHTICATION_SUCCESS.getCode(), resultCodeEnum.AUTHTICATION_SUCCESS.getMsg(), result);
		} else {
			return response(resultCodeEnum.JWT_TOKEN_FORMAT_ERROR.getCode(), resultCodeEnum.JWT_TOKEN_FORMAT_ERROR.getMsg(), result);
		}
	}
}
