package top.kjwang.auth.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kjwang
 * @date 2023/7/27 20:15
 * @description CaptchaVO
 */

@Data
public class CaptchaVO implements Serializable {
	// 唯一ID
	private String id;
	// 验证码图片 Base64
	private String base64;
}
