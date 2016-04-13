package com.best.web.dao.impl;

import com.best.web.dao.CardDao;
import com.best.web.model.admin.CardType;
import com.best.web.model.order.CardNo;
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
        sqlSession.selectList("cardSql.insertCardNo");
    }

    public void updateCardNo(CardNo model) {
        sqlSession.selectList("cardSql.updateCardNo");
    }

    public void deleteCardNo(CardNo model) {
        sqlSession.selectList("cardSql.deleteCardNo");
    }
}

//end file