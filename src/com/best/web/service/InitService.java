package com.best.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.best.web.dao.InitDao;
import com.best.web.model.init.Area;
import com.best.web.model.init.City;
import com.best.web.model.init.Code;
import com.best.web.model.init.Province;

@Service
public class InitService {
	@Resource
	private InitDao dao;
	
	public List<Province> findProvinceList(){
		return dao.findProvinceList();
	}
	
	public List<City> findCityList(String provinceId){
		return dao.findCityList(provinceId);
	}
	
	public List<Area> findAreaList(String cityId){
		return dao.findAreaList(cityId);
	}
	
	public List<Code> findCretTypeList(){
		return dao.findCodeList("BD_CRET_TYPE");
	}
	
	public List<Code> findinsuranceTypeList(){
		return dao.findCodeList("BD_INSURANCE_TYPE");
	}
	
	public List<Code> findMarriageList(){
		return dao.findCodeList("MARRIAGE");
	}
}
