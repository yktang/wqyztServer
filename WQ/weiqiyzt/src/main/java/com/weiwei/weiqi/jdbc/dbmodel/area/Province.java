package com.weiwei.weiqi.jdbc.dbmodel.area;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "province")
public class Province {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	
	@Column(name="province_name")
	public String provinceName;
	
	@OneToMany(mappedBy="province", fetch = FetchType.LAZY)
	private List<City> cities;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public List<City> getCities() {
		return cities;
	}
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	
}
