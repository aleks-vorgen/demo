package com.example.shop.dao.implementations;

import com.example.shop.dao.interfaces.UserDao;
import com.example.shop.dao.mapper.UserMapper;
import com.example.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    private static final String GET_USER_LIST =
            "SELECT id, username, email, permissions" +
            " FROM lab3_ko_users";

    private static final String GET_USER =
            "SELECT * FROM lab3_ko_users WHERE id = ?";

    private static final String GET_USER_BY_EMAIL =
            "SELECT * FROM lab3_ko_users WHERE email = ?";

    private static final String INSERT_USER =
            "INSERT INTO lab3_ko_users" +
            " VALUES (default, ?, ?, ?, ?)";

    private static final String UPDATE_USER =
            "UPDATE lab3_ko_users" +
            " SET username = ?, email = ?," +
            " password = ?, permissions = ?" +
            " WHERE id = ?";

    private static final String DELETE_USER =
            "DELETE FROM lab3_ko_users" +
            " WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getUserList() {
        return jdbcTemplate.query(GET_USER_LIST, new UserMapper());
    }

    @Override
    public User getUser(int id) {
        return jdbcTemplate.query(GET_USER,
                        new UserMapper(), new Object[]{id})
                .stream().findAny().orElse(null);
    }

    @Override
    public User getUser(String email) {
        return jdbcTemplate.query(GET_USER_BY_EMAIL,
                new UserMapper(), new Object[]{email})
                .stream().findAny().orElse(null);
    }

    @Override
    public void insertUser(User user) {
        jdbcTemplate.update(INSERT_USER,
                user.getUsername(), user.getEmail(),
                user.getPassword(), user.isPermissions());
    }


    @Override
    public void updateUser(User user) {
        jdbcTemplate.update(UPDATE_USER,
                user.getUsername(), user.getEmail(),
                user.getPassword(), user.isPermissions(), user.getId());
    }

    @Override
    public void deleteUser(int id) {
        jdbcTemplate.update(DELETE_USER, id);
    }
}
