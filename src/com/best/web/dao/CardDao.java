package com.best.web.dao;

import com.best.web.model.admin.CardType;

import java.util.List;


public interface CardDao {
    //卡类型
    public List<CardType> findCardTypeList();
    public void insertCartType(CardType model);
    public void updateCartType(CardType model);
    public void deleteCartType(CardType model);
}
