package com.best.web.dao;

import com.best.web.model.admin.Supplier;
import com.best.web.model.admin.SupplierOrg;
import com.best.web.model.admin.SupplierProduct;

import java.util.List;


public interface SupplierDao {
    //供应商管理
    public List<Supplier> findSupplierList();

    public void insertSupplier(Supplier model);

    public void updateSupplier(Supplier model);

    public void deleteSupplier(Supplier model);


    //供应商门店管理
    public List<SupplierOrg> findSupplierOrgList();

    public void insertSupplierOrg(SupplierOrg model);

    public void updateSupplierOrg(SupplierOrg model);

    public void deleteSupplierOrg(SupplierOrg model);


    //供应商产品管理
    public List<SupplierProduct> findSupplierServiceList();

    public void insertSupplierService(SupplierProduct model);

    public void updateSupplierService(SupplierProduct model);

    public void deleteSupplierService(SupplierProduct model);
}

//end file
