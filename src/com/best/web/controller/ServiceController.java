package com.best.web.controller;


import com.best.msg.ExtResponse;
import com.best.msg.GenResponse;
import com.best.msg.ListResponse;
import com.best.msg.ExtListResponse;
import com.best.web.model.admin.ProdService;
import com.best.util.AjaxUtil;
import com.best.web.model.order.CardNo;
import com.best.web.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/service")
public class ServiceController {
    @Resource
    private ProductService service;


    /**
     * 卡类型
     * 前面是页面方法，后面是ajax接口
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView usersPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("service");
        return model;
    }

    @RequestMapping(value = "getProdServiceList", method = RequestMethod.GET)
    public void testConn(HttpServletRequest request, HttpServletResponse response, int start, int limit) throws Exception {
        ExtListResponse<ProdService> listResponse = new ExtListResponse<ProdService>();
        listResponse.setResponse(service.findProdServiceList(), start, limit);
        AjaxUtil.sendJSON(response, listResponse);
    }


    @RequestMapping(value = "/updateProdService", method = RequestMethod.POST)
    public void updateFamily(HttpServletRequest request, HttpServletResponse response, ProdService cardType, BindingResult result) throws Exception {
        ExtResponse<String> genResponse = new ExtResponse<String>();
        service.updateProdService(cardType);
        AjaxUtil.sendJSON(response, genResponse);
    }


    @RequestMapping(value = "/insertProdService", method = RequestMethod.POST)
    public void addFamily(HttpServletRequest request, HttpServletResponse response, ProdService cardType, BindingResult result) throws Exception {
        GenResponse<String> genResponse = new GenResponse<String>();
        service.insertProdService(cardType);
        genResponse.setResponse(cardType.getId());
        AjaxUtil.sendJSON(response, genResponse);
    }
}

//end file
