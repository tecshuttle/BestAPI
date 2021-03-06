package com.best.web.controller;


import com.best.msg.ExtResponse;
import com.best.msg.GenResponse;
import com.best.msg.ListResponse;
import com.best.msg.ExtListResponse;
import com.best.util.AjaxUtil;
import com.best.web.model.admin.Supplier;
import com.best.web.model.admin.SupplierOrg;
import com.best.web.model.admin.SupplierProduct;
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


    /**
     * 供应商门店管理
     * 前面是页面方法，后面是ajax接口
     */
    @RequestMapping(value = "org", method = RequestMethod.GET)
    public ModelAndView orgPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("supplier_org");
        return model;
    }

    @RequestMapping(value = "getSupplierOrgList", method = RequestMethod.GET)
    public void getSupplierOrgList(HttpServletRequest request, HttpServletResponse response, SupplierOrg model, int start, int limit) throws Exception {
        ExtListResponse<SupplierOrg> listResponse = new ExtListResponse<SupplierOrg>();
        listResponse.setResponse(service.findSupplierOrgList(model), start, limit);
        AjaxUtil.sendJSON(response, listResponse);
    }


    @RequestMapping(value = "updateSupplierOrg", method = RequestMethod.POST)
    public void updateSupplierOrg(HttpServletRequest request, HttpServletResponse response, SupplierOrg cardType, BindingResult result) throws Exception {
        ExtResponse<String> genResponse = new ExtResponse<String>();
        service.updateSupplierOrg(cardType);
        AjaxUtil.sendJSON(response, genResponse);
    }


    @RequestMapping(value = "insertSupplierOrg", method = RequestMethod.POST)
    public void insertSupplierOrg(HttpServletRequest request, HttpServletResponse response, SupplierOrg cardType, BindingResult result) throws Exception {
        GenResponse<String> genResponse = new GenResponse<String>();
        service.insertSupplierOrg(cardType);
        genResponse.setResponse(cardType.getId());
        AjaxUtil.sendJSON(response, genResponse);
    }


    /**
     * 供应商产品管理
     * 前面是页面方法，后面是ajax接口
     */
    @RequestMapping(value = "service", method = RequestMethod.GET)
    public ModelAndView servicePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("supplier_service");
        return model;
    }

    @RequestMapping(value = "getSupplierServiceList", method = RequestMethod.GET)
    public void getSupplierServiceList(HttpServletRequest request, HttpServletResponse response, String supplier_id, String service_type, int start, int limit) throws Exception {
        ExtListResponse<SupplierProduct> listResponse = new ExtListResponse<SupplierProduct>();
        listResponse.setResponse(service.findSupplierServiceList(supplier_id, service_type), start, limit);
        AjaxUtil.sendJSON(response, listResponse);
    }


    @RequestMapping(value = "getServiceListBySupplier", method = RequestMethod.GET)
    public void getServiceListBySupplier(HttpServletRequest request, HttpServletResponse response, String supplier_id, int start, int limit) throws Exception {
        ExtListResponse<SupplierProduct> listResponse = new ExtListResponse<SupplierProduct>();
        listResponse.setResponse(service.findServiceListBySupplier(supplier_id), start, 9999);
        AjaxUtil.sendJSON(response, listResponse);
    }


    @RequestMapping(value = "getServiceListByType", method = RequestMethod.GET)
    public void getServiceListByType(HttpServletRequest request, HttpServletResponse response, String service_type, int start, int limit) throws Exception {
        ExtListResponse<SupplierProduct> listResponse = new ExtListResponse<SupplierProduct>();
        listResponse.setResponse(service.findServiceListByType(service_type), start, 9999);
        AjaxUtil.sendJSON(response, listResponse);
    }


    @RequestMapping(value = "updateSupplierService", method = RequestMethod.POST)
    public void updateSupplierService(HttpServletRequest request, HttpServletResponse response, SupplierProduct cardType, BindingResult result) throws Exception {
        ExtResponse<String> genResponse = new ExtResponse<String>();
        service.updateSupplierService(cardType);
        AjaxUtil.sendJSON(response, genResponse);
    }


    @RequestMapping(value = "insertSupplierService", method = RequestMethod.POST)
    public void insertSupplierService(HttpServletRequest request, HttpServletResponse response, SupplierProduct cardType, BindingResult result) throws Exception {
        GenResponse<String> genResponse = new GenResponse<String>();
        service.insertSupplierService(cardType);
        genResponse.setResponse(cardType.getId());
        AjaxUtil.sendJSON(response, genResponse);
    }
}

//end file
