package com.weiwei.weiqi.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/weiwei")
public class HomeController {
	
//	@Autowired
//	private VersionBean versionBean;
//	@Autowired
//	private BeanProcessorFactory beanProcessorFactory;
//	@Autowired
//	private AnnouncementProcessor announcementProcessor;
//	
//	@RequestMapping("/hello")
//	public String hello() {
//		return "Hello!";
//	}
//	
//	@RequestMapping(value="/version")
//    public String versionService(Locale locale, Model model) {
//    	return versionBean.getVersion();
//    }
//	
//	@RequestMapping(value="/android/version")
//    public String androidVersionService(Locale locale, Model model) {
//    	return versionBean.getAndroidVersion();
//    }
//	
//	@RequestMapping(value="/centreservice/announcement")
//	public Object announcementService(AnnouncementRequest serviceRequest) {
//		return beanProcessorFactory.getBean(ProcessorEnum.ANNOUNCEMENT);
//	}
//	
//	@RequestMapping(value="/province")
//	public Object getProvinceList(){
//		return beanProcessorFactory.getBean(ProcessorEnum.PROVINCE);
//	}
//	
//	@RequestMapping(value="/city")
//	public Object getCityList( int provinceId){
//		return beanProcessorFactory.getBean(ProcessorEnum.CITY);
//	}
//	
//	@RequestMapping(value="/announce/comment")
//	public Object commentAnnounceService(@RequestBody CommentFetchRequest serviceRequest){
//		return beanProcessorFactory.getBean(ProcessorEnum.COMMENTFETCH);
//	}
}
