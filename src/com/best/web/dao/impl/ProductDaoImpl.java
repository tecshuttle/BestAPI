package com.best.web.dao.impl;

import com.best.web.dao.ProductDao;
import com.best.web.model.admin.ProdService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    @Resource
    private SqlSession sqlSession;

    //供应商管理
    public List<ProdService> findProdServiceList() {
        return sqlSession.selectList("productSql.findProdServiceList");
    }

    public void insertProdService(ProdService model) {
        sqlSession.insert("productSql.insertProdService", model);
    }

    public void updateProdService(ProdService model) {
        sqlSession.insert("productSql.updateProdService", model);
    }

    public void deleteProdService(ProdService model) {
        sqlSession.insert("productSql.deleteProdService", model);
    }
}

//end file