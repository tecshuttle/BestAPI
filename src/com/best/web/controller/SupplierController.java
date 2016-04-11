package com.best.web.controller;


import com.best.msg.ExtResponse;
import com.best.msg.GenResponse;
import com.best.msg.ListResponse;
import com.best.util.AjaxUtil;
import com.best.web.model.admin.User;
import com.best.web.service.UserService;
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
    private UserService service;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView listPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("supplier_list");
        return model;
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
