package com.best.web.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.best.msg.ExtListResponse;
import com.best.msg.ExtResponse;
import com.best.msg.GenResponse;
import com.best.msg.ListResponse;
import com.best.util.AjaxUtil;
import com.best.web.model.admin.CardType;
import com.best.web.model.admin.CardPackageDtl;
import com.best.web.model.admin.CardNoBatch;
import com.best.web.model.cust.CardPackage;
import com.best.web.model.order.CardNo;
import com.best.web.service.CardService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Controller
@RequestMapping("/card")
public class CardController {
    @Resource
    private CardService service;

    /**
     * 卡类型
     * 前面是页面方法，后面是ajax接口
     */
    @RequestMapping(value = "type", method = RequestMethod.GET)
    public ModelAndView typePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("card_type");
        return model;
    }

    @RequestMapping(value = "getTypeList", method = RequestMethod.GET)
    public void getTypeList(HttpServletRequest request, HttpServletResponse response, String company_id) throws Exception {
        ListResponse<CardType> listResponse = new ListResponse<CardType>();

        if (company_id == null) {
            listResponse.setResponse(service.findCardTypeList());
        } else {
            listResponse.setResponse(service.findCardTypeListByCompany(company_id));
        }

        AjaxUtil.sendJSON(response, listResponse);
    }


    @RequestMapping(value = "/updateType", method = RequestMethod.POST)
    public void updateFamily(HttpServletRequest request, HttpServletResponse response, CardType cardType, BindingResult result) throws Exception {
        ExtResponse<String> genResponse = new ExtResponse<String>();
        service.updateCardType(cardType);
        AjaxUtil.sendJSON(response, genResponse);
    }


    @RequestMapping(value = "/insertType", method = RequestMethod.POST)
    public void addFamily(HttpServletRequest request, HttpServletResponse response, CardType cardType, BindingResult result) throws Exception {
        GenResponse<String> genResponse = new GenResponse<String>();
        service.insertCardType(cardType);
        genResponse.setResponse(cardType.getId());
        AjaxUtil.sendJSON(response, genResponse);
    }


    /**
     * 卡号管理
     * 前面是页面方法，后面是ajax接口
     */
    @RequestMapping(value = "no", method = RequestMethod.GET)
    public ModelAndView listPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("card_no");
        return model;
    }


    @RequestMapping(value = "getCardNoList", method = RequestMethod.GET)
    public void getCardNoList(HttpServletRequest request, HttpServletResponse response, int start, int limit) throws Exception {
        ExtListResponse<CardNo> listResponse = new ExtListResponse<CardNo>();
        listResponse.setResponse(service.findCardNoList(), start, limit);
        AjaxUtil.sendJSON(response, listResponse);
    }


    @RequestMapping(value = "/updateCardNo", method = RequestMethod.POST)
    public void updateCardNo(HttpServletRequest request, HttpServletResponse response, CardNo cardType, BindingResult result) throws Exception {
        ExtResponse<String> genResponse = new ExtResponse<String>();
        service.updateCardNo(cardType);
        AjaxUtil.sendJSON(response, genResponse);
    }


    @RequestMapping(value = "/insertCardNo", method = RequestMethod.POST)
    public void insertCardNo(HttpServletRequest request, HttpServletResponse response, CardNo cardType, BindingResult result) throws Exception {
        GenResponse<String> genResponse = new GenResponse<String>();
        service.insertCardNo(cardType);
        genResponse.setResponse(cardType.getId());
        AjaxUtil.sendJSON(response, genResponse);
    }


    @RequestMapping(value = "/insertCardNoBatch", method = RequestMethod.POST)
    public void insertCardNoBatch(HttpServletRequest request, HttpServletResponse response, CardNoBatch batch) throws Exception {
        GenResponse<String> genResponse = new GenResponse<String>();
        String json = JSON.toJSONString(batch, SerializerFeature.WriteMapNullValue);

        int amount = batch.getAmount();

        //生成卡号
        for (int i = 0; i < amount; i++) {
            CardNo newNo = new CardNo();

            newNo.setCard_no(newCardNo());
            newNo.setCard_code(RandomString(6, batch.getCard_code_type()));
            newNo.setCard_no_type(batch.getCard_no_type());
            newNo.setDept_id(batch.getDept_id());
            newNo.setDept2_id(batch.getDept2_id());
            newNo.setBatch_id(batch.getBatch_id());

            service.insertCardNo(newNo);

            //String json_newNo = JSON.toJSONString(newNo, SerializerFeature.WriteMapNullValue);
            //System.out.println(json_newNo);
        }

        AjaxUtil.sendJSON(response, genResponse);
    }

    private String newCardNo() {
        //判断卡号是否存在
        return guid();

        //按卡规则生成卡号
    }

    private boolean isNoExist(String CardNo) {
        return false;
    }

    private String RandomString(int length, String type) {
        String number = "0123456789";
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; i++) {
            if (type.equals("number")) {
                int num = random.nextInt(number.length());
                sb.append(number.charAt(num));
            } else {
                int num = random.nextInt(str.length());
                sb.append(str.charAt(num));
            }
        }

        return sb.toString();
    }

    private String guid() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();

        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        return temp;
    }


    @RequestMapping(value = "batchCardNoExport", method = RequestMethod.GET)
    public void batchCardNoExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //ExtListResponse<CardNo> listResponse = new ExtListResponse<CardNo>();
        //listResponse.setResponse(service.findCardNoList(), start, limit);

        // 创建Excel的工作书册 Workbook,对应到一个excel文档
        HSSFWorkbook wb = new HSSFWorkbook();

        // 创建Excel的工作sheet,对应到一个excel文档的tab
        HSSFSheet sheet = wb.createSheet("sheet1");

        HSSFRow row;
        HSSFCell cell;

        for (int i = 0; i < 60000; i++) {
            row = sheet.createRow(i); // 创建Excel的sheet的一行
            for (int j = 0; j < 30; j++) {
                cell = row.createCell(j); // 创建一个Excel的单元格
                cell.setCellValue("hello world " + i + " " + j);
            }
        }

        FileOutputStream os = new FileOutputStream("e://workbook.xls");
        wb.write(os);
        os.close();

        //AjaxUtil.sendJSON(response, listResponse);
    }


    /**
     * 卡套餐
     * 前面是页面方法，后面是ajax接口
     */
    @RequestMapping(value = "package", method = RequestMethod.GET)
    public ModelAndView suitPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("card_package");
        return model;
    }

    @RequestMapping(value = "getPackageList", method = RequestMethod.GET)
    public void getPackageList(HttpServletRequest request, HttpServletResponse response, String card_id, int start, int limit) throws Exception {
        ExtListResponse<CardPackage> listResponse = new ExtListResponse<CardPackage>();

        if (card_id == null) {
            listResponse.setResponse(service.findCardPackageList(), start, limit);
        } else {
            listResponse.setResponse(service.findCardPackageList(card_id), start, limit);
        }

        AjaxUtil.sendJSON(response, listResponse);
    }


    @RequestMapping(value = "getPackageListByCardId", method = RequestMethod.GET)
    public void getPackageListByCardId(HttpServletRequest request, HttpServletResponse response, String card_id, int start, int limit) throws Exception {
        ExtListResponse<CardPackage> listResponse = new ExtListResponse<CardPackage>();
        listResponse.setResponse(service.findCardPackageListByCardId(card_id), start, limit);
        AjaxUtil.sendJSON(response, listResponse);
    }


    @RequestMapping(value = "updatePackage", method = RequestMethod.POST)
    public void updatePackage(HttpServletRequest request, HttpServletResponse response, CardPackage cardType, BindingResult result) throws Exception {
        ExtResponse<String> genResponse = new ExtResponse<String>();
        service.updateCardPackage(cardType);
        AjaxUtil.sendJSON(response, genResponse);
    }


    @RequestMapping(value = "insertPackage", method = RequestMethod.POST)
    public void insertPackage(HttpServletRequest request, HttpServletResponse response, CardPackage cardType, BindingResult result) throws Exception {
        GenResponse<String> genResponse = new GenResponse<String>();
        service.insertCardPackage(cardType);
        genResponse.setResponse(cardType.getId());
        AjaxUtil.sendJSON(response, genResponse);
    }


    /**
     * 卡套餐 - 服务
     * 仅两个接口
     */
    @RequestMapping(value = "insertPackageDtl", method = RequestMethod.POST)
    public void insertPackageDtl(HttpServletRequest request, HttpServletResponse response, CardPackageDtl model, BindingResult result) throws Exception {
        GenResponse<String> genResponse = new GenResponse<String>();
        service.insertCardPackageDtl(model);
        genResponse.setResponse(model.getId());
        AjaxUtil.sendJSON(response, genResponse);
    }


    @RequestMapping(value = "updatePackageDtl", method = RequestMethod.POST)
    public void updatePackageDtl(HttpServletRequest request, HttpServletResponse response, CardPackageDtl model, BindingResult result) throws Exception {
        ExtResponse<String> genResponse = new ExtResponse<String>();
        service.updateCardPackageDtl(model);
        AjaxUtil.sendJSON(response, genResponse);
    }


}

//end file