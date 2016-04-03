package com.best.web.dao;

import com.best.web.model.admin.*;
import java.util.List;


public interface UserDao {
    //家庭成员
    public List<User> findFamilyList();

    public User findFamilyById(String id);

    public void insertFamily(User model);

    public void updateFamily(User model);

    public void deleteFamily(User model);
}
