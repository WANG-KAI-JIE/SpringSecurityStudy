package top.kjwang.auth.sms;

import lombok.Data;

/**
 * @author kjwang
 * @date 2023/7/27 22:04
 * @description SmsCaptchaVO
 */

@Data
public class SmsCaptchaVO {
	// 手机号
	private String phone;
	// 多少分钟后过期
	private Integer expire;
}
