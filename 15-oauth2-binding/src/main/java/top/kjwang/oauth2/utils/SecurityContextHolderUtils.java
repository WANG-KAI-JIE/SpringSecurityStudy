package top.kjwang.oauth2.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import top.kjwang.oauth2.security.MyUserDetails;

/**
 * @author kjwang
 * @date 2023/7/31
 * @description SecurityContextHolderUtils
 **/
public class SecurityContextHolderUtils {

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        return getCurrentLoginUserInfo().getUserId();
    }

    /**
     * 获取认证对象
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前登录用户信息
     */
    public static MyUserDetails getCurrentLoginUserInfo() {
        return (MyUserDetails) getAuthentication().getPrincipal();
    }
}