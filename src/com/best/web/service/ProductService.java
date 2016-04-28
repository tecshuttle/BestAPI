package com.best.web.service;

import com.best.util.BasicUtil;
import com.best.web.dao.ProductDao;
import com.best.web.model.admin.ProdService;
import com.best.web.model.admin.CardPackageDtl;

import com.best.web.model.admin.ProdServiceMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductService {
    @Resource
    private ProductDao ProductDao;

    //供应商务管理
    public List<ProdService> findProdServiceList() {
        return ProductDao.findProdServiceList();
    }

    public List<CardPackageDtl> findProdServiceListByPackageId(String package_id) {
        return ProductDao.findProdServiceListByPackageId(package_id);
    }

    public void insertProdService(ProdService model) {
        model.setId(BasicUtil.generateId());
        ProductDao.insertProdService(model);
    }

    public void updateProdService(ProdService model) {
        ProductDao.updateProdService(model);
    }

    public void deleteProdService(ProdService model) {
        ProductDao.deleteProdService(model);
    }

    public ProdService getCountServiceMap(String id) {
        return ProductDao.getCountServiceMap(id);
    }


    //产品管理 - 服务产品映射关系
    public List<ProdServiceMap> findProdServiceMapList(String xzh_service_id) {
        return ProductDao.findProdServiceMapList(xzh_service_id);
    }

    public void insertProdServiceMap(ProdServiceMap model) {
        model.setId(BasicUtil.generateId());
        ProductDao.insertProdServiceMap(model);
    }

    public void updateProdServiceMap(ProdServiceMap model) {
        ProductDao.updateProdServiceMap(model);
    }
}

//end file