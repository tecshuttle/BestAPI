package com.best.web.dao.impl;

import com.best.web.dao.CardDao;
import com.best.web.model.admin.CardType;
import com.best.web.model.admin.CardPackageDtl;
import com.best.web.model.order.CardNo;
import com.best.web.model.cust.CardPackage;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class CardDaoImpl implements CardDao {
    @Resource
    private SqlSession sqlSession;

    //卡类型
    public List<CardType> findCardTypeList() {
        return sqlSession.selectList("cardSql.findCardTypeList");
    }

    public List<CardType> findCardTypeListByCompany(String company_id) {
        return sqlSession.selectList("cardSql.findCardTypeListByCompany", company_id);
    }

    public void insertCardType(CardType model) {
        sqlSession.insert("cardSql.insertCardType", model);
    }

    public void updateCardType(CardType model) {
        sqlSession.insert("cardSql.updateCardType", model);
    }

    public void deleteCardType(CardType model) {
        sqlSession.insert("cardSql.deleteCardType", model);
    }


    //卡号管理
    public List<CardNo> findCardNoList() {
        return sqlSession.selectList("cardSql.findCardNoList");
    }

    public void insertCardNo(CardNo model) {
        sqlSession.insert("cardSql.insertCardNo", model);
    }

    public void updateCardNo(CardNo model) {
        sqlSession.insert("cardSql.updateCardNo", model);
    }

    public void deleteCardNo(CardNo model) {
        sqlSession.insert("cardSql.deleteCardNo", model);
    }


    //卡套餐管理
    public List<CardPackage> findCardPackageList() {
        return sqlSession.selectList("cardSql.findCardPackageList");
    }

    public List<CardPackage> findCardPackageList(String card_id) {
        return sqlSession.selectList("cardSql.findCardPackageListByCardId", card_id);
    }

    public List<CardPackage> findCardPackageListByCardId(String card_id) {
        return sqlSession.selectList("cardSql.findCardPackageListByCardId", card_id);
    }

    public void insertCardPackage(CardPackage model) {
        sqlSession.insert("cardSql.insertCardPackage", model);
    }

    public void updateCardPackage(CardPackage model) {
        sqlSession.insert("cardSql.updateCardPackage", model);
    }

    public void deleteCardPackage(CardPackage model) {
        sqlSession.insert("cardSql.deleteCardPackage", model);
    }


    //卡套餐 - 服务管理
    public void insertCardPackageDtl(CardPackageDtl model) {
        sqlSession.insert("cardSql.insertCardPackageDtl", model);
    }

    public void updateCardPackageDtl(CardPackageDtl model) {
        sqlSession.insert("cardSql.updateCardPackageDtl", model);
    }
}

//end file