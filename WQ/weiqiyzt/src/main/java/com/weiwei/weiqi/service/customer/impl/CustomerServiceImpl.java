package com.weiwei.weiqi.service.customer.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weiwei.common.constants.SessionKeyConstants;
import com.weiwei.common.constants.enums.ResultCodeEnum;
import com.weiwei.common.exception.BusinessException;
import com.weiwei.common.util.PasswordUtil;
import com.weiwei.weiqi.jdbc.dao.customer.CustomerDao;
import com.weiwei.weiqi.jdbc.dbmodel.customer.Customer;
import com.weiwei.weiqi.request.base.BaseEnter.ClIENTTYPE;
import com.weiwei.weiqi.request.login.LoginEnter;
import com.weiwei.weiqi.response.base.GeneralResult;
import com.weiwei.weiqi.service.customer.api.CustomerService;
import com.weiwei.weiqi.service.redis.api.RedisService;

@Component
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private RedisService redisService;

	@Autowired
	private CustomerDao customerDao;

	public GeneralResult login(LoginEnter loginEnter) {
		String phoneNumber = loginEnter.getLoginMobile();
		String password = loginEnter.getPassword();

		/**
		 * 验证
		 */
		Customer customer = customerDao.findByPhone(phoneNumber);
		try {
			if (customer == null || !PasswordUtil.validatePassword(password, customer.getPassword(), customer.getSalt())) {
				throw new BusinessException(ResultCodeEnum.RESULT_ERROR_ACCOUNT);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}

		/**
		 * 登陆成功后续操作
		 */
		int customerId = customer.getId();
		loginEnter.setUserId(customerId);
		
		String loginSessionKey = loginEnter.getSessionKey();
		// 检查当前用户的其他登录信息
		String userSessionKey = SessionKeyConstants.USER_SESSION_KEY + customerId;
		String redisSession = redisService.getValue(userSessionKey);
		if (redisSession != null && !redisSession.equals(loginSessionKey)) {
			// 如果用户已有其他登录信息。则把其他登录信息踢下线,后续记录踢下线用户的 终端信息
			redisService.setSingleHash(redisSession, SessionKeyConstants.AUTH_KEY, Boolean.FALSE.toString(), SessionKeyConstants.SESSION_ACTIVE_SECONDS);

		}
		redisService.setValue(userSessionKey, loginSessionKey, SessionKeyConstants.SESSION_ACTIVE_SECONDS);

		// 会话信息加入redis 缓存
		Map<String, String> map = new HashMap<String, String>();
		map.put(SessionKeyConstants.USER_LOGIN_TIME, String.valueOf(System.currentTimeMillis()));
		map.put(SessionKeyConstants.USER_LAST_TIME, "0");
		map.put(SessionKeyConstants.AUTH_KEY, Boolean.TRUE.toString());
		map.put(SessionKeyConstants.USER_PHONE_KEY, phoneNumber);
		map.put(SessionKeyConstants.USER_ID_KEY, String.valueOf(customerId));

		redisService.setMultiHash(loginSessionKey, map, SessionKeyConstants.SESSION_ACTIVE_SECONDS);

		return new GeneralResult(ResultCodeEnum.RESULT_SUCCESS);
	}

}
