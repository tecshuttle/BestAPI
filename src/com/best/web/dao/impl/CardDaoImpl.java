package com.best.web.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.best.web.dao.CardDao;
import com.best.web.model.admin.CardNoGenBatch;
import com.best.web.model.admin.CardType;
import com.best.web.model.admin.CardPackageDtl;
import com.best.web.model.order.CardNo;
import com.best.web.model.cust.CardPackage;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository
public class CardDaoImpl implements CardDao {
    @Resource
    private SqlSession sqlSession;

    //卡类型
    public List<CardType> findCardTypeList(CardType model) {
        return sqlSession.selectList("cardSql.findCardTypeList", model);
    }

    public CardType findCardTypeById(String id) {
        return sqlSession.selectOne("cardSql.findCardTypeById", id);
    }

    public CardType findMaxCardSn(String card_no_prefix) {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("card_no_prefix_length", (card_no_prefix.length() + 1) + "");
        param.put("card_no_prefix", card_no_prefix);

        return sqlSession.selectOne("cardSql.findMaxCardSn", param);
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
    public List<CardNo> findCardNoList(CardNo card_no) {
        return sqlSession.selectList("cardSql.findCardNoList", card_no);
    }

    public List<CardNo> findCardNoListByBatchId(String batch_id) {
        return sqlSession.selectList("cardSql.findCardNoListByBatchId", batch_id);
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


    //卡号生成
    public List<CardNoGenBatch> findCardNoGenBatchList(CardNoGenBatch model) {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("company_id", model.getCompany_id());
        param.put("card_id", model.getCard_id());
        param.put("card_type", model.getCard_type());
        param.put("batch_no", model.getBatch_no());
        param.put("card_no", model.getCard_no());
        param.put("proposer", model.getProposer());

        return sqlSession.selectList("cardSql.findCardNoGenBatchList", param);
    }

    public void insertCardNoGenBatch(CardNoGenBatch model) {
        sqlSession.insert("cardSql.insertCardNoGenBatch", model);
    }

    public void updateCardNoGenBatch(CardNoGenBatch model) {
        sqlSession.insert("cardSql.updateCardNoGenBatch", model);
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