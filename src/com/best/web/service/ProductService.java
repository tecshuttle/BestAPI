package com.best.web.service;

import com.best.util.BasicUtil;
import com.best.web.dao.ProductDao;
import com.best.web.model.admin.ProdService;

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
}

//end file