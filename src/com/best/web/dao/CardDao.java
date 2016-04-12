package com.best.web.dao;

import com.best.web.model.admin.CardType;

import java.util.List;


public interface CardDao {
    //卡类型
    public List<CardType> findCardTypeList();
    public void insertCardType(CardType model);
    public void updateCardType(CardType model);
    public void deleteCardType(CardType model);
}
