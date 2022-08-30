package com.example.shop.dao.mapper;

import com.example.shop.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        try {
            user.setPassword(rs.getString("password"));
        } catch (SQLException e) {
            user.setPassword("hidden");
        }
        user.setPermissions(rs.getString("permissions"));
        user.setActive(rs.getBoolean("active"));

        return user;
    }
}
