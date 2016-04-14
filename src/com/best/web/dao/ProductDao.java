package com.best.web.dao;

import com.best.web.model.admin.ProdService;

import java.util.List;


public interface ProductDao {
    //供应商管理
    public List<ProdService> findProdServiceList();

    public void insertProdService(ProdService model);

    public void updateProdService(ProdService model);

    public void deleteProdService(ProdService model);
}

//end file
