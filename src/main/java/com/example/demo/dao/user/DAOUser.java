package com.example.demo.dao.user;

import com.example.demo.dao.DAOAccess;
import com.example.demo.model.User;

import java.util.List;

public interface DAOUser extends DAOAccess {
    List<User> getUserList();
    User getUser(int id);
    boolean insertUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int id);
}
