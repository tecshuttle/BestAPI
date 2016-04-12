package com.best.web.controller;


import com.best.msg.ExtResponse;
import com.best.msg.GenResponse;
import com.best.msg.ListResponse;
import com.best.util.AjaxUtil;
import com.best.web.model.admin.Supplier;
import com.best.web.service.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Resource
    private SupplierService service;


    /**
     * 供应商
     * 前面是页面方法，后面是ajax接口
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView listPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("supplier_list");
        return model;
    }

    @RequestMapping(value = "getSupplierList", method = RequestMethod.GET)
    public void testConn(HttpServletRequest request, HttpServletResponse response, String username, String password, String start_date, String end_date) throws Exception {
        ListResponse<Supplier> listResponse = new ListResponse<Supplier>();
        listResponse.setResponse(service.findSupplierList());
        AjaxUtil.sendJSON(response, listResponse);
    }


    @RequestMapping(value = "/updateSupplier", method = RequestMethod.POST)
    public void updateFamily(HttpServletRequest request, HttpServletResponse response, Supplier cardType, BindingResult result) throws Exception {
        ExtResponse<String> genResponse = new ExtResponse<String>();
        service.updateSupplier(cardType);
        AjaxUtil.sendJSON(response, genResponse);
    }


    @RequestMapping(value = "/insertSupplier", method = RequestMethod.POST)
    public void addFamily(HttpServletRequest request, HttpServletResponse response, Supplier cardType, BindingResult result) throws Exception {
        GenResponse<String> genResponse = new GenResponse<String>();
        service.insertSupplier(cardType);
        genResponse.setResponse(cardType.getId());
        AjaxUtil.sendJSON(response, genResponse);
    }

    @RequestMapping(value = "store", method = RequestMethod.GET)
    public ModelAndView storePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("supplier_store");
        return model;
    }

    @RequestMapping(value = "service", method = RequestMethod.GET)
    public ModelAndView servicePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("supplier_service");
        return model;
    }
}
