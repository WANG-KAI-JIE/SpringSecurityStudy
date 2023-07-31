package top.kjwang.oauth2.controller;

import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import top.kjwang.oauth2.entity.UserBindThirdLogin;
import top.kjwang.oauth2.service.IUserBindThirdLoginService;
import top.kjwang.oauth2.utils.SecurityContextHolderUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kjwang
 * @date 2023/7/31
 * @description UserBindThirdLoginController
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserBindThirdLoginController {

    private final IUserBindThirdLoginService userBindThirdLoginService;

    private final InMemoryClientRegistrationRepository clientRegistrationRepository;

    /**
     * 查询当前用户未绑定的第三方
     */
    @GetMapping("/queryUnbind")
    public Object queryUserUnbind() {
        return selectUserUnbind();
    }

    /**
     * 查询当前用户已经绑定的第三方
     */
    @GetMapping("/queryBind")
    public Object queryBind() {
        return userBindThirdLoginService.selectListByUserId(SecurityContextHolderUtils.getUserId());
    }

    /**
     * 删除绑定
     */
    @GetMapping("/removeBind")
    @Transactional
    public Object queryUserUnbind(@RequestParam Long id) {
        userBindThirdLoginService.removeById(id);
        return "操作成功";
    }

    @Resource
    private OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;

    @GetMapping("/bind")
    @Transactional
    public Object bind(@RequestParam Long userId, String type, HttpServletRequest request) {
        UserBindThirdLogin bind = new UserBindThirdLogin();
        bind.setUserId(userId);
        // 查询会话中已认证的第三方用户信息
        OAuth2AuthorizedClient oAuth2AuthorizedClient = oAuth2AuthorizedClientRepository
                .loadAuthorizedClient(type, SecurityContextHolderUtils.getAuthentication(), request);
/*        oAuth2AuthorizedClient.
        userBindThirdLoginService.save()*/
        return "操作成功";
    }


    public Object queryUserBind() {
        return null;
    }

    private List<String> selectUserUnbind() {
        List<String> allList = new ArrayList<>();
        // 1. 查询系统当前支持的所有第三方平台
        for (ClientRegistration client : clientRegistrationRepository) {
            String registrationId = client.getRegistrationId();
            allList.add(registrationId);
        }
        // 2. 查询当前用户未绑定的平台
        Long userId = SecurityContextHolderUtils.getUserId();
        List<UserBindThirdLogin> userBindThirdLogins = userBindThirdLoginService.selectListByUserId(userId);
        if (CollUtil.isNotEmpty(userBindThirdLogins)) {
            // 已绑定的平台
            List<String> bindList = userBindThirdLogins.stream().map(UserBindThirdLogin::getType).toList();
            // 未绑定的平台
            return CollUtil.disjunction(allList, bindList).stream().toList();
        }
        return allList;
    }
}
