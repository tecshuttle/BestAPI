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
@RequestMapping("/card")
public class CardController {
    @Resource
    private UserService service;

    @RequestMapping(value = "type", method = RequestMethod.GET)
    public ModelAndView typePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("card_type");
        return model;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView listPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("card_list");
        return model;
    }

    @RequestMapping(value = "suit", method = RequestMethod.GET)
    public ModelAndView suitPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("card_suit");
        return model;
    }
}
