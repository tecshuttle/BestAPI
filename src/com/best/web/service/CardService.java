package com.best.web.service;

import com.best.util.BasicUtil;
import com.best.web.dao.CardDao;
import com.best.web.model.admin.CardType;
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
    public List<CardType> findCardTypeList() {
        return CardDao.findCardTypeList();
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
    public List<CardNo> findCardNoList() {
        return CardDao.findCardNoList();
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


    //卡套餐管理
    public List<CardPackage> findCardPackageList() {
        return CardDao.findCardPackageList();
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
}

//end file