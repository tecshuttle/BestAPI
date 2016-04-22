package com.best.web.dao.impl;

import com.best.web.dao.CompanyDao;
import com.best.web.model.admin.Company;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class CompanyDaoImpl implements CompanyDao {
    @Resource
    private SqlSession sqlSession;

    public List<Company> findCompanyList() {
        return sqlSession.selectList("companySql.findCompanyList");
    }
}

//end file
