package com.best.web.controller;


import com.best.msg.ExtResponse;
import com.best.msg.GenResponse;
import com.best.msg.ExtListResponse;
import com.best.web.model.admin.ProdService;
import com.best.web.model.admin.ProdServiceMap;
import com.best.util.AjaxUtil;
import com.best.web.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

        //单独统计关联的供应商服务
        List<ProdService> lists = listResponse.getResponse();

        for (int i = 0; i < lists.size(); i++) {
            ProdService row = lists.get(i);
            ProdService CSM = service.getCountServiceMap(row.getId());
            row.setCount_service_map(CSM.getCount_service_map());
        }

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


    /**
     * 服务映射
     * 前面是页面方法，后面是ajax接口
     */
    @RequestMapping(value = "getProdServiceMapList", method = RequestMethod.GET)
    public void getProdServiceMapList(HttpServletRequest request, HttpServletResponse response, int start, int limit, String xzh_service_id) throws Exception {
        ExtListResponse<ProdServiceMap> listResponse = new ExtListResponse<ProdServiceMap>();
        listResponse.setResponse(service.findProdServiceMapList(xzh_service_id), start, limit);
        AjaxUtil.sendJSON(response, listResponse);
    }


    @RequestMapping(value = "/updateProdServiceMap", method = RequestMethod.POST)
    public void updateProdServiceMap(HttpServletRequest request, HttpServletResponse response, ProdServiceMap model, BindingResult result) throws Exception {
        ExtResponse<String> genResponse = new ExtResponse<String>();
        service.updateProdServiceMap(model);
        AjaxUtil.sendJSON(response, genResponse);
    }


    @RequestMapping(value = "/insertProdServiceMap", method = RequestMethod.POST)
    public void addProdServiceMap(HttpServletRequest request, HttpServletResponse response, ProdServiceMap model, BindingResult result) throws Exception {
        GenResponse<String> genResponse = new GenResponse<String>();
        service.insertProdServiceMap(model);
        genResponse.setResponse(model.getId());
        AjaxUtil.sendJSON(response, genResponse);
    }
}

//end file
