package com.weiwei.weiqi.controller.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.weiwei.common.Servlets;
import com.weiwei.common.constants.Constants;
import com.weiwei.common.constants.SessionKeyConstants;
import com.weiwei.common.constants.enums.ResultCodeEnum;
import com.weiwei.common.exception.BusinessException;
import com.weiwei.common.util.StringUtils;
import com.weiwei.weiqi.annotation.AccessControl;
import com.weiwei.weiqi.request.base.BaseEnter;
import com.weiwei.weiqi.request.base.MyPageRequest;
import com.weiwei.weiqi.response.base.GeneralResult;
import com.weiwei.weiqi.service.redis.api.RedisService;

@Aspect
@Component
public class ControllerServiceAspect {
	private static final Logger logger = LoggerFactory.getLogger(ControllerServiceAspect.class);

	// @Autowired
	// public JdbcTemplate jdbcTemplate;
	/*
	 * @Before("execution(* com.weiwei.weiqi.controller.HomeController.*(..))")
	 * public void insertBefore(JoinPoint joinPoint) { logger.info("log Before "
	 * + joinPoint.getSignature().getName()); logger.info("request object: ");
	 * for (Object object : joinPoint.getArgs()) {
	 * logger.info(object.toString()); } }
	 */
	@Around("execution(* com.weiwei.weiqi.controller..*.*(..))")
	public Object insertAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		String requestPath = null; // 请求地址
		Map<String, String[]> inputParamMap = null; // 传入参数
		BaseEnter enter = null;
		String requestMethod = null;
		String requestHeader = null;
		String requestBody = null;
		Object result = null;

		Map<String, Object> scopes = new HashMap<String, Object>();
		if (args != null && args.length > 0) {
			scopes.put(Constants.SERVICE_REQUEST, args[0]);
		}
		// scopes.put(Constants.DAOOBJECT, jdbcTemplate);
		try {
			// 封装
			/**
			 * 获取request cookie 信息 封装至 enter
			 */
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			ServletRequestAttributes sra = (ServletRequestAttributes) ra;
			HttpServletRequest request = sra.getRequest();
			HttpServletResponse response = sra.getResponse();
			// 获取输入参数
			inputParamMap = request.getParameterMap();
			requestMethod = request.getMethod();
			requestHeader = getRequestHeaders(request);

			String contentType = request.getHeader("content-type");
			if (contentType != null && contentType.indexOf("multipart") != -1) {
				requestBody = request.getParameter("jsonData");
			}

			// 获取相关自定义注解
			Signature signature = joinPoint.getSignature();
			if (!(signature instanceof MethodSignature)) {
				result = joinPoint.proceed();
			}

			// 获取请求地址
			requestPath = request.getRequestURI();

			response.setHeader("Access-Control-Allow-Origin", "*");

			String sessionKey = Servlets.getCookie(request, Constants.SESSION_KEY);
			if (args != null && args.length > 0) {
				for (Object arg : args) {
					if (arg instanceof BaseEnter) {
						enter = (BaseEnter) arg;
						enter.setSessionKey(sessionKey);
						Cookie[] cookies = request.getCookies();

						// Map<String,Cookie> cookieMap = getCookieMap(cookies);
						// 获取cookie值赋值
						if (cookies != null) {
							for (Cookie cookie : cookies) {
								try {
									StringBuffer sb = new StringBuffer(cookie.getName());
									sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));// 暂时这样拼装方法
									sb.insert(0, "set");

									Class<?> type = null;
									Method m = null;
									Class<?> clz = enter.getClass();
									String enterName = BaseEnter.class.getName();
									while (clz != null) {
										if (clz.getName().equals(enterName)) {
											type = clz.getDeclaredField(cookie.getName()).getType();
											m = clz.getDeclaredMethod(sb.toString(), type);
											break;
										} else {
											clz = clz.getSuperclass();
										}
									}

									if (type.getName().equals("java.lang.String")) {
										m.invoke(enter, cookie.getValue());
									} else {
										m.invoke(enter, Integer.valueOf(cookie.getValue()));
									}
								} catch (NoSuchFieldException e) {
									// logger.warn("no field --> "+cookie.getName());
								}
							}
						}
					} else if (arg instanceof MyPageRequest) {
						MyPageRequest mpr = (MyPageRequest) arg;
						int page = 0;
						int pageSize = 10;
						String[] parmpage = inputParamMap.get(Constants.PAGE_NUM);
						String[] prampageSize = inputParamMap.get(Constants.PAGE_SIZE);
						if (parmpage != null && parmpage.length > 0 && StringUtils.isNotBlank(parmpage[0])) {
							page = Integer.valueOf(parmpage[0]) - 1;
						}
						if (prampageSize != null && prampageSize.length > 0 && StringUtils.isNotBlank(prampageSize[0])) {
							pageSize = Integer.valueOf(prampageSize[0]);
						}

						mpr.setPage(page < 0 ? 0 : page);
						mpr.setSize(pageSize < 1 ? 1 : pageSize);
					}
				}

			}
			// 录入请求IP
			enter.setEnterIp(Servlets.getRemoteAddr(request));
			MethodSignature methodSignature = (MethodSignature) signature;
			Method method = methodSignature.getMethod();

			AccessControl methodAccessControl = method.getDeclaredAnnotation(AccessControl.class);
			AccessControl classAccessControl = method.getDeclaringClass().getDeclaredAnnotation(AccessControl.class);
			if ((methodAccessControl != null && methodAccessControl.noSessionLogin()) || (classAccessControl != null && classAccessControl.noSessionLogin())) {
				result = joinPoint.proceed(args);
			} else {
				// 判断是否登录
				checkLogin(enter);
				result = joinPoint.proceed(args);
			}

			// 判断权限
		} catch (BusinessException be) {
			result = new GeneralResult(be);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = new GeneralResult(ResultCodeEnum.RESULT_SYSERROR);
		}

		// 保存日志

		return result;
	}

	/**
	 * 
	 * @Title: getRequestHeaders
	 * @Description: 获取请求头信息
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	private String getRequestHeaders(HttpServletRequest request) {
		StringBuffer headerString = new StringBuffer();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			headerString.append(key).append(":").append(value).append("\n");
		}
		return headerString.toString();
	}

	/**
	 * 
	 * @Title: checkLogin
	 * @Description: 判断用户是否登录
	 * @param @param enter
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	private boolean checkLogin(BaseEnter enter) {
		boolean resetTime = false;
		// logger.info("enter.getSessionKey() "+enter.getSessionKey());
		if (enter == null || StringUtils.isBlank(enter.getSessionKey())) {
			throw new BusinessException(ResultCodeEnum.RESULT_ERROR_SESSION_TIME_OUT);
		} else {
			// 获取当前用户 信息
			List<Object> list = new ArrayList<Object>();
			list.add(SessionKeyConstants.USER_ID_KEY);
			list.add(SessionKeyConstants.USER_PHONE_KEY);
			list.add(SessionKeyConstants.AUTH_KEY);
			list.add(SessionKeyConstants.USER_LAST_TIME);

			List<Object> hashresult = redisService.getMultiHash(enter.getSessionKey(), list);
			if (hashresult == null || hashresult.get(0) == null) {
				throw new BusinessException(ResultCodeEnum.RESULT_ERROR_SESSION_TIME_OUT);
			} else {
				Object userId = hashresult.get(0);
				Object regPhone = hashresult.get(1);
				Object sessionAuth = hashresult.get(2);
				Object lastTime = hashresult.get(3);

				if (userId != null) {
					enter.setUserId(Integer.valueOf(String.valueOf(userId)));
				}
				enter.setMobileNum(String.valueOf(regPhone));

				String authString = String.valueOf(sessionAuth);

				if (authString.equals(Boolean.TRUE.toString())) {
					enter.setUserAuth(true);
				} else {
					// 查看当前账号的其他登录信息 会话信息
					String userSessionKey = SessionKeyConstants.USER_SESSION_KEY + userId;
					String loginSessionKey = redisService.getValue(userSessionKey);
					if (loginSessionKey != null) {
						Object loginTime = redisService.getSingleHash(loginSessionKey, SessionKeyConstants.USER_LOGIN_TIME);
						if (loginTime != null) {
							String loginTimeString = "";
							// MyDateFormatUtils.toTimeString(Long.parseLong(String.valueOf(loginTime)));
							// 查找登录时间
							ResultCodeEnum res = ResultCodeEnum.RESULT_ERROR_SESSION_KICK_OUT;
							throw new BusinessException(res.getCode(), "你的账号" + loginTimeString + "在其他设备登录，请重新登陆");
						}
					}
					logger.warn(" RESULT_ERROR_SESSION_TIME_OUT-->>" + enter.getSessionKey());
					throw new BusinessException(ResultCodeEnum.RESULT_ERROR_SESSION_TIME_OUT);
				}
			}
		}

		return resetTime;
	}

	/*
	 * @AfterReturning(pointcut =
	 * "execution(* com.weiwei.weiqi.controller.HomeController.*(..))",
	 * returning = "result") public void insertAfterReturn(JoinPoint joinPoint,
	 * Object result) { logger.info("log after " +
	 * joinPoint.getSignature().getName()); logger.info("response object: " +
	 * result); }
	 */

	@Autowired
	private RedisService redisService;
}
