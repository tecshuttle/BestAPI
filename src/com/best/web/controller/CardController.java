package com.best.web.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.best.msg.ExtListResponse;
import com.best.msg.ExtResponse;
import com.best.msg.GenResponse;
import com.best.msg.ListResponse;
import com.best.util.AjaxUtil;
import com.best.web.model.admin.CardNoGenBatch;
import com.best.web.model.admin.CardType;
import com.best.web.model.admin.CardPackageDtl;
import com.best.web.model.admin.CardNoBatch;
import com.best.web.model.cust.CardPackage;
import com.best.web.model.order.CardNo;
import com.best.web.service.CardService;
import org.apache.logging.log4j.Logger;
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
import java.text.DecimalFormat;
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


    @RequestMapping(value = "getTypeById", method = RequestMethod.GET)
    public void getTypeById(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
        GenResponse<CardType> genResponse = new GenResponse<CardType>();
        genResponse.setResponse(service.findCardTypeById(id));
        AjaxUtil.sendJSON(response, genResponse);
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


    //卡号批量生成
    @RequestMapping(value = "noGen", method = RequestMethod.GET)
    public ModelAndView noGenPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("card_no_gen_batch");
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


    //卡号生成
    @RequestMapping(value = "getCardNoGenBatchList", method = RequestMethod.GET)
    public void getCardNoGenBatchList(HttpServletRequest request, HttpServletResponse response, int start, int limit) throws Exception {
        ExtListResponse<CardNoGenBatch> listResponse = new ExtListResponse<CardNoGenBatch>();
        listResponse.setResponse(service.findCardNoGenBatchList(), start, limit);
        AjaxUtil.sendJSON(response, listResponse);
    }


    @RequestMapping(value = "/updateCardNoGenBatch", method = RequestMethod.POST)
    public void updateCardNoGenBatch(HttpServletRequest request, HttpServletResponse response, CardNoGenBatch model, BindingResult result) throws Exception {
        ExtResponse<String> genResponse = new ExtResponse<String>();
        service.updateCardNoGenBatch(model);
        AjaxUtil.sendJSON(response, genResponse);
    }


    @RequestMapping(value = "/insertCardNoGenBatch", method = RequestMethod.POST)
    public void insertCardNoGenBatch(HttpServletRequest request, HttpServletResponse response, CardNoGenBatch model, BindingResult result) throws Exception {
        GenResponse<String> genResponse = new GenResponse<String>();
        service.insertCardNoGenBatch(model);
        genResponse.setResponse(model.getId());
        AjaxUtil.sendJSON(response, genResponse);
    }


    @RequestMapping(value = "/insertCardNoBatch", method = RequestMethod.POST)
    public void insertCardNoBatch(HttpServletRequest request, HttpServletResponse response, CardNoBatch batch) throws Exception {
        //取卡信息，获取卡号生成规则
        CardType card = service.findCardTypeById(batch.getCard_id());

        //登记批次记录
        CardNoGenBatch gen = new CardNoGenBatch();
        gen.setCard_id(batch.getCard_id());
        gen.setCard_type(batch.getCard_no_type());
        gen.setGen_quantity(batch.getAmount());
        gen.setBatch_no(batch.getBatch_no());
        gen.setProposer(batch.getProposer());
        gen.setMemo(batch.getBatch_id());

        service.insertCardNoGenBatch(gen);

        //获取最大卡号流水
        CardType card_max_card_sn = service.findMaxCardSn(card.getCard_no_prefix());
        int card_no_max = Integer.parseInt(card_max_card_sn.getMax_sn()) + 1;

        //生成卡号
        int amount = batch.getAmount();
        for (int i = 0; i < amount; i++) {
            String card_code = RandomString(card.getCard_code_length(), batch.getCard_code_type());
            String card_sn = new DecimalFormat(repeat("0", card.getCard_no_sn_length())).format(card_no_max + i);
            String card_no = card.getCard_no_prefix() + card_sn;

            CardNo newNo = new CardNo();

            newNo.setCard_id(batch.getCard_id());
            newNo.setCard_no(newCardNo(card_no));
            newNo.setCard_code(card_code);
            newNo.setCard_no_type(batch.getCard_no_type());
            newNo.setDept_id(batch.getDept_id());
            newNo.setDept2_id(batch.getDept2_id());
            newNo.setActive_flag("1");
            newNo.setStatus("1");

            newNo.setBatch_id(gen.getId());

            service.insertCardNo(newNo);
        }

        GenResponse<String> genResponse = new GenResponse<String>();
        AjaxUtil.sendJSON(response, genResponse);

    }

    private String repeat(String chr, int n) {
        String str = "";

        for (int i = 0; i < n; i++) {
            str += chr;
        }

        return str;
    }

    private String newCardNo(String card_no) {
        //判断卡号是否存在

        return card_no;
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