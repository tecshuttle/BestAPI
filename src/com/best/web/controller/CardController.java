package com.best.web.controller;


import com.best.msg.ExtListResponse;
import com.best.msg.ExtResponse;
import com.best.msg.GenResponse;
import com.best.msg.ListResponse;
import com.best.util.AjaxUtil;
import com.best.web.model.admin.CardType;
import com.best.web.model.cust.CardPackage;
import com.best.web.model.order.CardNo;
import com.best.web.service.CardService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/card")
public class CardController {
    @Resource
    private CardService service;

    /**
     * 卡类型
     * 前面是页面方法，后面是ajax接口
     */
    @RequestMapping(value = "type", method = RequestMethod.GET)
    public ModelAndView typePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("card_type");
        return model;
    }

    @RequestMapping(value = "getTypeList", method = RequestMethod.GET)
    public void testConn(HttpServletRequest request, HttpServletResponse response, String username, String password, String start_date, String end_date) throws Exception {
        ListResponse<CardType> listResponse = new ListResponse<CardType>();
        listResponse.setResponse(service.findCardTypeList());
        AjaxUtil.sendJSON(response, listResponse);
    }


    @RequestMapping(value = "/updateType", method = RequestMethod.POST)
    public void updateFamily(HttpServletRequest request, HttpServletResponse response, CardType cardType, BindingResult result) throws Exception {
        ExtResponse<String> genResponse = new ExtResponse<String>();
        service.updateCardType(cardType);
        AjaxUtil.sendJSON(response, genResponse);
    }


    @RequestMapping(value = "/insertType", method = RequestMethod.POST)
    public void addFamily(HttpServletRequest request, HttpServletResponse response, CardType cardType, BindingResult result) throws Exception {
        GenResponse<String> genResponse = new GenResponse<String>();
        service.insertCardType(cardType);
        genResponse.setResponse(cardType.getId());
        AjaxUtil.sendJSON(response, genResponse);
    }


    /**
     * 卡号管理
     * 前面是页面方法，后面是ajax接口
     */
    @RequestMapping(value = "no", method = RequestMethod.GET)
    public ModelAndView listPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("card_no");
        return model;
    }


    @RequestMapping(value = "getCardNoList", method = RequestMethod.GET)
    public void getCardNoList(HttpServletRequest request, HttpServletResponse response, int start, int limit) throws Exception {
        ExtListResponse<CardNo> listResponse = new ExtListResponse<CardNo>();
        listResponse.setResponse(service.findCardNoList(), start, limit);
        AjaxUtil.sendJSON(response, listResponse);
    }


    @RequestMapping(value = "/updateCardNo", method = RequestMethod.POST)
    public void updateCardNo(HttpServletRequest request, HttpServletResponse response, CardNo cardType, BindingResult result) throws Exception {
        ExtResponse<String> genResponse = new ExtResponse<String>();
        service.updateCardNo(cardType);
        AjaxUtil.sendJSON(response, genResponse);
    }


    @RequestMapping(value = "/insertCardNo", method = RequestMethod.POST)
    public void insertCardNo(HttpServletRequest request, HttpServletResponse response, CardNo cardType, BindingResult result) throws Exception {
        GenResponse<String> genResponse = new GenResponse<String>();
        service.insertCardNo(cardType);
        genResponse.setResponse(cardType.getId());
        AjaxUtil.sendJSON(response, genResponse);
    }


    /**
     * 卡套餐
     * 前面是页面方法，后面是ajax接口
     */
    @RequestMapping(value = "package", method = RequestMethod.GET)
    public ModelAndView suitPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("card_package");
        return model;
    }

    @RequestMapping(value = "getPackageList", method = RequestMethod.GET)
    public void getPackageList(HttpServletRequest request, HttpServletResponse response, int start, int limit) throws Exception {
        ExtListResponse<CardPackage> listResponse = new ExtListResponse<CardPackage>();
        listResponse.setResponse(service.findCardPackageList(), start, limit);
        AjaxUtil.sendJSON(response, listResponse);
    }


    @RequestMapping(value = "updatePackage", method = RequestMethod.POST)
    public void updatePackage(HttpServletRequest request, HttpServletResponse response, CardPackage cardType, BindingResult result) throws Exception {
        ExtResponse<String> genResponse = new ExtResponse<String>();
        service.updateCardPackage(cardType);
        AjaxUtil.sendJSON(response, genResponse);
    }


    @RequestMapping(value = "insertPackage", method = RequestMethod.POST)
    public void insertPackage(HttpServletRequest request, HttpServletResponse response, CardPackage cardType, BindingResult result) throws Exception {
        GenResponse<String> genResponse = new GenResponse<String>();
        service.insertCardPackage(cardType);
        genResponse.setResponse(cardType.getId());
        AjaxUtil.sendJSON(response, genResponse);
    }
}

//end file