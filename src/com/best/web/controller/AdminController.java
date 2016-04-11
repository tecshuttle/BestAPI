package com.best.web.controller;


import com.best.msg.BaseResponse;
import com.best.msg.GenResponse;
import com.best.msg.ExtResponse;
import com.best.msg.ListResponse;
import com.best.util.AjaxUtil;
import com.best.util.BasicUtil;
import com.best.util.ResultCodes;
import com.best.web.model.admin.User;
import com.best.web.model.cust.Family;
import com.best.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is protected page!");
        model.setViewName("admin");

        return model;
    }
}

