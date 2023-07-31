package top.kjwang.oauth2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.kjwang.oauth2.entity.User;
import top.kjwang.oauth2.mapper.UserMapper;
import top.kjwang.oauth2.service.IUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kjwang
 * @since 2023-07-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
