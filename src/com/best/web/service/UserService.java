package com.best.web.service;

import com.best.util.BasicUtil;
import com.best.web.dao.UserDao;
import com.best.web.model.admin.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserDao UserDao;

    //家庭成员
    public List<User> findUserList() {
        return UserDao.findUserList();
    }

    public User findUserById(String id) {
        return UserDao.findUserById(id);
    }

    public void insertUser(User model) {
        model.setId(BasicUtil.generateId());
        UserDao.insertUser(model);
    }

    public void updateUser(User model) {

        UserDao.updateUser(model);
    }

    public void deleteUser(User model) {

        UserDao.deleteUser(model);
    }
}
