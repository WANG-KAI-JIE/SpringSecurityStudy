package top.kjwang.auth.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kjwang
 * @date 2023/7/31
 * @description SessionController
 **/
@RestController
public class SessionController {

    @GetMapping("/test")
    public Object test() {
        return "success";
    }
}
