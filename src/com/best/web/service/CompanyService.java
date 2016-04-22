package com.best.web.service;

import com.best.util.BasicUtil;
import com.best.web.dao.CompanyDao;
import com.best.web.model.admin.Company;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompanyService {
    @Resource
    private CompanyDao CompanyDao;

    public List<Company> findCompanyList() {
        return CompanyDao.findCompanyList();
    }


}

//end file
