package com.best.web.dao.impl;

import com.best.web.dao.UserDao;
import com.best.web.model.admin.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Resource
    private SqlSession sqlSession;

    //家庭成员
    public List<User> findFamilyList() {
        return sqlSession.selectList("userSql.findFamilyList");
    }

    public User findFamilyById(String id) {
        return sqlSession.selectOne("userSql.findFamilyById", id);
    }

    public void insertFamily(User model) {
        sqlSession.insert("userSql.insertFamily", model);
    }

    public void updateFamily(User model) {
        sqlSession.update("userSql.updateFamily", model);
    }

    public void deleteFamily(User model) {
        sqlSession.update("userSql.deleteFamily", model);
    }
}
