package com.best.web.dao.impl;

import com.best.web.dao.CompanyDao;
import com.best.web.model.admin.Company;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository
public class CompanyDaoImpl implements CompanyDao {
    @Resource
    private SqlSession sqlSession;

    public List<Company> findCompanyList() {
        return sqlSession.selectList("companySql.findCompanyList");
    }

    public List<Company> findCompanyList(String company_type, String parent_id) {

        HashMap<String,String> param = new HashMap<String, String>();
        param.put("company_type", company_type);
        param.put("parent_id", parent_id);

        return sqlSession.selectList("companySql.findCompanyListByType", param);
    }
}

//end file
