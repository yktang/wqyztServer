package com.weiwei.weiqi.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weiwei.common.ProcessorEnum;
import com.weiwei.weiqi.processor.base.BaseProcessor;
import com.weiwei.weiqi.processor.processors.*;

@Component
public class BeanProcessorFactory {
	@Autowired
	protected GetCityListProcessor getCityListrocessor;
	@Autowired
	protected GetProvinceListProcessor getProvinceListProcessor;
	@Autowired
	protected CommentFetchProcessor commentFetchProcessor;
	
	public BaseProcessor getBean(ProcessorEnum en) {
		switch(en) {
		case PROVINCE:
			return getProvinceListProcessor;
		case CITY:
			return getCityListrocessor;
		case COMMENTFETCH:
			return commentFetchProcessor;
		default:
			break;
		}
		return null;
	}
}
