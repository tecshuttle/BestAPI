package com.best.web.service;

import com.best.util.BasicUtil;
import com.best.web.dao.CardDao;
import com.best.web.model.admin.CardNoGenBatch;
import com.best.web.model.admin.CardType;
import com.best.web.model.admin.CardPackageDtl;
import com.best.web.model.cust.CardPackage;
import com.best.web.model.order.CardNo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CardService {
    @Resource
    private CardDao CardDao;

    //卡类型
    public List<CardType> findCardTypeList(CardType model) {
        return CardDao.findCardTypeList(model);
    }

    public CardType findCardTypeById(String id) {
        return CardDao.findCardTypeById(id);
    }

    public CardType findMaxCardSn(String card_no_prefix) {
        return CardDao.findMaxCardSn(card_no_prefix);
    }

    public void insertCardType(CardType model) {
        model.setId(BasicUtil.generateId());
        CardDao.insertCardType(model);
    }

    public void updateCardType(CardType model) {
        CardDao.updateCardType(model);
    }

    public void deleteCardType(CardType model) {
        CardDao.deleteCardType(model);
    }

    //卡号管理
    public List<CardNo> findCardNoList(CardNo card_no) {
        return CardDao.findCardNoList(card_no);
    }

    public List<CardNo> findCardNoListByBatchId(String batch_id) {
        return CardDao.findCardNoListByBatchId(batch_id);
    }

    public void insertCardNo(CardNo model) {
        model.setId(BasicUtil.generateId());
        CardDao.insertCardNo(model);
    }

    public void updateCardNo(CardNo model) {
        CardDao.updateCardNo(model);
    }

    public void deleteCardNo(CardNo model) {
        CardDao.deleteCardNo(model);
    }

    //卡号生成
    public List<CardNoGenBatch> findCardNoGenBatchList(CardNoGenBatch model) {
        return CardDao.findCardNoGenBatchList(model);
    }

    public void insertCardNoGenBatch(CardNoGenBatch model) {
        model.setId(BasicUtil.generateId());
        CardDao.insertCardNoGenBatch(model);
    }

    public void updateCardNoGenBatch(CardNoGenBatch model) {
        CardDao.updateCardNoGenBatch(model);
    }


    //卡套餐管理
    public List<CardPackage> findCardPackageList() {
        return CardDao.findCardPackageList();
    }

    public List<CardPackage> findCardPackageList(String card_id) {
        return CardDao.findCardPackageList(card_id);
    }

    public List<CardPackage> findCardPackageListByCardId(String card_id) {
        return CardDao.findCardPackageListByCardId(card_id);
    }

    public void insertCardPackage(CardPackage model) {
        model.setId(BasicUtil.generateId());
        CardDao.insertCardPackage(model);
    }

    public void updateCardPackage(CardPackage model) {
        CardDao.updateCardPackage(model);
    }

    public void deleteCardPackage(CardPackage model) {
        CardDao.deleteCardPackage(model);
    }


    //卡套餐 - 服务管理
    public void insertCardPackageDtl(CardPackageDtl model) {
        model.setId(BasicUtil.generateId());
        CardDao.insertCardPackageDtl(model);
    }

    public void updateCardPackageDtl(CardPackageDtl model) {
        CardDao.updateCardPackageDtl(model);
    }
}

//end file