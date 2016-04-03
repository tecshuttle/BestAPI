package com.best.web.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.best.msg.BaseResponse;
import com.best.msg.GenResponse;
import com.best.msg.ListResponse;
import com.best.util.AjaxUtil;
import com.best.util.BasicUtil;
import com.best.util.ResultCodes;
import com.best.web.model.cust.Card;
import com.best.web.model.cust.CardPackage;
import com.best.web.model.cust.CardParam;
import com.best.web.model.cust.CardService;
import com.best.web.model.cust.CustBaseInfo;
import com.best.web.model.cust.Family;
import com.best.web.model.cust.FamilyBaseInfo;
import com.best.web.model.cust.PackageService;
import com.best.web.model.cust.Regist;
import com.best.web.model.cust.RegistCard;
import com.best.web.model.cust.ServiceLog;
import com.best.web.service.CustService;
import com.best.web.validator.cust.CustBaseInfoValidator;
import com.best.web.validator.cust.FamilyValidator;
import com.best.web.validator.cust.RegistValidator;

@Controller
@RequestMapping("/cust")
public class CustController {
	
	StringBuffer errorMsg;
	
	@Resource
	private CustService service;
	@Resource  
    private RegistValidator registValidator;
	@Resource
	private FamilyValidator familyValidator;
	@Resource
	private CustBaseInfoValidator custBaseInfoValidator;
	
	//TODO 上线删除
	@RequestMapping(value="/deleteCustByMobile",method=RequestMethod.POST)
	public void deleteCustByMobile(HttpServletRequest request,HttpServletResponse response, String mobile) throws Exception {
		BaseResponse genResponse = new BaseResponse();
		service.deleteCustByMobile(mobile);
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	/**  个人信息    **/
	
	@RequestMapping(value="/registByMobile",method=RequestMethod.POST)
	public void registByMobile(HttpServletRequest request,HttpServletResponse response,Regist regist, BindingResult result) throws Exception {
		System.out.println(regist.getChannel());
		GenResponse<String> genResponse = new GenResponse<String>();
		this.registValidator.validate(regist, result);
		if(result.hasErrors()){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(BasicUtil.extractErrors(result.getFieldErrors()));
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		String custId = BasicUtil.generateId();
		String msg = service.insertCustBaseInfoByMobile(regist,custId);
		if(msg!=null){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(msg);
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		genResponse.setResponse(custId);
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/registByCard",method=RequestMethod.POST)
	public void registByCard(HttpServletRequest request,HttpServletResponse response, RegistCard registCard, BindingResult result) throws Exception {
		GenResponse<String> genResponse = new GenResponse<String>();
		this.registValidator.validate(registCard, result);
		if(result.hasErrors()){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(BasicUtil.extractErrors(result.getFieldErrors()));
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		String custId = BasicUtil.generateId();
		String msg = service.insertCustBaseInfoByCard(registCard,custId);
		if(msg!=null){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(msg);
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		genResponse.setResponse(custId);
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/findCustBaseInfoById",method=RequestMethod.POST)
	public void findCustBaseInfoById(HttpServletRequest request,HttpServletResponse response, String custId) throws Exception {
		GenResponse<CustBaseInfo> genResponse = new GenResponse<CustBaseInfo>();
		genResponse.setResponse(service.findCustBaseInfoById(custId));
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/checkMobileExists",method=RequestMethod.POST)
	public void checkMobileExists(HttpServletRequest request,HttpServletResponse response,String custId,String mobile) throws Exception {
		GenResponse<Boolean> genResponse = new GenResponse<Boolean>();
		if(BasicUtil.isEmpty(mobile)){
			genResponse.setResultCode("-1");
			genResponse.setMsg("手机号为空");
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}else if(!BasicUtil.isMobile(mobile)){
			genResponse.setResultCode("-1");
			genResponse.setMsg("手机号格式错误");
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		genResponse.setResponse(service.checkMobileExists(custId, mobile));
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/checkMailExists",method=RequestMethod.POST)
	public void checkMailExists(HttpServletRequest request,HttpServletResponse response, String custId,String mail) throws Exception {
		GenResponse<Boolean> genResponse = new GenResponse<Boolean>();
		if(BasicUtil.isEmpty(mail)){
			genResponse.setResultCode("-1");
			genResponse.setMsg("邮箱为空");
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}else if(!BasicUtil.isMail(mail)){
			genResponse.setResultCode("-1");
			genResponse.setMsg("邮箱格式错误");
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		genResponse.setResponse(service.checkMailExists(custId, mail));
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/checkCretNoExists",method=RequestMethod.POST)
	public void checkCretNoExists(HttpServletRequest request,HttpServletResponse response, String custId,String cretType,String cretNo) throws Exception {
		GenResponse<Boolean> genResponse = new GenResponse<Boolean>();
		if(BasicUtil.isEmpty(cretNo)){
			genResponse.setResultCode("-1");
			genResponse.setMsg("证件号为空");
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}else if(!BasicUtil.isEmpty(cretType)){
			if(cretType.equals("1") && !BasicUtil.isIdcard(cretNo)){
				genResponse.setResultCode("-1");
				genResponse.setMsg("身份证号格式错误");
				AjaxUtil.sendJSON(response, genResponse);
				return;
			}
		}
		genResponse.setResponse(service.checkCretNoExists(custId, cretNo));
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	
	@RequestMapping(value="/updateCustBaseInfo",method=RequestMethod.POST)
	public void updateCustBaseInfo(HttpServletRequest request,HttpServletResponse response,String channel, CustBaseInfo custBaseInfo,BindingResult result) throws Exception {
		BaseResponse genResponse = new BaseResponse();
		if(!BasicUtil.checkChannel(channel)){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg("渠道参数错误");
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		this.custBaseInfoValidator.validate(custBaseInfo, result);
		if(result.hasErrors()){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(BasicUtil.extractErrors(result.getFieldErrors()));
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		service.updateCustBaseInfo(channel,custBaseInfo);
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/updateMobile",method=RequestMethod.POST)
	public void updateMobile(HttpServletRequest request,HttpServletResponse response,String custId,String mobile) throws Exception {
		BaseResponse genResponse = new BaseResponse();
		if(BasicUtil.isEmpty(custId)){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg("客户ID为空");
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		if(BasicUtil.isEmpty(mobile)){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg("手机号为空");
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}else if(!BasicUtil.isMobile(mobile)){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg("手机号格式错误");
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		String msg = service.updateMobile(custId,mobile);
		if(msg!=null){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(msg);
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/updateMail",method=RequestMethod.POST)
	public void updateMail(HttpServletRequest request,HttpServletResponse response, String custId,String mail) throws Exception {
		BaseResponse genResponse = new BaseResponse();
		if(BasicUtil.isEmpty(custId)){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg("客户ID为空");
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		if(BasicUtil.isEmpty(mail)){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg("邮件为空");
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}else if(!BasicUtil.isMail(mail)){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg("邮件号格式错误");
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		if(service.checkMailExists(custId, mail)){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg("邮件已存在");
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		service.updateMail(custId,mail);
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	/**  家庭成员    **/
	
	@RequestMapping(value="/findFamilyList",method=RequestMethod.POST)
	public void findFamilyList(HttpServletRequest request,HttpServletResponse response, String custId) throws Exception {
		ListResponse<Family> listResponse = new ListResponse<Family>();
		if(BasicUtil.isEmpty(custId)){
			listResponse.setResultCode("-1");
			listResponse.setMsg("客户ID为空");
			AjaxUtil.sendJSON(response, listResponse);
			return;
		}
		listResponse.setResponse(service.findFamilyList(custId));
		AjaxUtil.sendJSON(response, listResponse);
	}
	
	@RequestMapping(value="/findFamilyBaseInfoList",method=RequestMethod.POST)
	public void findFamilyBaseInfoList(HttpServletRequest request,HttpServletResponse response,String custId) throws Exception {
		ListResponse<FamilyBaseInfo> listResponse = new ListResponse<FamilyBaseInfo>();
		if(BasicUtil.isEmpty(custId)){
			listResponse.setResultCode("-1");
			listResponse.setMsg("客户ID为空");
			AjaxUtil.sendJSON(response, listResponse);
			return;
		}
		listResponse.setResponse(service.findFamilyBaseInfoList(custId));
		AjaxUtil.sendJSON(response, listResponse);
	}
	
	@RequestMapping(value="/findFamilyById",method=RequestMethod.POST)
	public void findFamilyById(HttpServletRequest request,HttpServletResponse response, String id) throws Exception {
		GenResponse<Family> genResponse = new GenResponse<Family>();
		if(BasicUtil.isEmpty(id)){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg("id为空");
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		genResponse.setResponse(service.findFamilyById(id));
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/addFamily",method=RequestMethod.POST)
	public void addFamily(HttpServletRequest request,HttpServletResponse response, Family family,BindingResult result) throws Exception {
		GenResponse<String> genResponse = new GenResponse<String>();
		this.familyValidator.validate(family, result);
		if(result.hasErrors()){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(BasicUtil.extractErrors(result.getFieldErrors()));
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		service.insertFamily(family);
		genResponse.setResponse(family.getId());
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/updateFamily",method=RequestMethod.POST)
	public void updateFamily(HttpServletRequest request,HttpServletResponse response, Family family,BindingResult result) throws Exception {
		BaseResponse baseResponse = new BaseResponse();
		this.familyValidator.validate(family, result);
		if(result.hasErrors()){
			baseResponse.setResultCode(ResultCodes.ERROR);
			baseResponse.setMsg(BasicUtil.extractErrors(result.getFieldErrors()));
			AjaxUtil.sendJSON(response, baseResponse);
			return;
		}
		if(BasicUtil.isEmpty(family.getId())){
			baseResponse.setResultCode(ResultCodes.ERROR);
			baseResponse.setMsg("家庭成员ID为空");
			AjaxUtil.sendJSON(response, baseResponse);
			return;
		}
		service.updateFamily(family);
		AjaxUtil.sendJSON(response, baseResponse);
	}
	
	@RequestMapping(value="/deleteFamily",method=RequestMethod.POST)
	public void deleteFamily(HttpServletRequest request,HttpServletResponse response, Family family) throws Exception {
		BaseResponse baseResponse = new BaseResponse();
		if(BasicUtil.isEmpty(family.getId())){
			baseResponse.setResultCode(ResultCodes.ERROR);
			baseResponse.setMsg("家庭成员ID为空");
			AjaxUtil.sendJSON(response, baseResponse);
			return;
		}
		service.deleteFamily(family);
		AjaxUtil.sendJSON(response, baseResponse);
	}
	
	/**  会员卡    **/
	
	@RequestMapping(value="/checkCardNoExists",method=RequestMethod.POST)
	public void checkCardNoExists(HttpServletRequest request,HttpServletResponse response, String cardNo) throws Exception {
		errorMsg = new StringBuffer();
		GenResponse<Boolean> genResponse = new GenResponse<Boolean>();
		if(BasicUtil.isEmpty(cardNo)){
			errorMsg.append("卡号为空,");
		}
		if(BasicUtil.extractErrors(errorMsg)!=null){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(BasicUtil.extractErrors(errorMsg));
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		boolean checkResult = service.checkCardNoExists(cardNo);
		genResponse.setResponse(checkResult);
		if(checkResult){
			CardParam cardParam = service.findCardByCardNo(cardNo);
			if(cardParam.getCardNoMemo()!=null && "VIP-GIFT".equals(cardParam.getCardNoMemo())){
				genResponse.setMsg(cardNo+" 是安信VIP健康卡，诚邀您致电：<br>4001-000-090，尊享专业客服为您开通VIP激活。");
			}
		}
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/verifyCardNo",method=RequestMethod.POST)
	public void verifyCardNo(HttpServletRequest request,HttpServletResponse response, String cardNo,String cardCode) throws Exception {
		errorMsg = new StringBuffer();
		BaseResponse baseResponse = new BaseResponse();
		if(BasicUtil.isEmpty(cardNo)){
			errorMsg.append("卡号为空,");
		}
		if(BasicUtil.isEmpty(cardCode)){
			errorMsg.append("卡密为空,");
		}
		if(BasicUtil.extractErrors(errorMsg)!=null){
			baseResponse.setResultCode(ResultCodes.ERROR);
			baseResponse.setMsg(BasicUtil.extractErrors(errorMsg));
			AjaxUtil.sendJSON(response, baseResponse);
			return;
		}
		String verifyMsg = service.verifyCardNo(cardNo, cardCode);
		if(BasicUtil.isNotEmpty(verifyMsg)){
			baseResponse.setResultCode(ResultCodes.ERROR);
			baseResponse.setMsg(verifyMsg);
			AjaxUtil.sendJSON(response, baseResponse);
			return;
		}
		AjaxUtil.sendJSON(response, baseResponse);
	}
	
	@RequestMapping(value="/addCustCard",method=RequestMethod.POST)
	public void addCustCard(HttpServletRequest request,HttpServletResponse response,String custId,String cardNo,String cardCode) throws Exception {
		errorMsg = new StringBuffer();
		GenResponse<String> genResponse = new GenResponse<String>();
		if(BasicUtil.isEmpty(custId)){
			errorMsg.append("客户ID为空,");
		}
		if(BasicUtil.isEmpty(cardNo)){
			errorMsg.append("卡号为空,");
		}
		if(BasicUtil.isEmpty(cardCode)){
			errorMsg.append("卡密为空,");
		}
		if(BasicUtil.extractErrors(errorMsg)!=null){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(BasicUtil.extractErrors(errorMsg));
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		String custCardId = BasicUtil.generateId();
		String msg = service.addCustCard(custId,custCardId, cardNo, cardCode);
		if(msg!=null){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(msg);
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		genResponse.setResponse(custCardId);
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/bindCardPackage",method=RequestMethod.POST)
	public void bindCardPackage(HttpServletRequest request,HttpServletResponse response,String custCardId,String packageId) throws Exception {
		errorMsg = new StringBuffer();
		BaseResponse baseResponse = new BaseResponse();
		if(BasicUtil.isEmpty(custCardId)){
			errorMsg.append("客户卡ID为空,");
		}
		if(BasicUtil.isEmpty(packageId)){
			errorMsg.append("套餐ID为空,");
		}
		if(BasicUtil.extractErrors(errorMsg)!=null){
			baseResponse.setResultCode(ResultCodes.ERROR);
			baseResponse.setMsg(BasicUtil.extractErrors(errorMsg));
			AjaxUtil.sendJSON(response, baseResponse);
			return;
		}
		String msg = service.bindCardPackage(custCardId, packageId);
		if(msg!=null){
			baseResponse.setResultCode(ResultCodes.ERROR);
			baseResponse.setMsg(msg);
			AjaxUtil.sendJSON(response, baseResponse);
			return;
		}
		AjaxUtil.sendJSON(response, baseResponse);
	}
	
	@RequestMapping(value="/changeCardPackage",method=RequestMethod.POST)
	public void changeCardPackage(HttpServletRequest request,HttpServletResponse response,String custCardId,String newPackageId) throws Exception {
		errorMsg = new StringBuffer();
		BaseResponse baseResponse = new BaseResponse();
		if(BasicUtil.isEmpty(custCardId)){
			errorMsg.append("客户卡ID为空,");
		}
		if(BasicUtil.isEmpty(newPackageId)){
			errorMsg.append("套餐ID为空,");
		}
		if(BasicUtil.extractErrors(errorMsg)!=null){
			baseResponse.setResultCode(ResultCodes.ERROR);
			baseResponse.setMsg(BasicUtil.extractErrors(errorMsg));
			AjaxUtil.sendJSON(response, baseResponse);
			return;
		}
		String msg = service.changeCardPackage(custCardId, newPackageId);
		if(msg!=null){
			baseResponse.setResultCode(ResultCodes.ERROR);
			baseResponse.setMsg(msg);
			AjaxUtil.sendJSON(response, baseResponse);
			return;
		}
		AjaxUtil.sendJSON(response, baseResponse);
	}
	
	@RequestMapping(value="/findCustCardInfo",method=RequestMethod.POST)
	public void findCustCardInfo(HttpServletRequest request,HttpServletResponse response, String custId) throws Exception {
		GenResponse<HashMap<String,BigDecimal>> genResponse = new GenResponse<HashMap<String,BigDecimal>>();
		genResponse.setResponse(service.findCustCardInfo(custId));
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/findCustNotBindCardList",method=RequestMethod.POST)
	public void findCustNotBindCardList(HttpServletRequest request,HttpServletResponse response, String custId) throws Exception {
		ListResponse<Card> listResponse = new ListResponse<Card>();
		listResponse.setResponse(service.findCustNotBindCardList(custId));
		AjaxUtil.sendJSON(response, listResponse);
	}
	
	@RequestMapping(value="/findCustCardList",method=RequestMethod.POST)
	public void findCustCardList(HttpServletRequest request,HttpServletResponse response, String custId) throws Exception {
		ListResponse<Card> listResponse = new ListResponse<Card>();
		listResponse.setResponse(service.findCustCardList(custId));
		AjaxUtil.sendJSON(response, listResponse);
	}
	
	@RequestMapping(value="/findCustCardById",method=RequestMethod.POST)
	public void findCustCardById(HttpServletRequest request,HttpServletResponse response, String custCardId) throws Exception {
		GenResponse<Card> listResponse = new GenResponse<Card>();
		listResponse.setResponse(service.findCustCardById(custCardId));
		AjaxUtil.sendJSON(response, listResponse);
	}
	
	@RequestMapping(value="/findCardPackageList",method=RequestMethod.POST)
	public void findCardPackageList(HttpServletRequest request,HttpServletResponse response, String custCardId) throws Exception {
		ListResponse<CardPackage> listResponse = new ListResponse<CardPackage>();
		listResponse.setResponse(service.findCardPackageListByCustCardId(custCardId));
		AjaxUtil.sendJSON(response, listResponse);
	}
	
	@RequestMapping(value="/findServiceListInPackage",method=RequestMethod.POST)
	public void findServiceListInPackage(HttpServletRequest request,HttpServletResponse response, String packageId) throws Exception {
		ListResponse<PackageService> listResponse = new ListResponse<PackageService>();
		listResponse.setResponse(service.findServiceListInPackage(packageId));
		AjaxUtil.sendJSON(response, listResponse);
	}
	
	@RequestMapping(value="/findServiceListInBindCard",method=RequestMethod.POST)
	public void findServiceListInBindCard(HttpServletRequest request,HttpServletResponse response, String custCardId) throws Exception {
		ListResponse<CardService> listResponse = new ListResponse<CardService>();
		listResponse.setResponse(service.findCardPackageServiceList(custCardId));
		AjaxUtil.sendJSON(response, listResponse);
	}
	
	@RequestMapping(value="/findCustServiceList",method=RequestMethod.POST)
	public void findCustServiceList(HttpServletRequest request,HttpServletResponse response, String custId) throws Exception {
		ListResponse<CardService> listResponse = new ListResponse<CardService>();
		listResponse.setResponse(service.findCustAllServiceList(custId));
		AjaxUtil.sendJSON(response, listResponse);
	}
	
	@RequestMapping(value="/findCustValidServiceList",method=RequestMethod.POST)
	public void findCustValidServiceList(HttpServletRequest request,HttpServletResponse response, String custId,String serviceType,String serviceId) throws Exception {
		ListResponse<CardService> listResponse = new ListResponse<CardService>();
		listResponse.setResponse(service.findCustValidServiceList(custId,serviceType,serviceId));
		AjaxUtil.sendJSON(response, listResponse);
	}
	
	@RequestMapping(value="/findCustServiceTotalList",method=RequestMethod.POST)
	public void findCustServiceTotalList(HttpServletRequest request,HttpServletResponse response, String custId) throws Exception {
		ListResponse<CardService> listResponse = new ListResponse<CardService>();
		listResponse.setResponse(service.findCustServiceTotalList(custId));
		AjaxUtil.sendJSON(response, listResponse);
	}
	
	@RequestMapping(value="/findCustServiceLogList",method=RequestMethod.POST)
	public void findCustServiceLogList(HttpServletRequest request,HttpServletResponse response, String custId) throws Exception {
		ListResponse<ServiceLog> listResponse = new ListResponse<ServiceLog>();
		listResponse.setResponse(service.findCustServiceLogList(custId));
		AjaxUtil.sendJSON(response, listResponse);
	}
}
