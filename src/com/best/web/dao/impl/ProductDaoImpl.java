package com.best.web.dao.impl;

import com.best.web.dao.ProductDao;
import com.best.web.model.admin.ProdService;
import com.best.web.model.admin.ProdServiceMap;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    @Resource
    private SqlSession sqlSession;

    //产品管理 - 服务
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

    public ProdService getCountServiceMap(String id) {
        return sqlSession.selectOne("productSql.getCountServiceMap", id);
    }


    //产品管理 - 服务产品映射关系
    public List<ProdServiceMap> findProdServiceMapList(String xzh_service_id) {
        return sqlSession.selectList("productSql.findProdServiceMapList", xzh_service_id);
    }

    public void insertProdServiceMap(ProdServiceMap model) {
        sqlSession.insert("productSql.insertProdServiceMap", model);
    }

    public void updateProdServiceMap(ProdServiceMap model) {
        sqlSession.insert("productSql.updateProdServiceMap", model);
    }
}

//end file