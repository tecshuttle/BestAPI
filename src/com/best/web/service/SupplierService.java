package com.best.web.service;

import com.best.util.BasicUtil;
import com.best.web.dao.SupplierDao;
import com.best.web.model.admin.Supplier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SupplierService {
    @Resource
    private SupplierDao SupplierDao;

    //卡类型
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
}

//end file