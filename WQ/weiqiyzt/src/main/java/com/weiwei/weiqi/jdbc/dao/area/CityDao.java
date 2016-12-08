package com.weiwei.weiqi.jdbc.dao.area;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.weiwei.weiqi.jdbc.dao.base.BaseJpaRepository;
import com.weiwei.weiqi.jdbc.dbmodel.area.City;

public interface CityDao extends BaseJpaRepository<City, Integer> {

	@Query("from City bean where bean.province.id = ?1")
	List<City> findCityListByProvinceId(int province_id);

}
