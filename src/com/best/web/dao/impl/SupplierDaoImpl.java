package com.best.web.dao.impl;

import com.best.web.dao.SupplierDao;
import com.best.web.model.admin.Supplier;
import com.best.web.model.admin.SupplierOrg;
import com.best.web.model.admin.SupplierProduct;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository
public class SupplierDaoImpl implements SupplierDao {
    @Resource
    private SqlSession sqlSession;

    //供应商管理
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


    //供应商门店管理
    public List<SupplierOrg> findSupplierOrgList(SupplierOrg model) {
        return sqlSession.selectList("supplierSql.findSupplierOrgList", model);
    }

    public void insertSupplierOrg(SupplierOrg model) {
        sqlSession.insert("supplierSql.insertSupplierOrg", model);
    }


    public void updateSupplierOrg(SupplierOrg model) {
        sqlSession.insert("supplierSql.updateSupplierOrg", model);
    }


    public void deleteSupplierOrg(SupplierOrg model) {
        sqlSession.insert("supplierSql.deleteSupplierOrg", model);
    }


    //供应商产品管理
    public List<SupplierProduct> findSupplierServiceList(String supplier_id, String service_type) {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("supplier_id", supplier_id);
        param.put("service_type", service_type);

        return sqlSession.selectList("supplierSql.findSupplierServiceList", param);
    }

    public List<SupplierProduct> findServiceListBySupplier(String supplier_id) {
        return sqlSession.selectList("supplierSql.findServiceListBySupplier", supplier_id);
    }

    public List<SupplierProduct> findServiceListByType(String service_type) {
        return sqlSession.selectList("supplierSql.findServiceListByType", service_type);
    }

    public void insertSupplierService(SupplierProduct model) {
        sqlSession.insert("supplierSql.insertSupplierService", model);
    }


    public void updateSupplierService(SupplierProduct model) {
        sqlSession.insert("supplierSql.updateSupplierService", model);
    }


    public void deleteSupplierService(SupplierProduct model) {
        sqlSession.insert("supplierSql.deleteSupplierService", model);
    }
}

//end file