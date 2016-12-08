package com.weiwei.weiqi.service.announcement.api;

import com.weiwei.common.exception.BusinessException;
import com.weiwei.weiqi.request.announcement.ContentEnter;
import com.weiwei.weiqi.request.announcement.HtmlsEnter;
import com.weiwei.weiqi.request.base.BaseEnter;
import com.weiwei.weiqi.request.base.MyPageRequest;
import com.weiwei.weiqi.response.base.GeneralResult;

public interface HtmlsService {
	/**
	  * 
	  * @Title: list   
	  * @Description: 公告栏列表
	  * @param @param pageRequest
	  * @param @param enter
	  * @param @return   
	  * @return GeneralResult    
	  * @throws
	  */
	GeneralResult list(MyPageRequest pageRequest, HtmlsEnter enter) throws BusinessException;
	
	/**
	  * 
	  * @Title: content   
	  * @Description: 公告内容
	  * @param @param enter
	  * @param @return   
	  * @return GeneralResult    
	  * @throws
	  */
	GeneralResult content(ContentEnter enter) throws BusinessException;

}
