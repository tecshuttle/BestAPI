package com.best.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.best.web.dao.InitDao;
import com.best.web.model.init.Area;
import com.best.web.model.init.City;
import com.best.web.model.init.Code;
import com.best.web.model.init.Province;

@Repository
public class InitDaoImpl implements InitDao {

	@Resource
	private SqlSession sqlSession;
	
	public List<Code> findCodeList(String codeType){
		return sqlSession.selectList("initSql.findCodeList",codeType);
	}
	
	public List<Province> findProvinceList() {
		return sqlSession.selectList("initSql.findProvinceList");
	}
	
	public List<City> findCityList(String provinceId) {
		return sqlSession.selectList("initSql.findCityList", provinceId);
	}
	
	public List<Area> findAreaList(String cityId) {
		return sqlSession.selectList("initSql.findAreaList", cityId);
	}

}
