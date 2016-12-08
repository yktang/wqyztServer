package com.weiwei.weiqi.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weiwei.weiqi.annotation.AccessControl;
import com.weiwei.weiqi.request.base.BaseEnter;
import com.weiwei.weiqi.request.register.CompleteRegSubmitEnter;
import com.weiwei.weiqi.request.register.PreRegEnter;
import com.weiwei.weiqi.response.base.GeneralResult;
import com.weiwei.weiqi.service.customer.api.RegisterService;

@AccessControl(noSessionLogin = true)
@RestController
@RequestMapping("/reg")
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	/**
	 * 
	  * @Title: getRegSMSCaptcha   
	  * @Description: 获取短信验证码
	  * @param @param enter
	  * @param @return   
	  * @return GeneralResult    
	  * @throws
	 */
	@RequestMapping(value = "/getRegSMSCaptcha")
	public GeneralResult getRegSMSCaptcha(BaseEnter enter) {
		return registerService.getRegSMSCaptcha(enter);
	}

	/**
	 * 
	 * @Title: validationRegSMS
	 * @Description: 校验短信验证码
	 * @param @param preRegEnter
	 * @param @return
	 * @return GeneralResult
	 * @throws
	 */
	@RequestMapping(value = "/validationRegSMS")
	public GeneralResult validationRegSMS(PreRegEnter preRegEnter) {
		return registerService.validationRegSmsCaptcha(preRegEnter);
	}
	
	/**
	  * 
	  * @Title: completeSubmit   
	  * @Description: 提交用户信息
	  * @param @param completeRegSubmitEnter
	  * @param @param baseEnter
	  * @param @return   
	  * @return GeneralResult    
	  * @throws
	  */
	@RequestMapping(value = "/completeSubmit")
	public GeneralResult completeSubmit(
			@RequestBody CompleteRegSubmitEnter completeRegSubmitEnter,
			BaseEnter baseEnter) {
		return registerService
				.completeSubmit(completeRegSubmitEnter, baseEnter);
	}

}
