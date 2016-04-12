package com.best.web.dao.impl;

import com.best.web.dao.CardDao;
import com.best.web.model.admin.*;
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


    public void insertCardType(CardType model) {
        sqlSession.insert("cardSql.insertCardType", model);
    }


    public void updateCardType(CardType model) {
        sqlSession.insert("cardSql.updateCardType", model);
    }


    public void deleteCardType(CardType model) {
        sqlSession.insert("cardSql.deleteCardType", model);
    }
}

//end file