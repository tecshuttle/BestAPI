package com.best.web.controller;


import com.best.msg.ExtResponse;
import com.best.msg.GenResponse;
import com.best.msg.ListResponse;
import com.best.util.AjaxUtil;
import com.best.web.model.admin.CardType;
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


    //卡列表
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView listPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("card_list");
        return model;
    }


    //卡套餐
    @RequestMapping(value = "suit", method = RequestMethod.GET)
    public ModelAndView suitPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("card_suit");
        return model;
    }

}

//end file