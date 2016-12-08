package com.weiwei.weiqi.controller.area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.weiwei.weiqi.request.area.GetCityListEnter;
import com.weiwei.weiqi.request.base.BaseEnter;
import com.weiwei.weiqi.response.base.GeneralResult;
import com.weiwei.weiqi.service.area.api.AreaService;

@RestController
@RequestMapping("/area")
public class AreaController {
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value="/city")
	public GeneralResult getCityList(GetCityListEnter cityListEnter){
		return areaService.getCityList(cityListEnter);
	}
	
	@RequestMapping(value="/province")
	public GeneralResult getProvinceList(BaseEnter baseEnter){
		return areaService.getProvinceList();
	}
	
}
