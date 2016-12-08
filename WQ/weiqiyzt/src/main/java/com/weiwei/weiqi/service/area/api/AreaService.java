package com.weiwei.weiqi.service.area.api;

import com.weiwei.weiqi.request.area.GetCityListEnter;
import com.weiwei.weiqi.response.base.GeneralResult;

public interface AreaService {

	GeneralResult getCityList(GetCityListEnter cityListEnter);

	GeneralResult getProvinceList();

}
