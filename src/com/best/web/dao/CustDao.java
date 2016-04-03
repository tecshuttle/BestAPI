package com.best.web.dao;

import java.util.HashMap;
import java.util.List;

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
import com.best.web.model.cust.ServiceLog;
import com.best.web.model.essence.CustInfo;


public interface CustDao {
	public List<CustInfo> testConn(String id);

	//安信
	public List<CustInfo> syncCust(HashMap<String,String> param);

	//个人信息
	public CustBaseInfo findCustBaseInfoById(String custId);
	public List<String> checkMobileExists(String custId,String mobile);
	public List<String> checkMailExists(String custId,String mail);
	public List<String> checkCretNoExists(String custId,String cretType,String cretNo);
	public void insertOrgEmp(Cust model);
	public void insertElAccountByLoginName(Cust model);
	public void insertCustReginfo(CustReginfo model);
	public void updateCustBaseInfo(Cust model);
	public void updateMobile(CustBaseInfo model);
	public void updateMail(CustBaseInfo model);
	
	public void deleteCustByMobile(String mobile);
	
	//家庭成员
	public List<Family> findFamilyList(String custId);
	public List<FamilyBaseInfo> findFamilyBaseInfoList(String custId);
	public Family findFamilyById(String id);
	public void insertFamily(Family model);
	public void updateFamily(Family model);
	public void deleteFamily(Family model);
	
	//会员卡--绑定
	public String checkCardNoExists(String cardNo);
	public CardParam findCardByCardNo(String cardNo);
	public void addCardNoVerifyCount(String cardNo);
	public void updateCardNoStatus(CardParam model);
	public void updateChangePackageCount(String custCardId);
	public void insertCustCard(CardParam model);
	public List<String> checkCustCardPackageExists(String custCardId);
	public List<String> checkUsedServiceExistsInPackage(String custCardId);
	public CardParam checkWhetherChangePackage(String custCardId);
	public void insertCustCardPackage(CardParam model);
	public void deleteCustCardPackage(String custCardId);
	public void insertCustServiceByPackage(CardParam model);
	public void insertCustService(CardPackage model);
	public void deleteCustServiceInPackage(String custCardId);
	
	//会员卡--详情
	public Card findCustCardById(String custCardId);
	public List<HashMap<String,Object>> findCustCardInfo(String custId);
	public List<Card> findCustNotBindCardList(String custId);
	public List<Card> findCustCardList(String custId);
	public List<CardPackage> findCardPackageListByCardId(String cardId);
	public List<CardPackage> findCardPackageListWithServiceByCustCardId(String custCardId);
	public List<CardPackage> findCardPackageListByCustCardId(String custCardId);
	public List<CardService> findCardPackageServiceList(String custCardId);
	
	//会员卡
	public List<PackageService> findServiceListInPackage(String packageId);
	
	//会员卡--服务
	public List<CardService> findCustServiceList(String custId,String status,String validFlag,String serviceType,String serviceId);
	public List<CardService> findCustServiceTotalList(String custId);
	public List<ServiceLog> findCustServiceLogList(String custId);
}
