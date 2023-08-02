package top.kjwang.auth.cloud.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author kjwang
 * @date 2023/7/31
 * @description ResultCodeEnum
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResultCodeEnum {
    FAIL(-1, "操作失败"),
    SUCCESS(200, "操作成功"),

    LOGOUT_SUCCESS(200, "注销成功"),
    AUTHENTICATION_SUCCESS(200, "登录成功"),
    NOT_AUTHENTICATION(401, "未认证请求"),
    ACCESS_DENIED(403, "权限不足，访问被拒绝");

    private int code;

    private String msg;

}
