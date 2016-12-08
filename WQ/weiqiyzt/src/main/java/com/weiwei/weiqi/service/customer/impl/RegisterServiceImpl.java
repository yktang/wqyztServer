package com.weiwei.weiqi.service.customer.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Couchbase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.weiwei.common.constants.Constants;
import com.weiwei.common.constants.SessionKeyConstants;
import com.weiwei.common.constants.enums.ResultCodeEnum;
import com.weiwei.common.exception.BusinessException;
import com.weiwei.common.util.PasswordUtil;
import com.weiwei.weiqi.jdbc.dao.customer.CustomerDao;
import com.weiwei.weiqi.jdbc.dbmodel.customer.Customer;
import com.weiwei.weiqi.request.base.BaseEnter;
import com.weiwei.weiqi.request.register.CompleteRegSubmitEnter;
import com.weiwei.weiqi.request.register.PreRegEnter;
import com.weiwei.weiqi.response.base.GeneralResult;
import com.weiwei.weiqi.service.customer.api.RegisterService;
import com.weiwei.weiqi.service.message.sms.SendSMSService;
import com.weiwei.weiqi.service.redis.api.RedisService;

@Component
public class RegisterServiceImpl extends AbstractCustomerService implements
		RegisterService {

	@Autowired
	private SendSMSService sendSMSService;

	@Autowired
	private CustomerDao customerDao;

	private final Logger logger = LoggerFactory
			.getLogger(RegisterServiceImpl.class);

	/**
	 * 注册验证码超阀值锁定时间 超阀值锁定6小时
	 */
	private static final int REGSMSC_SECONDS = REGSMSC_LOCK_HOURS * 3600;

	@Autowired
	private RedisService redisService;

	@Override
	public GeneralResult getRegSMSCaptcha(BaseEnter preRegEnter) {
		String sessionKey = preRegEnter.getSessionKey();
		String mobileNum = preRegEnter.getMobileNum();
		isRegistered(mobileNum);
		String smsIRedisKey = getSMSCIRedisKey(mobileNum);
		long sendInteval = redisService.valueIncrement(smsIRedisKey,
				INCREMENT_VALUE, REGSMSC_INTEVAL);
		if (sendInteval > 1) {
			throw new BusinessException(ResultCodeEnum.RESULT_ERROR_SMS_INTEVAL);
		}
		String smsTRedisKey = getSMSCTRedisKey(mobileNum);
		long sendTims = redisService.valueIncrement(smsTRedisKey,
				INCREMENT_VALUE, REGSMSC_SECONDS);
		if (sendTims > REGSMSC_TIMES) {
			throw new BusinessException(ResultCodeEnum.RESULT_ERROR_SMS_TIMES);
		} else {
			Random random = new Random();
	    	StringBuilder rvalue = new StringBuilder();
	    	for(int i=0; i<6; i++){
	    		rvalue.append(random.nextInt(10));
	    	}
			// 验证码放进 redis缓存
			String smsCRedisKey = getSMSCRedisKey(mobileNum, sessionKey);
			redisService.setSingleHash(smsCRedisKey, SMSCODE,
					String.valueOf(rvalue), EXPIRED_SECONDS);
			int smsidx = getIndex(); // 获取短信次数
			sendSMSService.sendRegSMS(mobileNum,rvalue.toString());
		}

		return new GeneralResult(ResultCodeEnum.RESULT_SUCCESS);
	}

	@Override
	public GeneralResult validationRegSmsCaptcha(PreRegEnter preRegEnter) {
		String mobileNum = preRegEnter.getMobileNum();
		String sessionKey = preRegEnter.getSessionKey();
		String smsCRedisKey = getSMSCRedisKey(mobileNum, sessionKey);
		Object rvalue = redisService.getSingleHash(smsCRedisKey, SMSCODE);

		if (rvalue == null || !rvalue.equals(preRegEnter.getCaptchaCode())) {
			// 如果 redis内无 注册验证码信息 或与 用户发过来的验证码不一致 返回注册验证码失败
			throw new BusinessException(ResultCodeEnum.RESULT_ERROR_SMS_CAPTCHA);
		} else {
			// 设置 这个会话 验证码已验证通过
			Map<String, String> map = new HashMap<String, String>();
			map.put(SessionKeyConstants.AUTH_KEY, Boolean.TRUE.toString());
			map.put(SessionKeyConstants.USER_PHONE_KEY,
					preRegEnter.getMobileNum());
			redisService.setMultiHash(sessionKey, map,
					SessionKeyConstants.SESSION_ACTIVE_SECONDS);
			return new GeneralResult(ResultCodeEnum.RESULT_SUCCESS);
		}
	}

	@Override
	@Transactional
	public GeneralResult completeSubmit(
			CompleteRegSubmitEnter completeRegSubmitEnter, BaseEnter baseEnter){
		String sessionKey = baseEnter.getSessionKey();
		String phone = baseEnter.getMobileNum();
		isRegistered(phone);
		
		Map<Object, Object> map = redisService.getHashMap(sessionKey);
		if (map.isEmpty()) {
			throw new BusinessException(ResultCodeEnum.RESULT_ERROR_SMS_CAPTCHA);
		}
		if ("false".equals(map.get(SessionKeyConstants.AUTH_KEY))) {
			throw new BusinessException(ResultCodeEnum.RESULT_ERROR_SMS_EXPIRED);
		}

		// 保存用户	
		try {
			Customer customers = new Customer();
			BeanUtils.copyProperties(completeRegSubmitEnter, customers);
			customers.setMobilePhonenumber(phone);
			String[] hashsalt;
			hashsalt = createHash(completeRegSubmitEnter.getPassword());
			String hash = hashsalt[0];
			String salt = hashsalt[1];
			customers.setPassword(hash);
			customers.setSalt(salt);
			customerDao.save(customers);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return new GeneralResult(ResultCodeEnum.RESULT_SUCCESS);
	}

	/**
	 *
	 * @Title: isRegistered
	 * @Description: 判断用户是否注册过
	 * @param @param phone
	 * @return void
	 * @throws
	 */
	private void isRegistered(String phone) {
		Customer customer = customerDao.findByPhone(phone);
		if (customer != null) {
			throw new BusinessException(
					ResultCodeEnum.RESULT_ERROR_REG_DUPLICATE);
		}
	}

	
	private String[] createHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException{
		return  PasswordUtil.createHash(password);
	}
	
	@Override
	protected String getRedisKey() {
		// TODO Auto-generated method stub
		return Constants.REG_REDIS_KEY;
	}

}
