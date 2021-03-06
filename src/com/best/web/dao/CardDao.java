package com.best.web.dao;

import com.best.web.model.admin.CardNoGenBatch;
import com.best.web.model.admin.CardType;
import com.best.web.model.admin.CardPackageDtl;
import com.best.web.model.order.CardNo;
import com.best.web.model.cust.CardPackage;

import java.util.List;


public interface CardDao {
    //卡类型
    public List<CardType> findCardTypeList(CardType model);

    public CardType findCardTypeById(String id);

    public CardType findMaxCardSn(String card_no_prefix);

    public void insertCardType(CardType model);

    public void updateCardType(CardType model);

    public void deleteCardType(CardType model);


    //卡号管理
    public List<CardNo> findCardNoList(CardNo card_no);

    public List<CardNo> findCardNoListByBatchId(String batch_id);

    public void insertCardNo(CardNo model);

    public void updateCardNo(CardNo model);

    public void deleteCardNo(CardNo model);

    //卡号生成
    public List<CardNoGenBatch> findCardNoGenBatchList(CardNoGenBatch model);

    public void insertCardNoGenBatch(CardNoGenBatch model);

    public void updateCardNoGenBatch(CardNoGenBatch model);


    //卡套餐管理
    public List<CardPackage> findCardPackageList();

    public List<CardPackage> findCardPackageList(String card_id);

    public List<CardPackage> findCardPackageListByCardId(String card_id);

    public void insertCardPackage(CardPackage model);

    public void updateCardPackage(CardPackage model);

    public void deleteCardPackage(CardPackage model);


    //卡套餐 - 服务管理
    public void insertCardPackageDtl(CardPackageDtl model);

    public void updateCardPackageDtl(CardPackageDtl model);
}

//end file
