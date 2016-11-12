package com.weiwei.weiqi.processor.factory;

import com.weiwei.common.ProcessorEnum;
import com.weiwei.weiqi.processor.base.BaseProcessor;
import com.weiwei.weiqi.processor.processors.*;

public class ProcessorFactory {
	private BaseProcessor processor;
	public BaseProcessor getProcessor(ProcessorEnum en) {
		switch(en) {
			case ANNOUNCEMENT:
				processor = new AnnouncementProcessor();
				break;
			case PROVINCE:
				processor = new GetProvinceListProcessor();
				break;
			case CITY:
				processor = new GetCityListProcessor();
				break;
			default:
				break;
		}
		return processor;
	}
}
