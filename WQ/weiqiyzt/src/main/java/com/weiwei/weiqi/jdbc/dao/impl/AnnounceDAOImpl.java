package com.weiwei.weiqi.jdbc.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.weiwei.weiqi.jdbc.dao.IAnnouncementDAO;
import com.weiwei.weiqi.jdbc.dbmodel.TableAnnounce;

public class AnnounceDAOImpl implements IAnnouncementDAO{

	protected JdbcTemplate jdbcTemplate;
	
	@Override
	public List<?> findBySequenceId(int startId, int endId) {
		String numbers = String.valueOf(endId-startId+1);
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		Date currentDate = cal.getTime();
		cal.add(Calendar.MONTH, -1);
		Date lastMonthDate = cal.getTime();
		cal.add(Calendar.MONTH, -1);
		Date mBeforeLastMonthDate = cal.getTime(); 
		
		SimpleDateFormat formatter5 = new SimpleDateFormat("yyyy-MM");
		String month1 = formatter5.format(currentDate);
		String month2 = formatter5.format(lastMonthDate);
		String month3 = formatter5.format(mBeforeLastMonthDate);
		String sql = "SELECT aid, title, url FROM dede_co_htmls where nid=4 and (url like '%"+month1+"%' or url like '%"+month2+"%' or url like '%"+month3+"%') ORDER BY url desc LIMIT "+numbers;
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<TableAnnounce>(TableAnnounce.class));
	}

	@Override
	public List<?> findByUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate2) {
		this.jdbcTemplate = jdbcTemplate2;
	}
	
}
