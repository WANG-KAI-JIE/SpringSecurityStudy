package top.kjwang.auth.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kjwang
 * @date 2023/8/3 09:44
 * @description CoresController
 */

@RestController
public class CorsController {

	@GetMapping("/cors")
	String permit() {
		return "cors";
	}
}
