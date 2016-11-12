package com.weiwei.weiqi.processor.processors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.weiwei.common.Constants;
import com.weiwei.weiqi.jdbc.dao.ICommentDAO;
import com.weiwei.weiqi.jdbc.dbmodel.CommentTableJoinBean;
import com.weiwei.weiqi.processor.base.BaseProcessor;
import com.weiwei.weiqi.response.bean.CommentBean;
import com.weiwei.weiqi.response.bean.CommentFetchRequest;

@Component
public class CommentFetchProcessor extends BaseProcessor {
	
	private CommentFetchRequest request;
	private List<CommentBean> result_list;
	private boolean moreComment;
	@Autowired
	private ICommentDAO daoImpl;
	
	@Override
	protected void preProcess(Map scopes){
		request = (CommentFetchRequest)scopes.get(Constants.SERVICE_REQUEST);
		if(request!= null && request.getStartId() > 0){
			moreComment = true;
		} else {
			moreComment = false;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected String executeProcess(Map scopes) {
		
		List<CommentTableJoinBean> tableBeanList = null;
		result_list = new ArrayList<CommentBean>();
		int announceId = 0;
		if(!StringUtils.isEmpty(request.getAnnounceId())){
			announceId = Integer.valueOf(request.getAnnounceId());
		}
		if(moreComment){
			if(announceId == 0){
				tableBeanList = (List<CommentTableJoinBean>)daoImpl.getCommentLimitedNumbersFromStartIdJoinQuery(request.getStartId(), request.getNumbers());
			}else{
				tableBeanList = (List<CommentTableJoinBean>)daoImpl.getCommentLimitedNumbersFromStartIdJoinQuery(announceId, request.getStartId(), request.getNumbers());
			}
		}else{
			if(announceId == 0){
				tableBeanList = (List<CommentTableJoinBean>)daoImpl.getCommentLimitedNumbersJoinQuery(request.getNumbers());
			}else{
				tableBeanList = (List<CommentTableJoinBean>)daoImpl.getCommentLimitedNumbersJoinQuery(announceId, request.getNumbers());
			}
		}
		if (tableBeanList != null && tableBeanList.size() > 0){
			for(CommentTableJoinBean table : tableBeanList){
				CommentBean bean = new CommentBean();
				bean.setSession_id(table.getSession_id());
				bean.setId(table.getId());
				bean.setAnnounce_id(table.getAnnounce_id());
				bean.setComment(table.getComment_content());
				bean.setComment_time(table.getComment_time().toString());
				bean.setCustomer_name(table.getUserName());
				bean.setNumberOfLike(table.getCountLike());
				bean.setNumberOfComment(table.getCountComments());
				
				if(table.getCountComments() > 1){
					String commentConcat = table.getCommentGroup();
					String[] commentArray = commentConcat.split(Constants.GROUP_SEPARATOR);
					bean.setCommentList(commentArray);
					String usernameConcat = table.getUsernameGroup();
					String[] usernameArray = usernameConcat.split(Constants.GROUP_SEPARATOR);
					bean.setCustomernameList(usernameArray);
				}
				
				result_list.add(bean);
			}
		}
		scopes.put(Constants.SERVICE_RESPONSE, result_list);
		
		return Constants.EVENT_SUCCESS;
	}
}
