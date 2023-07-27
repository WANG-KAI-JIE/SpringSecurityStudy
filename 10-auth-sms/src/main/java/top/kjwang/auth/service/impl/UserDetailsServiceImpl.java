package top.kjwang.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.kjwang.auth.entity.User;
import top.kjwang.auth.security.MyUserDetails;
import top.kjwang.auth.service.IUserService;

import java.util.List;

/**
 * @author kjwang
 * @date 2023/7/27
 * @description UserDetailsServiceImpl
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final IUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 1. 数据库查询用户
		User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUserName, username));
		if (ObjectUtil.isNull(user)) {
			log.error("Query returned no results for user '" + username + "'");
			throw new UsernameNotFoundException(StrUtil.format("Username {} not found", username));
		} else {
			// 2. 设置权限集合，后续需要数据库查询（授权篇讲解）
			List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
			// 3. 返回UserDetails类型用户
			return new MyUserDetails(username, user.getPassword(), user.getPhone(), authorityList,
					true, true, true, true);
		}
	}

	public UserDetails loadUserByPhone(String phone) throws UsernameNotFoundException {
		// 1. 数据库查询用户
		User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
		if (ObjectUtil.isNull(user)) {
			log.error("Query returned no results for user '" + phone + "'");
			throw new UsernameNotFoundException(StrUtil.format("Phone {} not found", phone));
		} else {
			// 2. 设置权限集合，后续需要数据库查询
			List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
			// 3. 返回 UserDetails 类型用户
			return new MyUserDetails(user.getUserName(), null, user.getPhone(), authorityList,
					true, true, true, true);
		}
	}
}