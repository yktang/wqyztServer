package com.weiwei.weiqi.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessControl {
	/**
	 * 
	 * @Description: 是否不需要登录状态。 默认false 表示需要
	 *
	 * @return boolean
	 *
	 * @version V1.0
	 *
	 */
	boolean noSessionLogin() default false;

}