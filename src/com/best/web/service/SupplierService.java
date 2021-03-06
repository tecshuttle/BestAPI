package com.best.web.service;

import com.best.util.BasicUtil;
import com.best.web.dao.SupplierDao;
import com.best.web.model.admin.Supplier;
import com.best.web.model.admin.SupplierOrg;
import com.best.web.model.admin.SupplierProduct;
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
    public List<SupplierOrg> findSupplierOrgList(SupplierOrg model) {
        return SupplierDao.findSupplierOrgList(model);
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


    //供应商产品管理
    public List<SupplierProduct> findSupplierServiceList(String supplier_id, String service_type) {
        return SupplierDao.findSupplierServiceList(supplier_id, service_type);
    }

    public List<SupplierProduct> findServiceListBySupplier(String supplier_id) {
        return SupplierDao.findServiceListBySupplier(supplier_id);
    }

    public List<SupplierProduct> findServiceListByType(String service_type) {
        return SupplierDao.findServiceListByType(service_type);
    }

    public void insertSupplierService(SupplierProduct model) {
        model.setId(BasicUtil.generateId());
        SupplierDao.insertSupplierService(model);
    }

    public void updateSupplierService(SupplierProduct model) {
        SupplierDao.updateSupplierService(model);
    }

    public void deleteSupplierService(SupplierProduct model) {
        SupplierDao.deleteSupplierService(model);
    }
}

//end file