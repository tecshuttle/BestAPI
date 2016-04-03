package com.best.web.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.best.util.BasicUtil;
import com.best.web.dao.CustDao;
import com.best.web.model.cust.Card;
import com.best.web.model.cust.CardPackage;
import com.best.web.model.cust.CardParam;
import com.best.web.model.cust.CardService;
import com.best.web.model.cust.Cust;
import com.best.web.model.cust.CustBaseInfo;
import com.best.web.model.cust.CustReginfo;
import com.best.web.model.cust.Family;
import com.best.web.model.cust.FamilyBaseInfo;
import com.best.web.model.cust.PackageService;
import com.best.web.model.cust.Regist;
import com.best.web.model.cust.RegistCard;
import com.best.web.model.cust.ServiceLog;
import com.best.web.model.essence.CustInfo;

@Service
public class CustService {
	@Resource
	private CustDao custDao;

	//安信接口
	public List<CustInfo> testConn(String id){
		return custDao.testConn(id);
	}
	
	//安信接口
	public List<CustInfo> syncCust(String start_date,String end_date){
		HashMap<String,String> param = new HashMap<String, String>();
		param.put("start_date", start_date);
		param.put("end_date", end_date);
		return custDao.syncCust(param);
	}
	
	//会员中心
	public String insertCustBaseInfoByMobile(Regist regist,String custId){
		if(checkMobileExists(regist.getMobile())){
			return "手机号已被注册";
		}
		Cust cust = new Cust();
		cust.setId(BasicUtil.generateId());
		cust.setCreator(regist.getChannel());
		cust.setElAccountId(custId);
		cust.setLoginName(regist.getMobile());
		cust.setMobile(regist.getMobile());
		
		cust.setCustOrgType("PERSONAL");
		cust.setCareType("1");//1注册用户2铜牌会员3银牌会员4金牌会员5钻石会员
		cust.setSourceType(regist.getChannel());
		cust.setDataStatus("1");
		custDao.insertOrgEmp(cust);
		custDao.insertElAccountByLoginName(cust);
		
		CustReginfo custReginfo = new CustReginfo();
		custReginfo.setId(BasicUtil.generateId()); 
		custReginfo.setAvailableFlag("0");
		custReginfo.setModifier(cust.getCreator());
		custReginfo.setCustId(custId);
		custReginfo.setSiteId(null);
		custDao.insertCustReginfo(custReginfo);
		return null;
	}
	
	public String insertCustBaseInfoByCard(RegistCard regist,String custId){
		if(checkMobileExists(regist.getMobile())){
			return "手机号已被注册";
		}
		String verifyMsg = verifyCardNo(regist.getCardNo(), regist.getCardCode());
		if(BasicUtil.isNotEmpty(verifyMsg)){
			return verifyMsg;
		}
		// 检查卡号 todo
		Cust cust = new Cust();
		cust.setId(BasicUtil.generateId());
		cust.setCreator(regist.getChannel());
		cust.setElAccountId(custId);
		cust.setLoginName(regist.getMobile());
		cust.setMobile(regist.getMobile());
		
		cust.setCustOrgType("PERSONAL");
		cust.setCareType("1");//1注册用户2铜牌会员3银牌会员4金牌会员5钻石会员
		cust.setSourceType(regist.getChannel());
		cust.setDataStatus("1");
		custDao.insertOrgEmp(cust);
		custDao.insertElAccountByLoginName(cust);
		
		CustReginfo custReginfo = new CustReginfo();
		custReginfo.setId(BasicUtil.generateId()); 
		custReginfo.setAvailableFlag("0");
		custReginfo.setModifier(cust.getCreator());
		custReginfo.setCustId(custId);
		custReginfo.setSiteId(null);
		custDao.insertCustReginfo(custReginfo);
		
		return addCustCard(custId,BasicUtil.generateId(),regist.getCardNo(), regist.getCardCode());
	}
	
	public CustBaseInfo findCustBaseInfoById(String custId){
		return custDao.findCustBaseInfoById(custId);
	}
	
	public boolean checkCustExists(String custId){
		CustBaseInfo cust = custDao.findCustBaseInfoById(custId);
		if(BasicUtil.isNotEmpty(cust) && BasicUtil.isNotEmpty(cust.getCustId())){
			return true;
		}
		return false;
	}
	
	public boolean checkMobileExists(String mobile){
		if(checkMobileExists(null,mobile)){
			return true;
		}
		return false;
	}
	
	public Boolean checkMobileExists(String custId,String mobile){
		if(custDao.checkMobileExists(custId, mobile).size()>0){
			return true;
		}
		return false;
	}
	
	public boolean checkMailExists(String mail){
		if(checkMailExists(null,mail)){
			return true;
		}
		return false;
	}
	
	public boolean checkMailExists(String custId,String mail){
		if(custDao.checkMailExists(custId, mail).size()>0){
			return true;
		}
		return false;
	}
	
	public boolean checkCretNoExists(String cretType,String cretNo){
		if(checkCretNoExists(null,cretType,cretNo)){
			return true;
		}
		return false;
	}
	
	public boolean checkCretNoExists(String custId,String cretType,String cretNo){
		if(custDao.checkCretNoExists(custId, cretType,cretNo).size()>0){
			return true;
		}
		return false;
	}
	
	public void updateCustBaseInfo(String channel,CustBaseInfo model){
		Cust cust = new Cust();
		cust.setModifier(channel);
		cust.setId(model.getCustId());
		cust.setEmpName(model.getName());
		cust.setSex(model.getSex());
		cust.setBirthDate(model.getBirthday());
		cust.setCretType(model.getCretType());
		cust.setCretNo(model.getCretNo());
		custDao.updateCustBaseInfo(cust);
	}
	
	public String updateMobile(String custId,String mobile){
		if(checkMobileExists(custId,mobile)){
			return "手机号已被使用";
		}
		CustBaseInfo model = new CustBaseInfo();
		model.setCustId(custId);
		model.setMobile(mobile);
		custDao.updateMobile(model);
		return null;
	}
	
	public void updateMail(String custId,String mail){
		CustBaseInfo model = new CustBaseInfo();
		model.setCustId(custId);
		model.setMail(mail);
		custDao.updateMail(model);
	}
	
	public void deleteCustByMobile(String mobile){
		custDao.deleteCustByMobile(mobile);
	}
	
	//家庭成员
	public List<Family> findFamilyList(String custId){
		return custDao.findFamilyList(custId);
	}
	
	public List<FamilyBaseInfo> findFamilyBaseInfoList(String custId){
		return custDao.findFamilyBaseInfoList(custId);
	}
	
	public Family findFamilyById(String id){
		return custDao.findFamilyById(id);
	}
	
	public void insertFamily(Family model){
		model.setId(BasicUtil.generateId());
		custDao.insertFamily(model);
	}
	
	public void updateFamily(Family model){
		custDao.updateFamily(model);
	}
	
	public void deleteFamily(Family model){
		custDao.deleteFamily(model);
	}
	
	//会员卡
	
	public boolean checkCardNoExists(String cardNo){
		if(BasicUtil.isEmpty(custDao.checkCardNoExists(cardNo))){
			return false;
		}
		return true;
	}
	
	public CardParam findCardByCardNo(String cardNo){
		return custDao.findCardByCardNo(cardNo);
	}
	
	public String verifyCardNo(String cardNo,String cardCode){
		CardParam cardParam = custDao.findCardByCardNo(cardNo);
		
		if(BasicUtil.isEmpty(cardParam)){
			return "卡号或卡密错误";
		}else if(cardParam.getVerifyCount()>10){
			return "验证次数过多";
		}else if(!cardCode.equals(cardParam.getCardCode())){
			//增加验证次数
			custDao.addCardNoVerifyCount(cardNo);
			return "卡号或卡密错误";
		}
		//检查卡激活有效期
		Timestamp today = new Timestamp(System.currentTimeMillis());
		if(cardParam.getOpenDate()!=null && cardParam.getCloseDate()!=null){
			if(today.before(cardParam.getOpenDate())){
				return "开始激活日期还未到";
			}else if(today.after(cardParam.getCloseDate())){
				return "激活日期已经结束";
			}
		}
		//检查卡号状态
		if(BasicUtil.isEmpty(cardParam.getStatus())){
			return "卡号状态异常";
		}else if(cardParam.getStatus().equals("3")){//已激活
			return "卡号已被激活";
		}else if(cardParam.getStatus().equals("4")){//已失效
			return "卡号已失效";
		}else if(!"1,2".contains(cardParam.getStatus())){
			return "卡号状态异常";
		}
		return null;
	}
	
	public String addCustCard(String custId,String custCardId,String cardNo,String cardCode){
		String verifyMsg = verifyCardNo(cardNo, cardCode);
		if(BasicUtil.isNotEmpty(verifyMsg)){
			return verifyMsg;
		}
		
		CardParam cardParam = custDao.findCardByCardNo(cardNo);
		//修改卡状态
		cardParam.setStatus("3");
		custDao.updateCardNoStatus(cardParam);
		//增加验证次数
		custDao.addCardNoVerifyCount(cardNo);
		//绑卡
		cardParam.setCustId(custId);
		cardParam.setCustCardId(custCardId);
		cardParam.setCreator("API");
		cardParam.setBindType("CUST");//CUST客户、ORG机构、XZH选最好
		custDao.insertCustCard(cardParam);
		//如果只有一个套餐，直接绑定套餐
		List<CardPackage> cardPackageList = custDao.findCardPackageListByCardId(cardParam.getCardId());
		if(cardPackageList.size()==1){
			bindCardPackage(cardParam.getCustCardId(), cardPackageList.get(0).getPackageId());
		}
		return null;
	}
	
	public String bindCardPackage(String custCardId,String selectPackageId ){
		CardParam cardParam = new CardParam();
		cardParam.setCustCardId(custCardId);
		//检查是否已经绑定
		if(checkCustCardPackageExists(custCardId)){
			return "套餐已绑定，不能重复绑定";
		}
		//绑定新套餐
		cardParam.setPackageId(selectPackageId);
		cardParam.setCustCardPackageId(BasicUtil.generateId());
		custDao.insertCustCardPackage(cardParam);
		custDao.insertCustServiceByPackage(cardParam);
		return null;
	}
	
	public String changeCardPackage(String custCardId,String newPackageId ){
		CardParam cardParam = new CardParam();
		cardParam.setCustCardId(custCardId);
		//检查旧套餐是否被使用
		if(checkUsedServiceExistsInPackage(custCardId)){
			return "原有套餐服务已使用，不允许变更其他套餐";
		}
		//检查修改次是否超限
		String msg = checkWhetherChangePackage(custCardId);
		if(BasicUtil.isNotEmpty(msg)){
			return msg;
		}
		//取消旧套餐&服务
		custDao.deleteCustServiceInPackage(custCardId);
		custDao.deleteCustCardPackage(custCardId);
		//绑定新套餐
		cardParam.setPackageId(newPackageId);
		cardParam.setCustCardPackageId(BasicUtil.generateId());
		custDao.insertCustCardPackage(cardParam);
		custDao.insertCustServiceByPackage(cardParam);
		//卡变更套餐数+1
		custDao.updateChangePackageCount(custCardId);
		return null;
	}
	
	private boolean checkCustCardPackageExists(String custCardId){
		if(BasicUtil.isNotEmpty(custDao.checkCustCardPackageExists(custCardId))){
			return true;
		}
		return false;
	}
	
	private boolean checkUsedServiceExistsInPackage(String custCardId){
		if(BasicUtil.isNotEmpty(custDao.checkUsedServiceExistsInPackage(custCardId))){
			return true;
		}
		return false;
	}
	
	public String checkWhetherChangePackage(String custCardId){
		CardParam cardParam = custDao.checkWhetherChangePackage(custCardId);
		if(cardParam.getAllowChangeCount().intValue()<=cardParam.getChangePackageCount().intValue()){
			return "会员卡不允许变更套餐或修改次数已超过限定次数";
		}
		return null;
		
	}
	
	public Card findCustCardById(String custCardId){
		Card custCard = custDao.findCustCardById(custCardId);
		if(custCard!=null){
			custCard.setPackageList(custDao.findCardPackageListWithServiceByCustCardId(custCard.getCustCardId()));
			if(custCard.getSelectPackageTotal().intValue()>0){//已选定套餐
				custCard.setBindServiceList(custDao.findCardPackageServiceList(custCard.getCustCardId()));
			}
		}
		return custCard;
	}
	
	public HashMap<String,BigDecimal> findCustCardInfo(String custId){
		HashMap<String,BigDecimal> cardInfo = new HashMap<String, BigDecimal>();
		List<HashMap<String,Object>> infoList = custDao.findCustCardInfo(custId);
		for(HashMap<String,Object> info:infoList){
			cardInfo.put(info.get("MGR_CODE").toString(), (BigDecimal)info.get("QUANTITY"));
		}
		return cardInfo;
	}
	
	public List<Card> findCustNotBindCardList(String custId){
		return custDao.findCustNotBindCardList(custId);
	}
	
	public List<Card> findCustCardList(String custId){
		List<Card> custCardList = custDao.findCustCardList(custId);
		for(Card custCard:custCardList){
			custCard.setPackageList(custDao.findCardPackageListByCustCardId(custCard.getCustCardId()));
			if(custCard.getSelectPackageTotal().intValue()>0){//已选定套餐
				custCard.setBindServiceList(custDao.findCardPackageServiceList(custCard.getCustCardId()));
			}
		}
		return custCardList;
	}
	
	public List<CardPackage> findCardPackageListByCardId(String cardId){
		return custDao.findCardPackageListByCardId(cardId);
	}
	
	public List<CardPackage> findCardPackageListByCustCardId(String custCardId){
		return custDao.findCardPackageListByCustCardId(custCardId);
	}
	
	public List<CardService> findCardPackageServiceList(String custCardId){
		return custDao.findCardPackageServiceList(custCardId);
	}
	
	public List<PackageService> findServiceListInPackage(String packageId){
		return custDao.findServiceListInPackage(packageId);
	}
	
	//会员卡--服务
	public List<CardService> findCustAllServiceList(String custId){
		return custDao.findCustServiceList(custId, null, null,null,null);
	}
	
	public List<CardService> findCustValidServiceList(String custId,String serviceType,String serviceId){
		return custDao.findCustServiceList(custId, "1", "1",serviceType,serviceId);
	}
	
	public List<CardService> findCustServiceTotalList(String custId){
		return custDao.findCustServiceTotalList(custId);
	}
	
	public List<ServiceLog> findCustServiceLogList(String custId){
		return custDao.findCustServiceLogList(custId);
	}
	
}
