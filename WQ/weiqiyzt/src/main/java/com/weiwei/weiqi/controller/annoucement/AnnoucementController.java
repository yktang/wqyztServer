package com.weiwei.weiqi.controller.annoucement;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weiwei.weiqi.annotation.AccessControl;
import com.weiwei.weiqi.request.announcement.AddCommentEnter;
import com.weiwei.weiqi.request.announcement.CommentListEnter;
import com.weiwei.weiqi.request.announcement.ContentEnter;
import com.weiwei.weiqi.request.announcement.HtmlsEnter;
import com.weiwei.weiqi.request.base.BaseEnter;
import com.weiwei.weiqi.request.base.MyPageRequest;
import com.weiwei.weiqi.response.base.GeneralResult;
import com.weiwei.weiqi.service.announcement.api.CommentService;
import com.weiwei.weiqi.service.announcement.api.HtmlsService;

/**
  * 
  * @ClassName: AnnoucementController   
  * @Description: 公告相关操作   
  * @author zlp 
  * @date 2016年11月11日 下午10:37:37   
  *
  */
@RestController
@RequestMapping("/announcement")
public class AnnoucementController {
	
	@Autowired
	private HtmlsService htmlsService;
	@Autowired
	private CommentService commentService;
	
	/**
	  * 
	  * @Title: list   
	  * @Description: 公告标题列表
	  * @param @param pageRequest
	  * @param @param enter
	  * @param @return   
	  * @return GeneralResult    
	  * @throws
	  */
	@AccessControl(noSessionLogin = true)
	@GetMapping("/list")
	public GeneralResult list(MyPageRequest pageRequest,HtmlsEnter enter){
		return htmlsService.list(pageRequest,enter);
	}
	
	/**
	 * 
	  * @Title: content   
	  * @Description: 公告内容
	  * @param @param enter
	  * @param @return   
	  * @return GeneralResult    
	  * @throws
	 */
	@AccessControl(noSessionLogin = true)
	@GetMapping("/content")
	public GeneralResult content(ContentEnter enter){
		return htmlsService.content(enter);
	}
	
	/**
	  * 
	  * @Title: commentList   
	  * @Description: 评论列表
	  * @param @param pageRequest
	  * @param @param enter
	  * @param @return   
	  * @return GeneralResult    
	  * @throws
	  */
	@AccessControl(noSessionLogin = true)
	@GetMapping("/comment/list")
	public GeneralResult commentList(MyPageRequest pageRequest,@Valid CommentListEnter enter){
		return commentService.commentList(pageRequest,enter);
	}
	
	/**
	  * 
	  * @Title: addComment   
	  * @Description: 添加评论
	  * @param @param enter
	  * @param @param baseEnter
	  * @param @return   
	  * @return GeneralResult    
	  * @throws
	  */
	@PostMapping("/comment/add")
	public GeneralResult addComment(@RequestBody AddCommentEnter enter,BaseEnter baseEnter){
		return commentService.addComment(enter,baseEnter);
	}
	
//	public GeneralResult like(){
//		
//	}
}
