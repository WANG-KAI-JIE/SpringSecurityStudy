package top.kjwang.oauth2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.kjwang.oauth2.entity.UserBindThirdLogin;

import java.util.List;

/**
 * @author kjwang
 * @date 2023/7/31
 * @description IUserBindThirdLoginService
 **/
public interface IUserBindThirdLoginService extends IService<UserBindThirdLogin> {

    /**
     * 根据用户ID查询所有第三方平台绑定关系
     *
     * @param userId 用户ID
     * @return 绑定
     */
    List<UserBindThirdLogin> selectListByUserId(Long userId);

    /**
     * 根据第三方平台类型 + 第三方用户ID 查询绑定的当前平台用户ID
     */
    UserBindThirdLogin selectOne(String type, String thirdUserId);

    /**
     * 删除绑定
     */
    void removeBind(String type, String userId);
}