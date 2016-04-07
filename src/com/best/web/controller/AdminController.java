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

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is welcome page!");
        model.setViewName("hello");
        return model;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is protected page!");
        model.setViewName("admin");

        return model;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public ModelAndView usersPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is welcome page!");
        model.setViewName("users");
        return model;
    }


    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public void testConn(HttpServletRequest request, HttpServletResponse response, String username, String password, String start_date, String end_date) throws Exception {
        ListResponse<User> listResponse = new ListResponse<User>();
        listResponse.setResponse(service.findUserList());
        AjaxUtil.sendJSON(response, listResponse);
    }


    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public void updateFamily(HttpServletRequest request, HttpServletResponse response, User user, BindingResult result) throws Exception {
        ExtResponse<String> genResponse = new ExtResponse<String>();

        service.updateUser(user);

        AjaxUtil.sendJSON(response, genResponse);
    }

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public void addFamily(HttpServletRequest request, HttpServletResponse response, User user, BindingResult result) throws Exception {
        GenResponse<String> genResponse = new GenResponse<String>();

        service.insertUser(user);

        genResponse.setResponse(user.getId());

        AjaxUtil.sendJSON(response, genResponse);
    }
}

