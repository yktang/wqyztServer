package com.weiwei.weiqi.jdbc.dao;

import java.util.List;

public interface IAnnouncementDAO {
	public List<?> findBySequenceId(int startId, int endId);
	public List<?> findByUrl(String url);
}
