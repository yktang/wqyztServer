package com.weiwei.weiqi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingServiceAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingServiceAspect.class);
	
	@Before("execution(* com.weiwei.weiqi.*.*.*.*(..)) || execution(* com.weiwei.weiqi.*.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		logger.info("Execution Before: " + joinPoint.getSignature().getDeclaringTypeName() + ", " + joinPoint.getSignature().getName());
	}
	
	@AfterReturning(pointcut = "execution(* com.weiwei.weiqi.*.*.*(..)) || execution(* com.weiwei.weiqi.*.*.*.*(..))", returning = "result")
	public void insertAfterReturn(JoinPoint joinPoint, Object result) {
		logger.info("Execution After: " + joinPoint.getSignature().getDeclaringTypeName()  + ", " + joinPoint.getSignature().getName());
		logger.info("Return: " + result);
	}
	
}
