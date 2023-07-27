package top.kjwang.auth.handler;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kjwang
 * @date 2023/7/27 20:44
 * @description 认证失败处理器，校验失败时返回JSON
 */
public class JsonAuthenticationFailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8"); // 返回 JSON
		response.setStatus(HttpStatus.UNAUTHORIZED.value()); // 状态码 400
		Map<String,Object> result = new HashMap<>(); // 返回结果
		result.put("code",401);
		result.put("data",exception.getMessage());
		response.getWriter().write(JSONUtil.toJsonStr(result));
	}
}
