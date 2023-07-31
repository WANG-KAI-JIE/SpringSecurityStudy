package top.kjwang.oauth2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kjwang
 * @date 2023/7/31 17:26
 * @description UserController
 */
@Slf4j
@RestController
public class UserController {
	@GetMapping("/user-info")
	@ResponseBody
	Object userInfo() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}