package com.example.shop.dao.interfaces;

import com.example.shop.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUserList();

    User getUser(int id);

    User getUser(String email);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(int id);
}
