package top.kjwang.auth.sms;

import cn.hutool.core.util.RandomUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.kjwang.auth.common.R;

import java.util.concurrent.TimeUnit;

/**
 * @author kjwang
 * @date 2023/7/27 22:05
 * @description SmsController
 */

@RestController
@RequestMapping("/sms")
@Slf4j
public class SmsController {
	@Resource
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 发送验证码接口
	 */
	@GetMapping("/send/captcha")
	public R<SmsCaptchaVO> smsCaptcha(@RequestParam String phone) {
		// 1. 获取手机号
		log.info(phone + "请求获取验证码");
		// 2. 模拟调用短信平台获取验证码，以手机号为 KEY，存入 Redis，过期时间 5 分钟
		String code = RandomUtil.randomNumbers(6);
		stringRedisTemplate.opsForValue().set(phone,code,60 * 5, TimeUnit.SECONDS);
		log.info(phone + "生成验证码：" + code);
		// 3. 返回过期时间，方便前端提示多少时间内有效
		SmsCaptchaVO smsCaptchaVO = new SmsCaptchaVO();
		smsCaptchaVO.setPhone(phone);
		smsCaptchaVO.setExpire(5);
		return R.response(200,"短信验证码发送成功",smsCaptchaVO);
	}
}
