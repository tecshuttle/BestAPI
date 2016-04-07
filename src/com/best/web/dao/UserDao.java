package com.best.web.dao;

import com.best.web.model.admin.*;
import java.util.List;


public interface UserDao {
    //家庭成员
    public List<User> findUserList();

    public User findUserById(String id);

    public void insertUser(User model);

    public void updateUser(User model);

    public void deleteUser(User model);
}
