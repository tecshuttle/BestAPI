package com.best.web.dao;

import java.util.List;

import com.best.web.model.init.Area;
import com.best.web.model.init.City;
import com.best.web.model.init.Code;
import com.best.web.model.init.Province;

public interface InitDao {
	public List<Code> findCodeList(String codeType);
	public List<Province> findProvinceList();
	public List<City> findCityList(String provinceId);
	public List<Area> findAreaList(String cityId);
}
