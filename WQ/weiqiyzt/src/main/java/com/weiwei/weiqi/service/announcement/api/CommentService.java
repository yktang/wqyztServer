package com.weiwei.weiqi.service.announcement.api;

import com.weiwei.common.exception.BusinessException;
import com.weiwei.weiqi.request.announcement.AddCommentEnter;
import com.weiwei.weiqi.request.announcement.CommentListEnter;
import com.weiwei.weiqi.request.base.BaseEnter;
import com.weiwei.weiqi.request.base.MyPageRequest;
import com.weiwei.weiqi.request.comment.AnnouncesEnter;
import com.weiwei.weiqi.response.base.GeneralResult;

public interface CommentService {

	GeneralResult commentList(MyPageRequest pageRequest, CommentListEnter enter) throws BusinessException ;

	GeneralResult addComment(AddCommentEnter enter, BaseEnter baseEnter) throws BusinessException;

}
