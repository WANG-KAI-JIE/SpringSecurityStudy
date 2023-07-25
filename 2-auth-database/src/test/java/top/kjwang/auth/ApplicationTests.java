package top.kjwang.auth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import top.kjwang.auth.entity.User;
import top.kjwang.auth.service.IUserService;

@SpringBootTest
class ApplicationTests {
	@Autowired
	private IUserService userService;

	@Test
	@DisplayName("根据用户名查询用户")
	void testMp() {
		User admin = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUserName, "admin"));
		System.out.println(admin);
	}

	@Test
	@DisplayName("插入一条用户数据")
	void insertUserTest() {
		User user = new User();
		user.setUserName("admin1");
		user.setPassword(new BCryptPasswordEncoder().encode("123456"));
		user.setLoginName("管理员");
		user.setPhone("13688888888");
		userService.save(user);
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
}