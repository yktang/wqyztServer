package com.weiwei.weiqi.jdbc.dao;

import java.util.List;

public interface IAreaLocationDAO {
	public List<?> findProvinceList();
	public List<?> findCityListByProvinceId(int province_id);
}
