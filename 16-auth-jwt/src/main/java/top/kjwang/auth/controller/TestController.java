package top.kjwang.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author kjwang
 * @date 2023/8/1 10:17
 * @description TestController
 */

@RestController
public class TestController {

	@GetMapping("/test")
	public Object test() {
		return "Hello Security";
	}

	@GetMapping("/user-info")
	public Object userInfo() {
		SecurityContext context = SecurityContextHolder.getContext(); // 获取 SecurityContext
		Authentication authentication = context.getAuthentication(); // 获取认证信息
		String username = authentication.getName(); // 用户名
		Object principal = authentication.getPrincipal(); // 用户信息
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); // 权限
		System.out.println(username + principal + authorities);
		return authentication;
	}
}
