package com.best.web.dao;

import com.best.web.model.admin.Company;

import java.util.HashMap;
import java.util.List;

public interface CompanyDao {
    public List<Company> findCompanyList();

    public List<Company> findCompanyList(String company_type, String parent_id);
}

//end file
