package com.example.shop.dao.mapper;

import com.example.shop.model.Category;
import com.example.shop.model.Order;
import com.example.shop.model.Product;
import com.example.shop.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setUser(new User(
                rs.getInt("u_id"),
                rs.getString("username"),
                rs.getString("email"),
                "hidden", false
        ));
        order.setProduct(new Product(
                rs.getInt("p_id"),
                new Category(), 0,
                rs.getString("title"),
                rs.getBigDecimal("price"),
                0, "unnecessary",
                rs.getString("img_path")
        ));

        return order;
    }
}
