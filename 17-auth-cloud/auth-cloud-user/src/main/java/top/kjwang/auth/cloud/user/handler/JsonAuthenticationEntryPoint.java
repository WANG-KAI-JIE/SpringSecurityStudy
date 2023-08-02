package top.kjwang.auth.cloud.user.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import top.kjwang.auth.cloud.user.common.ResponseUtils;

import java.io.IOException;

/**
 * @author kjwang
 * @date 2023/7/31
 * @description JsonAuthenticationEntryPoint
 **/
public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtils.buildResponse(response,authException,"未登录", HttpStatus.UNAUTHORIZED);
    }
}
