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
    public List<User> findFamilyList() {
        return UserDao.findFamilyList();
    }

    public User findFamilyById(String id) {
        return UserDao.findFamilyById(id);
    }

    public void insertFamily(User model) {
        model.setId(BasicUtil.generateId());
        UserDao.insertFamily(model);
    }

    public void updateFamily(User model) {

        UserDao.updateFamily(model);
    }

    public void deleteFamily(User model) {

        UserDao.deleteFamily(model);
    }
}
