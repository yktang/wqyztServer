package com.weiwei.weiqi.processor.processors;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weiwei.common.Constants;
import com.weiwei.weiqi.annotation.MapToBean;
import com.weiwei.weiqi.jdbc.dao.IAreaLocationDAO;
import com.weiwei.weiqi.jdbc.dbmodel.TableCities;
import com.weiwei.weiqi.processor.base.BaseProcessor;
import com.weiwei.weiqi.response.bean.CityBean;

@Component
public class GetCityListProcessor extends BaseProcessor{
	private List<CityBean> beanList;
	@Autowired
	private IAreaLocationDAO daoimpl;
	private int province_id;
	
	@Override
	protected void preProcess(Map<String, Object> scopes){
		province_id = (int)scopes.get(Constants.SERVICE_REQUEST);
	}
	
	@Override
	protected String executeProcess(Map<String, Object> scopes) {
		beanList = new ArrayList<CityBean>();
		List<TableCities> tableList = (List<TableCities>)daoimpl.findCityListByProvinceId(province_id);
		if(tableList != null & tableList.size()>0){
			for(int i=0; i<tableList.size(); i++){
				TableCities tableCities = tableList.get(i);
				CityBean bean = new CityBean();
				Field[] fields = bean.getClass().getFields();
				try {
					for (Field field : fields) {
						MapToBean annoMapToBean = field.getAnnotation(MapToBean.class);
						if (annoMapToBean == null) {
							continue;
						}
						String fieldName = field.getName();
						String fromfield = annoMapToBean.fromField();
						if (fromfield != null && !fromfield.equals("")) {
							fieldName = fromfield;
						}
						Field fieldTableCities = tableCities.getClass().getField(fieldName);
						field.set(bean, fieldTableCities.get(tableCities));
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				}
				bean.setId(tableList.get(i).getId());
				bean.setCityName(tableList.get(i).getCity_name());
				beanList.add(bean);
			}
		}
		return Constants.EVENT_SUCCESS;
	}
	
	@Override
	protected String postProcess(Map scopes, String event){
		scopes.put(Constants.SERVICE_RESPONSE, beanList);
		return event;
	}

}
