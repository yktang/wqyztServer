package com.weiwei.weiqi.service.area.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weiwei.common.constants.enums.ResultCodeEnum;
import com.weiwei.weiqi.jdbc.dao.area.CityDao;
import com.weiwei.weiqi.jdbc.dao.area.ProvinceDao;
import com.weiwei.weiqi.jdbc.dbmodel.area.City;
import com.weiwei.weiqi.jdbc.dbmodel.area.Province;
import com.weiwei.weiqi.request.area.GetCityListEnter;
import com.weiwei.weiqi.response.area.CityBean;
import com.weiwei.weiqi.response.area.ProvinceBean;
import com.weiwei.weiqi.response.base.DataResult;
import com.weiwei.weiqi.response.base.GeneralResult;
import com.weiwei.weiqi.service.area.api.AreaService;
import com.weiwei.weiqi.service.base.BaseServiceImpl;

@Component
public class AreaServiceImpl extends BaseServiceImpl implements AreaService {

	@Autowired
	private CityDao iAreaDao;

	@Autowired
	private ProvinceDao provinceDao;
	
	@Override
	public GeneralResult getCityList(GetCityListEnter cityListEnter) {
		List<CityBean> beanList = new ArrayList<CityBean>();

		List<City> citiesList = iAreaDao.findCityListByProvinceId(cityListEnter.getProvinceId());
		if (!citiesList.isEmpty()) {
			for (City city : citiesList) {
				CityBean bean = new CityBean();
				BeanUtils.copyProperties(city, bean);
				bean.setCityd(city.getId());
				beanList.add(bean);
			}
		}
		return new DataResult<List<CityBean>>(ResultCodeEnum.RESULT_SUCCESS,beanList);
	}

	@Override
	public GeneralResult getProvinceList() {
	    List<ProvinceBean> beanList = new ArrayList<ProvinceBean>();
		List<Province> provinces = provinceDao.findAll();
		if(!provinces.isEmpty()){
			for (Province province : provinces) {
				ProvinceBean bean = new ProvinceBean();
				bean.setProvinceId(province.getId());
				bean.setProvinceName(province.getProvinceName());
				beanList.add(bean);
			}
		}
		return new DataResult<List<ProvinceBean>>(ResultCodeEnum.RESULT_SUCCESS,beanList);
	}

}
