package com.best.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.best.msg.ListResponse;
import com.best.util.AjaxUtil;
import com.best.web.model.init.Area;
import com.best.web.model.init.City;
import com.best.web.model.init.Code;
import com.best.web.model.init.Province;
import com.best.web.service.InitService;

@Controller
@RequestMapping("/init")
public class InitController {
	
	@Resource
	private InitService service;
	
	/** 省市区   **/
	@RequestMapping(value="/findProvinceList",method=RequestMethod.POST)
	public void findProvinceList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ListResponse<Province> _listResponse = new ListResponse<Province>();
		_listResponse.setResponse(service.findProvinceList());
		AjaxUtil.sendJSON(response, _listResponse);
	}
	
	@RequestMapping(value="/findCityList",method=RequestMethod.POST)
	public void findCityList(HttpServletRequest request,HttpServletResponse response, String provinceId) throws Exception {
		ListResponse<City> _listResponse = new ListResponse<City>();
		_listResponse.setResponse(service.findCityList(provinceId));
		AjaxUtil.sendJSON(response, _listResponse);
		AjaxUtil.sendJSON(response, service.findCityList(provinceId));
	}
	
	@RequestMapping(value="/findAreaList",method=RequestMethod.POST)
	public void findAreaList(HttpServletRequest request,HttpServletResponse response, String cityId) throws Exception {
		ListResponse<Area> _listResponse = new ListResponse<Area>();
		_listResponse.setResponse(service.findAreaList(cityId));
		AjaxUtil.sendJSON(response, _listResponse);
	}
	
	@RequestMapping(value="/findCretTypeList",method=RequestMethod.POST)
	public void findCretTypeList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ListResponse<Code> _listResponse = new ListResponse<Code>();
		_listResponse.setResponse(service.findCretTypeList());
		AjaxUtil.sendJSON(response, _listResponse);
	}
	
	@RequestMapping(value="/findInsuranceTypeList",method=RequestMethod.POST)
	public void findInsuranceTypeList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ListResponse<Code> _listResponse = new ListResponse<Code>();
		_listResponse.setResponse(service.findinsuranceTypeList());
		AjaxUtil.sendJSON(response, _listResponse);
	}
	
	@RequestMapping(value="/findMarriageList",method=RequestMethod.POST)
	public void findMarriageList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ListResponse<Code> _listResponse = new ListResponse<Code>();
		_listResponse.setResponse(service.findMarriageList());
		AjaxUtil.sendJSON(response, _listResponse);
	}
}
