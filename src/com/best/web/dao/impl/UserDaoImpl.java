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
    public List<User> findUserList() {
        return sqlSession.selectList("userSql.findUserList");
    }

    public User findUserById(String id) {
        return sqlSession.selectOne("userSql.findUserById", id);
    }

    public void insertUser(User model) {
        sqlSession.insert("userSql.insertUser", model);
    }

    public void updateUser(User model) {
        sqlSession.update("userSql.updateUser", model);
    }

    public void deleteUser(User model) {
        sqlSession.update("userSql.deleteUser", model);
    }
}
