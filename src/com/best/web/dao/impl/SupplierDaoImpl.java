package com.best.web.dao.impl;

import com.best.web.dao.SupplierDao;
import com.best.web.model.admin.Supplier;
import com.best.web.model.admin.Supplier;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class SupplierDaoImpl implements SupplierDao {
    @Resource
    private SqlSession sqlSession;

    //卡类型
    public List<Supplier> findSupplierList() {
        return sqlSession.selectList("supplierSql.findSupplierList");
    }


    public void insertSupplier(Supplier model) {
        sqlSession.insert("supplierSql.insertSupplier", model);
    }


    public void updateSupplier(Supplier model) {
        sqlSession.insert("supplierSql.updateSupplier", model);
    }


    public void deleteSupplier(Supplier model) {
        sqlSession.insert("supplierSql.deleteSupplier", model);
    }
}

//end file