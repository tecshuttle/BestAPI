package com.best.web.dao.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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
import com.best.web.model.cust.ServiceLog;
import com.best.web.model.essence.CustInfo;

@Repository
public class CustDaoImpl implements CustDao {
    @Resource
    private SqlSession sqlSession;

    //安信
    public List<CustInfo> testConn(String id) {
        return sqlSession.selectList("custSql.testConn", id);
    }

    //安信
    public List<CustInfo> syncCust(HashMap<String, String> param) {
        return sqlSession.selectList("custSql.syncCust", param);
    }

    //个人信息

    public CustBaseInfo findCustBaseInfoById(String custId) {
        return sqlSession.selectOne("custSql.findCustBaseInfoById", custId);
    }

    public List<String> checkMobileExists(String custId, String mobile) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("custId", custId);
        map.put("mobile", mobile);
        return sqlSession.selectList("custSql.checkMobileExists", map);
    }

    public List<String> checkMailExists(String custId, String mail) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("custId", custId);
        map.put("mail", mail);
        return sqlSession.selectList("custSql.checkMailExists", map);
    }

    public List<String> checkCretNoExists(String custId, String cretType, String cretNo) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("custId", custId);
        map.put("cretType", cretType);
        map.put("cretNo", cretNo);
        return sqlSession.selectList("custSql.checkCretNoExists", map);
    }

    public void insertOrgEmp(Cust model) {
        sqlSession.insert("custSql.insertOrgEmp", model);
    }

    public void insertCustReginfo(CustReginfo model) {
        sqlSession.insert("custSql.insertCustReginfo", model);
    }

    public void insertElAccountByLoginName(Cust model) {
        sqlSession.insert("custSql.insertElAccountByLoginName", model);
    }

    public void updateCustBaseInfo(Cust model) {
        sqlSession.update("custSql.updateCustBaseInfo", model);
    }


    public void updateMobile(CustBaseInfo model) {
        sqlSession.update("custSql.updateMobile", model);
    }


    public void updateMail(CustBaseInfo model) {
        sqlSession.update("custSql.updateMail", model);
    }

    public void deleteCustByMobile(String mobile) {
        sqlSession.delete("custSql.deleteElAccount", mobile);
        sqlSession.delete("custSql.deleteOrgEmp", mobile);
    }

    //家庭成员

    public List<Family> findFamilyList(String custId) {
        return sqlSession.selectList("custSql.findFamilyList", custId);
    }

    public List<FamilyBaseInfo> findFamilyBaseInfoList(String custId) {
        return sqlSession.selectList("custSql.findFamilyBaseInfoList", custId);
    }

    public Family findFamilyById(String id) {
        return sqlSession.selectOne("custSql.findFamilyById", id);
    }

    public void insertFamily(Family model) {
        sqlSession.insert("custSql.insertFamily", model);
    }

    public void updateFamily(Family model) {
        sqlSession.update("custSql.updateFamily", model);
    }

    public void deleteFamily(Family model) {
        sqlSession.update("custSql.deleteFamily", model);
    }

    //会员卡--绑定

    public String checkCardNoExists(String cardNo) {
        return sqlSession.selectOne("custSql.checkCardNoExists", cardNo);
    }

    public CardParam findCardByCardNo(String cardNo) {
        CardParam param = new CardParam();
        param.setCardNo(cardNo);
        return sqlSession.selectOne("custSql.findCardByCardNo", param);
    }

    public void addCardNoVerifyCount(String cardNo) {
        sqlSession.update("custSql.addCardNoVerifyCount", cardNo);
    }

    public void updateCardNoStatus(CardParam model) {
        sqlSession.update("custSql.updateCardNoStatus", model);
    }

    public void updateChangePackageCount(String custCardId) {
        sqlSession.update("custSql.updateChangePackageCount", custCardId);
    }

    public void insertCustCard(CardParam model) {
        sqlSession.insert("custSql.insertCustCard", model);
    }

    public List<String> checkCustCardPackageExists(String custCardId) {
        return sqlSession.selectList("custSql.checkCustCardPackageExists", custCardId);
    }

    public List<String> checkUsedServiceExistsInPackage(String custCardId) {
        return sqlSession.selectList("custSql.checkUsedServiceExistsInPackage", custCardId);
    }

    public CardParam checkWhetherChangePackage(String custCardId) {
        return sqlSession.selectOne("custSql.checkWhetherChangePackage", custCardId);
    }

    public void insertCustCardPackage(CardParam model) {
        sqlSession.insert("custSql.insertCustCardPackage", model);
    }

    public void deleteCustCardPackage(String custCardId) {
        sqlSession.update("custSql.deleteCustCardPackage", custCardId);
    }

    public void insertCustServiceByPackage(CardParam model) {
        sqlSession.insert("custSql.insertCustServiceByPackage", model);
    }

    public void insertCustService(CardPackage model) {
        sqlSession.insert("custSql.insertCustService", model);
    }

    public void deleteCustServiceInPackage(String custCardId) {
        sqlSession.update("custSql.deleteCustServiceInPackage", custCardId);
    }


    //会员卡--详情
    public Card findCustCardById(String custCardId) {
        return sqlSession.selectOne("custSql.findCustCardById", custCardId);
    }

    public List<HashMap<String, Object>> findCustCardInfo(String custId) {
        return sqlSession.selectList("custSql.findCustCardInfo", custId);
    }

    public List<Card> findCustNotBindCardList(String custId) {
        return sqlSession.selectList("custSql.findCustNotBindCardList", custId);
    }

    public List<Card> findCustCardList(String custId) {
        return sqlSession.selectList("custSql.findCustCardList", custId);
    }

    public List<CardPackage> findCardPackageListByCardId(String cardId) {
        return sqlSession.selectList("custSql.findCardPackageListByCardId", cardId);
    }

    public List<CardPackage> findCardPackageListByCustCardId(String custCardId) {
        return sqlSession.selectList("custSql.findCardPackageListByCustCardId", custCardId);
    }

    public List<CardPackage> findCardPackageListWithServiceByCustCardId(String custCardId) {
        List<CardPackage> cardPackageList = sqlSession.selectList("custSql.findCardPackageListByCustCardId", custCardId);
        for (CardPackage cardPackage : cardPackageList) {
            cardPackage.setServiceList(this.findServiceListInPackage(cardPackage.getPackageId()));
        }
        return cardPackageList;
    }

    public List<CardService> findCardPackageServiceList(String custCardId) {
        return sqlSession.selectList("custSql.findCardPackageServiceList", custCardId);
    }

    //会员卡
    public List<PackageService> findServiceListInPackage(String packageId) {
        return sqlSession.selectList("custSql.findServiceListInPackage", packageId);
    }

    //会员卡--服务
    public List<CardService> findCustServiceList(String custId, String status, String validFlag, String serviceType, String serviceId) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("custId", custId);
        map.put("status", status);
        map.put("validFlag", validFlag);
        map.put("serviceType", serviceType);
        map.put("serviceId", serviceId);
        return sqlSession.selectList("custSql.findCustServiceList", map);
    }

    public List<CardService> findCustServiceTotalList(String custId) {
        return sqlSession.selectList("custSql.findCustServiceTotalList", custId);
    }

    public List<ServiceLog> findCustServiceLogList(String custId) {
        return sqlSession.selectList("custSql.findCustServiceLogList", custId);
    }
}
