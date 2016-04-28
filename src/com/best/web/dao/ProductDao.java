package com.best.web.dao;

import com.best.web.model.admin.CardPackageDtl;
import com.best.web.model.admin.ProdService;
import com.best.web.model.admin.ProdServiceMap;

import java.util.List;

public interface ProductDao {
    //产品管理 - 服务
    public List<ProdService> findProdServiceList();

    public List<CardPackageDtl> findProdServiceListByPackageId(String package_id);

    public void insertProdService(ProdService model);

    public void updateProdService(ProdService model);

    public void deleteProdService(ProdService model);

    public ProdService getCountServiceMap(String id); //获取一个服务包含几个产品


    //产品管理 - 服务产品映射关系
    public List<ProdServiceMap> findProdServiceMapList(String xzh_service_id);

    public void insertProdServiceMap(ProdServiceMap model);

    public void updateProdServiceMap(ProdServiceMap model);
}

//end file
