package com.best.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.best.msg.AnxinListResponse;
import com.best.util.AjaxUtil;
import com.best.util.BasicUtil;
import com.best.util.ResultCodes;
import com.best.web.model.essence.CustInfo;
import com.best.web.service.CustService;

@Controller
@RequestMapping("/essence")
public class EssenceController {

    StringBuffer errorMsg;

    @Resource
    private CustService service;

    //同步激活数据
    @RequestMapping(value = "/syncCust", method = RequestMethod.GET)
    public void syncCust(HttpServletRequest request, HttpServletResponse response, String username, String password, String start_date, String end_date) throws Exception {
        AnxinListResponse<CustInfo> listResponse = new AnxinListResponse<CustInfo>();
        if (BasicUtil.isEmpty(username)) {
            listResponse.setError("账户名为空");
            AjaxUtil.sendJSON(response, listResponse);
            return;
        }
        if (BasicUtil.isEmpty(password)) {
            listResponse.setError("账户密码为空");
            AjaxUtil.sendJSON(response, listResponse);
            return;
        }
        if (!"z1001".equals(username) || !"h565274@com".equals(password)) {
            listResponse.setError("账户或密码错误");
            AjaxUtil.sendJSON(response, listResponse);
            return;
        }
        if (BasicUtil.isNotEmpty(start_date)) {
            if (!BasicUtil.isValidDate(start_date, "yyyy-MM-dd")) {
                listResponse.setError("start_date格式错误");
                AjaxUtil.sendJSON(response, listResponse);
                return;
            }
        }
        if (BasicUtil.isNotEmpty(end_date)) {
            if (!BasicUtil.isValidDate(end_date, "yyyy-MM-dd")) {
                listResponse.setError("end_date格式错误");
                AjaxUtil.sendJSON(response, listResponse);
                return;
            }
        }

        listResponse.setData(service.syncCust(start_date, end_date));
        AjaxUtil.sendJSON(response, listResponse);
    }

    //同步激活数据
    @RequestMapping(value = "/testConn", method = RequestMethod.GET)
    public void testConn(HttpServletRequest request, HttpServletResponse response, String username, String password, String start_date, String end_date) throws Exception {
        AnxinListResponse<CustInfo> listResponse = new AnxinListResponse<CustInfo>();

        listResponse.setData(service.testConn("1"));
        AjaxUtil.sendJSON(response, listResponse);
    }
}
