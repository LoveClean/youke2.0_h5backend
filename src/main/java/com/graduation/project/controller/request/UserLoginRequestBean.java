package com.graduation.project.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Title: UserLoginRequestBean.java
 * @Package com.zhishangquan.server.controller.request
 * @Description: 登录RequestBean
 * @version V1.0
 */
@ApiModel
public class UserLoginRequestBean {
	/**
	 * 用户名
	 */
	@NotBlank(message = "手机号不能为空")
	@ApiModelProperty(value = "用户名，必填", required = true)
	private String loginName;

	/**
	 * 短信验证码
	 */
//	@NotBlank(message = "短信验证码不能为空")
//	@ApiModelProperty(value = "短信验证码，必填", required = true)
//	private String smsVerifyCode;
	
//	@NotBlank(message = "验证码不能为空")
//	@ApiModelProperty(value = "验证码，必填", required = true)
//	private String verifyCode;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

//	public String getSmsVerifyCode() {
//		return smsVerifyCode;
//	}
//
//	public void setSmsVerifyCode(String smsVerifyCode) {
//		this.smsVerifyCode = smsVerifyCode;
//	}

//	public String getVerifyCode() {
//		return verifyCode;
//	}
//
//	public void setVerifyCode(String verifyCode) {
//		this.verifyCode = verifyCode;
//	}
//
	

}
