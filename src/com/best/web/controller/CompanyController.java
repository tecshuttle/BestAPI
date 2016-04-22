package com.best.web.controller;


import com.best.msg.ExtResponse;
import com.best.msg.GenResponse;
import com.best.msg.ListResponse;
import com.best.util.AjaxUtil;
import com.best.web.model.admin.Company;
import com.best.web.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Resource
    private CompanyService service;

    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public void testConn(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ListResponse<Company> listResponse = new ListResponse<Company>();
        listResponse.setResponse(service.findCompanyList());
        AjaxUtil.sendJSON(response, listResponse);
    }
}

//end file

