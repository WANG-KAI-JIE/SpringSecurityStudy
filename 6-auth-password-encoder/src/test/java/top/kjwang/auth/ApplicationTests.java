package top.kjwang.auth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import top.kjwang.auth.entity.User;
import top.kjwang.auth.service.IUserService;

@SpringBootTest
class ApplicationTests {
	@Autowired
	private IUserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	@DisplayName("根据用户名查询用户")
	void testMp() {
		User admin = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUserName, "admin"));
		System.out.println(admin);
	}

	//	插入两种不同算法加密的用户插入两种不同算法加密的用户
	@Test
	@DisplayName("插入用户数据")
	void insertUserTest() {
		User user = new User();
		user.setUserName("bcrypt");
		// 使用 bcrypt 加密
		user.setPassword(new BCryptPasswordEncoder().encode("123456"));
		user.setLoginName("bcrypt");
		user.setPhone("13688888888");
		userService.save(user);

		User user2 = new User();
		user2.setUserName("argon2");
		// 使用 argon2 加密
		Argon2PasswordEncoder arg2SpringSecurity = new Argon2PasswordEncoder(16, 32, 1, 65536, 10);
		user2.setPassword(arg2SpringSecurity.encode("123456"));
		user2.setLoginName("argon2");
		user2.setPhone("13699999999");
		userService.save(user2);
	}

	@Test
	@DisplayName("根据手机号和密码登录")
	void loginTest() {
		User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getPhone, "13688888888"));
		if ("$2a$10$hIrKUocf/IabC5snTdR4Z.cuF.QhEvZJZHUEzQ8i8gWMGjTs5GN3i".equals(user.getPassword())) {
			System.out.println("登录成功");
		} else {
			System.out.println("登录失败");
		}
	}

	@Test
	@DisplayName("根据加密方式查询用户")
	void passwordEncodeTest() {
		User admin = userService.getOne(new LambdaQueryWrapper<User>().like(User::getPassword, "{argon2}"));
		if (admin != null) {
			System.out.println(admin);
		} else {
			System.out.println("没找到");
		}
	}

//	@Test
//	@DisplayName("新增用户，密码添加算法前缀")
//	void insertUserPasswordEncoder() {
//		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		User user = new User();
//		user.setUserName("zhangsan");
//		// 使用加密：{bcrypt} 中的算法，可以从配置文件读取
//		user.setPassword( passwordEncoder.encode("{bcrypt}123456"));
//		user.setLoginName("zhangsan");
//		user.setPhone("13911112222");
//		userService.save(user);
//	}

//	@Test
//	@DisplayName("新增用户，注入 PasswordEncoder")
//	void insertUserPasswordEncoder() {
//		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		User user = new User();
//		user.setUserName("lisi");
//		user.setPassword("123456");
//		user.setPassword( passwordEncoder.encode("123456"));
//		user.setLoginName("lisi");
//		user.setPhone("13911113333");
//		userService.save(user);
//	}

	@Test
	@DisplayName("新增用户，注入 PasswordEncoder")
	void insertUserPasswordEncoder() {
		User user = new User();
		user.setUserName("lisi");
		user.setPassword(passwordEncoder.encode("123456"));
		user.setLoginName("lisi");
		userService.save(user);
	}
}