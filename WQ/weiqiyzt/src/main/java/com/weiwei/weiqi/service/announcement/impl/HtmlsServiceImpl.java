package com.weiwei.weiqi.service.announcement.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.weiwei.common.constants.enums.ResultCodeEnum;
import com.weiwei.common.exception.BusinessException;
import com.weiwei.weiqi.jdbc.dao.annoucement.CommentSessionDao;
import com.weiwei.weiqi.jdbc.dao.annoucement.HtmlsDao;
import com.weiwei.weiqi.jdbc.dbmodel.annoucement.CommentSession;
import com.weiwei.weiqi.jdbc.dbmodel.annoucement.Htmls;
import com.weiwei.weiqi.request.announcement.ContentEnter;
import com.weiwei.weiqi.request.announcement.HtmlsEnter;
import com.weiwei.weiqi.request.base.BaseEnter;
import com.weiwei.weiqi.request.base.MyPageRequest;
import com.weiwei.weiqi.response.annoucement.AnnouncementBean;
import com.weiwei.weiqi.response.annoucement.ContentResult;
import com.weiwei.weiqi.response.annoucement.HtmlListResult;
import com.weiwei.weiqi.response.base.DataResult;
import com.weiwei.weiqi.response.base.GeneralResult;
import com.weiwei.weiqi.service.announcement.api.HtmlsService;
import com.weiwei.weiqi.service.base.BaseServiceImpl;

@Component
public class HtmlsServiceImpl extends BaseServiceImpl implements HtmlsService {

	@Autowired
	private HtmlsDao htmlsDao;

	@Autowired
	private CommentSessionDao commentSessionDao;
	
	@Override
	public GeneralResult list(MyPageRequest pageRequest, HtmlsEnter enter) {
		List<Htmls> list = getList(pageRequest, enter);
		List<HtmlListResult> results = new ArrayList<HtmlListResult>();
		for (Htmls htmls : list) {
			HtmlListResult bean = new HtmlListResult();
			bean.setAid(htmls.getAid());
			bean.setPublishTime(htmls.getUrl());
			bean.setTitle(htmls.getTitle());
			results.add(bean);
		}		
		return new DataResult<List<HtmlListResult>>(ResultCodeEnum.RESULT_SUCCESS, results);
	}

	@Override
	public GeneralResult content(ContentEnter enter) {
		Htmls htmls = htmlsDao.getOne(enter.getAid());
		if(htmls == null){
			throw new BusinessException(ResultCodeEnum.RESULT_ERROR_PARM);
		}
		ContentResult result = new ContentResult();
		result.setAid(htmls.getAid());
		result.setFillBody(htmls.getResult());
		return new DataResult<ContentResult>(ResultCodeEnum.RESULT_SUCCESS, result);
	}

	private List<Htmls> getList(MyPageRequest pageRequest, HtmlsEnter enter){
		Map<String, String[]> params = new HashMap<String, String[]>();
		if(enter.getTitle()!=null){
			params.put("CONTAIN_title", new String[]{enter.getTitle()});
		}
		Pageable pageable = getPageable(pageRequest, new Sort(Direction.DESC, "url"));
		Specification<Htmls> spec = spec(params, Htmls.class);
		Page<Htmls> page = htmlsDao.findAll(spec, pageable);
		return page.getContent();
	}
}
