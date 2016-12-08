package com.weiwei.weiqi.service.announcement.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.weiwei.common.constants.enums.ResultCodeEnum;
import com.weiwei.common.exception.BusinessException;
import com.weiwei.common.util.DateFormatUtils;
import com.weiwei.weiqi.jdbc.dao.annoucement.CommentDao;
import com.weiwei.weiqi.jdbc.dao.annoucement.CommentSessionDao;
import com.weiwei.weiqi.jdbc.dao.annoucement.HtmlsDao;
import com.weiwei.weiqi.jdbc.dao.customer.CustomerDao;
import com.weiwei.weiqi.jdbc.dbmodel.annoucement.Comment;
import com.weiwei.weiqi.jdbc.dbmodel.annoucement.CommentLike;
import com.weiwei.weiqi.jdbc.dbmodel.annoucement.CommentSession;
import com.weiwei.weiqi.jdbc.dbmodel.annoucement.Htmls;
import com.weiwei.weiqi.jdbc.dbmodel.customer.Customer;
import com.weiwei.weiqi.request.announcement.AddCommentEnter;
import com.weiwei.weiqi.request.announcement.CommentListEnter;
import com.weiwei.weiqi.request.base.BaseEnter;
import com.weiwei.weiqi.request.base.MyPageRequest;
import com.weiwei.weiqi.response.annoucement.CommentListResult;
import com.weiwei.weiqi.response.base.DataResult;
import com.weiwei.weiqi.response.base.GeneralResult;
import com.weiwei.weiqi.service.announcement.api.CommentService;
import com.weiwei.weiqi.service.base.BaseServiceImpl;

@Component
public class CommentServiceImpl extends BaseServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;
	@Autowired
	private CommentSessionDao commentSessionDao;
	@Autowired
	private HtmlsDao htmlsDao;
	@Autowired
	private CustomerDao customerDao;
	
	
	@Override
	public GeneralResult commentList(MyPageRequest pageRequest, CommentListEnter enter) {
		Map<String, String[]> params = new HashMap<String, String[]>();
		params.put("EQ_announce.aid", new String[]{String.valueOf(enter.getAnnounceId())});
		Pageable pageable = getPageable(pageRequest, new Sort(Direction.DESC, "id"));
		Specification<CommentSession> spec = spec(params, CommentSession.class);
		Page<CommentSession> page = commentSessionDao.findAll(spec, pageable);
		List<CommentSession> list = page.getContent();

		List<CommentListResult> results = new ArrayList<CommentListResult>();
		for (CommentSession commentSession : list) {	
			List<Comment> comments = commentSession.getComments();
			if(comments==null || comments.isEmpty())
				continue;
				
			CommentListResult result = new CommentListResult();
			List<CommentLike> commentLikes = commentSession.getCommentLikes();
			// 第一个为初始评论
			Comment cm = comments.get(0);
			result.setSessionId(commentSession.getId());	
			result.setComment(cm.getCommentContent());
			result.setCommentTime(DateFormatUtils.toDateStringTimestamp(cm.getCommentTime()));
			result.setCustomerName(cm.getCustomer().getUserName());
			
			//取评论的回复
			int  numberOfComment =  comments.size();
			String[] commentList = new String[numberOfComment-1];
			for (int i = 1; i < numberOfComment; i++) {
				Comment c = comments.get(i);
				commentList[i-1] = c.getCommentContent();
			}
			
			//点赞
			int numberOfLike = commentLikes.size();
			String[] customernameList = new String[numberOfLike];
			for(int i= 0;i<numberOfLike;i++){
				CommentLike c = commentLikes.get(i);
				customernameList[i] = c.getCustomer().getUserName();
			}	
			result.setCommentList(commentList);
			result.setCustomernameList(customernameList);
			result.setNumberOfComment(numberOfComment-1);
			result.setNumberOfLike(numberOfLike);
			results.add(result);
		}
		return new DataResult<List<CommentListResult>>(ResultCodeEnum.RESULT_SUCCESS, results);
	}

	
	@Transactional
	public GeneralResult addComment(AddCommentEnter enter, BaseEnter baseEnter) {
		Integer announceId = enter.getAnnounceId();
		Integer sessionId = enter.getSessionId();

		CommentSession commentSession;
		if(announceId!=null){
			Htmls htmls;
			if(announceId!=0)
				htmls =htmlsDao.getOne(announceId);
			else
				htmls = new Htmls(0);
			//添加公告或者自由评论
			 commentSession = new CommentSession();
			commentSession.setAnnounce(htmls);
			commentSessionDao.save(commentSession);	
		}else if(sessionId!=null){
			commentSession = commentSessionDao.getOne(sessionId);
		}else{
			throw new BusinessException(ResultCodeEnum.RESULT_ERROR_NEED_PARM);
		}
		
		Comment comment = new Comment();
		comment.setCommentContent(enter.getCommentContent());
		comment.setCommentSession(commentSession);
		comment.setCommentTime(DateFormatUtils.getNowTimestamp());
		comment.setCustomer(customerDao.getOne(baseEnter.getUserId()));
		commentDao.save(comment);
		return new GeneralResult(ResultCodeEnum.RESULT_SUCCESS);
	}

	
}
