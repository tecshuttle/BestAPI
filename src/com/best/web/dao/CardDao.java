package com.best.web.dao;

import com.best.web.model.admin.CardType;
import com.best.web.model.order.CardNo;
import com.best.web.model.cust.CardPackage;

import java.util.List;


public interface CardDao {
    //卡类型
    public List<CardType> findCardTypeList();

    public void insertCardType(CardType model);

    public void updateCardType(CardType model);

    public void deleteCardType(CardType model);


    //卡号管理
    public List<CardNo> findCardNoList();

    public void insertCardNo(CardNo model);

    public void updateCardNo(CardNo model);

    public void deleteCardNo(CardNo model);


    //卡套餐管理
    public List<CardPackage> findCardPackageList();

    public void insertCardPackage(CardPackage model);

    public void updateCardPackage(CardPackage model);

    public void deleteCardPackage(CardPackage model);
}

//end file
