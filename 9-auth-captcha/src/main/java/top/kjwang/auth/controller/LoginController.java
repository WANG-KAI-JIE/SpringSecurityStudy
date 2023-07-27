package top.kjwang.auth.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.kjwang.auth.common.R;
import top.kjwang.auth.vo.CaptchaVO;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author kjwang
 * @date 2023/7/27 20:25
 * @description LoginController
 */

@Controller
@Slf4j
public class LoginController {

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@GetMapping("/generateCaptcha")
	@ResponseBody
	public R<CaptchaVO> getcaptcha() {
		// 定义图形验证码的长和宽
		LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200,100);
		String code = lineCaptcha.getCode(); // 验证码
		log.info("生成验证码：" + lineCaptcha.getCode());
		String imageBase64 = lineCaptcha.getImageBase64Data(); // 验证码图片BASE64
		// 创建验证码对象
		CaptchaVO captchaVO = new CaptchaVO();
		captchaVO.setId(UUID.randomUUID().toString());
		captchaVO.setBase64(imageBase64);
		// 缓存验证码，10分钟有效
		stringRedisTemplate.opsForValue().set(captchaVO.getId(),code,10, TimeUnit.MINUTES);
		return R.response(200,"生成验证码成功",captchaVO);
	}
}
