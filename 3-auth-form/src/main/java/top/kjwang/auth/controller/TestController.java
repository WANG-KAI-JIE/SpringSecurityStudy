package top.kjwang.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kjwang
 * @date 2023/7/25 21:28
 * @description TestController
 */

@RequestMapping
public class TestController {
	@GetMapping("/test")
	public String test(){
		return "Hello Spring Security";
	}
}
