package com.weiwei.weiqi.controller.aspect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.weiwei.common.Constants;
import com.weiwei.weiqi.processor.base.BaseProcessor;
import com.weiwei.weiqi.response.GeneralServiceResponse;

@Aspect
@Component
public class ControllerServiceAspect {
	private static final Logger logger = LoggerFactory.getLogger(ControllerServiceAspect.class);
	@Autowired
	public JdbcTemplate jdbcTemplate;
	/*
	@Before("execution(* com.weiwei.weiqi.controller.HomeController.*(..))")
	public void insertBefore(JoinPoint joinPoint) {
		logger.info("log Before " + joinPoint.getSignature().getName());
		logger.info("request object: ");
		for (Object object : joinPoint.getArgs()) {
			logger.info(object.toString());
		}
	}
	*/
	@Around("execution(* com.weiwei.weiqi.controller.HomeController.*(..))")
	public Object insertAround(ProceedingJoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		Map<String, Object> scopes = new HashMap<String, Object>();
		if (args != null && args.length > 0) {
			scopes.put(Constants.SERVICE_REQUEST, args[0]);
		}
		scopes.put(Constants.DAOOBJECT, jdbcTemplate);
		try {
			Object resultObject = joinPoint.proceed(args);
			logger.info("join point return object: " + resultObject.toString());
			if (resultObject instanceof BaseProcessor) {
				((BaseProcessor) resultObject).doProcess(scopes);
				GeneralServiceResponse<Object> serviceResponse = new GeneralServiceResponse<>();
				if(scopes.containsKey(Constants.SERVICE_RESPONSE)){
					serviceResponse.setResponseObjectList((ArrayList)scopes.get(Constants.SERVICE_RESPONSE));
				}
				return serviceResponse;
			} else {
				return resultObject;
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	@AfterReturning(pointcut = "execution(* com.weiwei.weiqi.controller.HomeController.*(..))", returning = "result")
	public void insertAfterReturn(JoinPoint joinPoint, Object result) {
		logger.info("log after " + joinPoint.getSignature().getName());
		logger.info("response object: " + result);
	}
	*/
}
