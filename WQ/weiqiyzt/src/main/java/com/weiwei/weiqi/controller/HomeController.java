package com.weiwei.weiqi.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.weiwei.common.ProcessorEnum;
import com.weiwei.weiqi.beans.BeanProcessorFactory;
import com.weiwei.weiqi.beans.VersionBean;
import com.weiwei.weiqi.processor.factory.ProcessorFactory;
import com.weiwei.weiqi.request.AnnouncementRequest;
import com.weiwei.weiqi.response.bean.CommentFetchRequest;

@RestController
@RequestMapping("/weiwei")
public class HomeController {
	
	private ProcessorFactory processorFactory = new ProcessorFactory();//这个取消掉，之后都用BeanProcessorFactory
	@Autowired
	private VersionBean versionBean;
	@Autowired
	private BeanProcessorFactory beanProcessorFactory;

	@RequestMapping("/hello")
	public String hello() {
		return "Hello!";
	}
	
	@RequestMapping(value="/version")
    public String versionService(Locale locale, Model model) {
    	return versionBean.getVersion();
    }
	
	@RequestMapping(value="/android/version")
    public String androidVersionService(Locale locale, Model model) {
    	return versionBean.getAndroidVersion();
    }
	
	@RequestMapping(value="/centreservice/announcement",  method = RequestMethod.POST)
	public Object announcementService(@RequestBody AnnouncementRequest serviceRequest) {
		return processorFactory.getProcessor(ProcessorEnum.ANNOUNCEMENT);
	}
	
	@RequestMapping(value="/province")
	public Object getProvinceList(){
		return beanProcessorFactory.getBean(ProcessorEnum.PROVINCE);
	}
	
	@RequestMapping(value="/city")
	public Object getCityList(@RequestBody int provinceId){
		return beanProcessorFactory.getBean(ProcessorEnum.CITY);
	}
	
	@RequestMapping(value="/announce/comment")
	public Object commentAnnounceService(@RequestBody CommentFetchRequest serviceRequest){
		return beanProcessorFactory.getBean(ProcessorEnum.COMMENTFETCH);
	}
}
