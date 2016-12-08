package com.weiwei.weiqi.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weiwei.weiqi.annotation.AccessControl;
import com.weiwei.weiqi.request.login.LoginEnter;
import com.weiwei.weiqi.response.base.GeneralResult;
import com.weiwei.weiqi.service.customer.api.CustomerService;

@AccessControl(noSessionLogin = true)
@RestController
@RequestMapping("/custom")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@RequestMapping(value = "/login")
	public GeneralResult login(LoginEnter loginEnter){
		return customerService.login(loginEnter);
	}
}
