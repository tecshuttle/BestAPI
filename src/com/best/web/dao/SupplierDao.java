package com.best.web.dao;

import com.best.web.model.admin.Supplier;

import java.util.List;


public interface SupplierDao {
    //卡类型
    public List<Supplier> findSupplierList();

    public void insertSupplier(Supplier model);

    public void updateSupplier(Supplier model);

    public void deleteSupplier(Supplier model);
}

//end file
