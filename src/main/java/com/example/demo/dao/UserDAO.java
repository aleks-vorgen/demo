package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAO {
    private static final String GET_USER_LIST =
            "SELECT *" +
            " FROM lab3_ko_users";

    private static final String GET_USER =
            "SELECT *" +
            " FROM lab3_ko_users" +
            " WHERE id = ?";

    private static final String GET_USER_BY_EMAIL =
            "SELECT *" +
            " FROM lab3_ko_users" +
            " WHERE email = ?";

    private static final String INSERT_USER =
            "INSERT INTO lab3_ko_users" +
            " VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_USER =
            "UPDATE lab3_ko_users" +
            " SET id = ?, username = ?, email = ?," +
            " password = ?, permissions = ?" +
            " WHERE id = ?";

    private static final String DELETE_USER =
            "DELETE FROM lab3_ko_users" +
            " WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getUserList() {
        return jdbcTemplate.query(GET_USER_LIST,
                new BeanPropertyRowMapper<>(User.class));
    }

    public User getUser(int id) {
        return jdbcTemplate.query(GET_USER,
                        new BeanPropertyRowMapper<>(User.class), new Object[]{id})
                .stream().findAny().orElse(null);
    }

    public User getUser(String email) {
        return jdbcTemplate.query(GET_USER_BY_EMAIL,
                new BeanPropertyRowMapper<>(User.class), new Object[]{email})
                .stream().findAny().orElse(null);
    }

    public void insertUser(User user) {
        jdbcTemplate.update(INSERT_USER,
                user.getId(), user.getUsername(), user.getEmail(),
                user.getPassword(), user.isPermissions());
    }


    public void updateUser(User user) {
        jdbcTemplate.update(UPDATE_USER,
                user.getId(), user.getUsername(), user.getEmail(),
                user.getPassword(), user.isPermissions(), user.getId());
    }

    public void deleteUser(int id) {
        jdbcTemplate.update(DELETE_USER, id);
    }
}
