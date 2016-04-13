package com.best.web.service;

import com.best.util.BasicUtil;
import com.best.web.dao.SupplierDao;
import com.best.web.model.admin.Supplier;
import com.best.web.model.admin.SupplierOrg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SupplierService {
    @Resource
    private SupplierDao SupplierDao;

    //供应商务管理
    public List<Supplier> findSupplierList() {
        return SupplierDao.findSupplierList();
    }

    public void insertSupplier(Supplier model) {
        model.setId(BasicUtil.generateId());
        SupplierDao.insertSupplier(model);
    }

    public void updateSupplier(Supplier model) {
        SupplierDao.updateSupplier(model);
    }

    public void deleteSupplier(Supplier model) {
        SupplierDao.deleteSupplier(model);
    }


    //供应商门店管理
    public List<SupplierOrg> findSupplierOrgList() {
        return SupplierDao.findSupplierOrgList();
    }

    public void insertSupplierOrg(SupplierOrg model) {
        model.setId(BasicUtil.generateId());
        SupplierDao.insertSupplierOrg(model);
    }

    public void updateSupplierOrg(SupplierOrg model) {
        SupplierDao.updateSupplierOrg(model);
    }

    public void deleteSupplierOrg(SupplierOrg model) {
        SupplierDao.deleteSupplierOrg(model);
    }
}

//end file