package top.kjwang.permission.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kjwang
 * @date 2023/7/31 09:08
 * @description UserController
 */

@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping("/save")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	String save() {
		return "save";
	}

	@GetMapping("/del")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	String del() {
		return "del";
	}

	@GetMapping("/update")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	String update() {
		return "update";
	}

	@GetMapping("/list")
	String list() {
		return "list";
	}
}
