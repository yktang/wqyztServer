package com.weiwei.weiqi.jdbc.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.weiwei.weiqi.jdbc.dao.IAreaLocationDAO;
import com.weiwei.weiqi.jdbc.dbmodel.TableCities;
import com.weiwei.weiqi.jdbc.dbmodel.TableProvince;

@Service
public class AreaLocationDAOImpl implements IAreaLocationDAO {
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	@Override
	public List<?> findProvinceList() {
    	String sql = "SELECT * FROM province";
    	return jdbcTemplate.query(sql, new BeanPropertyRowMapper<TableProvince>(TableProvince.class));
	}
	
	@Override
	public List<?> findCityListByProvinceId(int province_id) {
    	String sql = "SELECT * FROM cities WHERE province_id=?";
    	return jdbcTemplate.query(sql, new Object[]{province_id}, new BeanPropertyRowMapper<TableCities>(TableCities.class));
	}
	
}
