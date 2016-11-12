package com.weiwei.weiqi.processor.processors;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.weiwei.common.Constants;
import com.weiwei.weiqi.jdbc.dao.IAnnouncementDAO;
import com.weiwei.weiqi.jdbc.dao.impl.AnnounceDAOImpl;
import com.weiwei.weiqi.jdbc.dbmodel.TableAnnounce;
import com.weiwei.weiqi.processor.base.BaseProcessor;
import com.weiwei.weiqi.request.AnnouncementRequest;
import com.weiwei.weiqi.response.bean.AnnouncementBean;

public class AnnouncementProcessor extends BaseProcessor {

	private AnnouncementRequest request;
	private List<?> announcementList_db;
	private AnnounceDAOImpl announcementDAOImpl;
	private List<AnnouncementBean> announcementList_response;
	
	@Override
	protected void preProcess(Map<String, Object> scopes){
		request = (AnnouncementRequest)scopes.get(Constants.SERVICE_REQUEST);
		announcementDAOImpl = new AnnounceDAOImpl();
		announcementDAOImpl.setJdbcTemplate((JdbcTemplate)scopes.get(Constants.DAOOBJECT));
	}
	
	@Override
	protected String executeProcess(Map<String, Object> scopes) {
		if(request.getUrl() != null && !"".equalsIgnoreCase(request.getUrl().trim())){
			announcementList_db = announcementDAOImpl.findByUrl(request.getUrl());
			return Constants.EVENT_ANNOUNCE_BODY;
		}
		else if(request.getStartIndex() >= 0 && request.getEndIndex() >= 0){
			if(request.getEndIndex() >= request.getEndIndex()){
				announcementList_db = announcementDAOImpl.findBySequenceId(request.getEndIndex(), request.getEndIndex());
				return Constants.EVENT_ANNOUNCE_LIST;
			}
		}
		return Constants.EVENT_FAIL;
	}
	
	@Override
	protected String postProcess(Map scopes, String event){
		announcementList_response = new ArrayList<AnnouncementBean>();
		for(int i=0; i<announcementList_db.size(); i++){
			TableAnnounce announce = (TableAnnounce)announcementList_db.get(i);
			if(Constants.EVENT_ANNOUNCE_LIST.equalsIgnoreCase(event)){
				AnnouncementBean announcement = new AnnouncementBean();
				Field[] fields = announcement.getClass().getDeclaredFields();
				for (Field field : fields) {
					try {
						String fieldName = field.getName();
						Field fieldtableann = announce.getClass().getField(fieldName);
						field.set(announcement, fieldtableann.get(announce));
					} catch(Exception ex) {
						ex.printStackTrace();
					}
				}
				//announcement.fillPublishTime();
				announcementList_response.add(announcement);
			}
			else if(Constants.EVENT_ANNOUNCE_BODY.equalsIgnoreCase(event)){
				AnnouncementBean announcement = new AnnouncementBean();
				//announcement.fillBody(announce.getResult());
				//announcement.setAid(announce.getAid());
				announcementList_response.add(announcement);
			}
		}
		
		scopes.put(Constants.SERVICE_RESPONSE, announcementList_response);
		return event;
	}

}
