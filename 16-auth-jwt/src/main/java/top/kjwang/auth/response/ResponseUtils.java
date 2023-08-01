package top.kjwang.auth.response;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * @author kjwang
 * @date 2023/8/1 09:49
 * @description ResponseUtils
 */
public class ResponseUtils {

	public static void buildResponse(HttpServletResponse response, Object result, HttpStatus httpStatus) throws IOException {
		response.setContentType("application/json;charset=utf-8"); // 返回 JSON
		response.setStatus(httpStatus.value()); // 状态码
		response.getWriter().write(JSONUtil.toJsonStr(result));
	}
}
