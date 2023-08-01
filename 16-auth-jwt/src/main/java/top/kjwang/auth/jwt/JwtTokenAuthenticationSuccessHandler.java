package top.kjwang.auth.jwt;

import cn.hutool.jwt.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import top.kjwang.auth.response.R;
import top.kjwang.auth.response.ResponseUtils;
import top.kjwang.auth.response.ResultCodeEnum;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kjwang
 * @date 2023/8/1 09:33
 * @description JwtTokenAuthenticationSuccessHandler
 */
public class JwtTokenAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	/**
	 *
	 * @param request        请求
	 * @param response       响应
	 * @param authentication 成功认证的用户信息
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8"); // 返回 JSON
		response.setStatus(HttpStatus.OK.value()); // 状态码 200
		Map<String,Object> result = new HashMap<>(); // 返回结果
		// JWT 信息
		Map<String,Object> jwtMap = new HashMap<>();
		jwtMap.put("username",authentication.getName()); // 用户名作为用户唯一标识，实际开发可以使用 用户ID
		jwtMap.put("expire_time",System.currentTimeMillis() + 1000 * 60 * 60); // 过期时间，一小时后过期
		// 创建令牌（hutool 工具）
		String token = JWTUtil.createToken(jwtMap,AuthenticationConstants.JWT_KEY.getBytes());
		result.put("token",token);
		// 响应数据
		ResponseUtils.buildResponse(response, R.response(ResultCodeEnum.AUTHTICATION_SUCCESS,result),
				HttpStatus.UNAUTHORIZED);
	}
}
