package top.kjwang.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.kjwang.auth.entity.User;
import top.kjwang.auth.mapper.UserMapper;
import top.kjwang.auth.service.IUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kjwang
 * @since 2023-07-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
