package com.weiwei.weiqi.response.area;

import com.weiwei.weiqi.annotation.MapToBean;

public class CityBean {
//	@MapToBean
//	public int id;
//	@MapToBean(fromField = "city_name")
//	public String cityName;
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getCityName() {
//		return cityName;
//	}
//	public void setCityName(String cityName) {
//		this.cityName = cityName;
//	}
	private int cityd;
	private String cityName;
	public int getCityd() {
		return cityd;
	}
	public void setCityd(int cityd) {
		this.cityd = cityd;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
}
