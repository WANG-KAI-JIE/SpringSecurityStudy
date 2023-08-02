package top.kjwang.auth.cloud.order.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import top.kjwang.auth.cloud.order.common.ResponseUtils;

import java.io.IOException;

/**
 * @author kjwang
 * @date 2023/7/31
 * @description JsonAccessDeniedHandler
 **/
public class JsonAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
        ResponseUtils.buildResponse(response,exception,"权限不足，访问被拒绝", HttpStatus.FORBIDDEN);
    }
}
