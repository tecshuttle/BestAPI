package com.best.web.service;

import com.best.util.BasicUtil;
import com.best.web.dao.CardDao;
import com.best.web.model.admin.CardType;
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
}

//end file