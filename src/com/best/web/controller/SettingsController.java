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
@RequestMapping("/settings")
public class SettingsController {
    @Resource
    private UserService service;

    @RequestMapping( method = RequestMethod.GET)
    public ModelAndView usersPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("settings");
        return model;
    }
}
