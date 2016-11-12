package com.weiwei.weiqi.processor.processors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weiwei.common.Constants;
import com.weiwei.weiqi.jdbc.dao.IAreaLocationDAO;
import com.weiwei.weiqi.jdbc.dbmodel.TableProvince;
import com.weiwei.weiqi.processor.base.BaseProcessor;

@Component
public class GetProvinceListProcessor extends BaseProcessor{
	private List<String> beanList;
	@Autowired
	private IAreaLocationDAO daoimpl;
	
	@Override
	protected void preProcess(Map<String, Object> scopes){
		beanList = new ArrayList<String>();
	}
	
	@Override
	protected String executeProcess(Map<String, Object> scopes) {
		List<TableProvince> tableList = (List<TableProvince>)daoimpl.findProvinceList();
		if(tableList != null & tableList.size()>0){
			for(int i=0; i<tableList.size(); i++){
				beanList.add(tableList.get(i).getId()-1, tableList.get(i).getProvince_name());
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
