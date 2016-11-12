package com.weiwei.weiqi.jdbc.dao;

import java.util.List;

public interface ICommentDAO {
	public List<?> getCommentLimitedNumbersJoinQuery(int announce_id, int n);
	public List<?> getCommentLimitedNumbersJoinQuery(int n);
	public List<?> getCommentLimitedNumbersFromStartIdJoinQuery(int announce_id, int start_id, int n);
	public List<?> getCommentLimitedNumbersFromStartIdJoinQuery(int start_id,int n);
}
