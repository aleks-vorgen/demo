package com.example.demo.dao;

import com.example.demo.model.Category;
import com.example.demo.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDAO {
    private static final String GET_ORDER_LIST =
            "SELECT *" +
            " FROM lab3_ko_orders";

    private static final String GET_ORDER =
            "SELECT *" +
            " FROM lab3_ko_orders" +
            " WHERE id = ?";

    private static final String INSERT_ORDER =
            "INSERT INTO lab3_ko_orders" +
            " VALUES (default, ?, ?)";

    private static final String UPDATE_ORDER =
            "UPDATE lab3_ko_orders" +
            " SET user_id = ?, product_id = ?" +
            " WHERE id = ?";

    private static final String DELETE_ORDER =
            "DELETE FROM lab3_ko_orders" +
            " WHERE id = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Category> getOrderList() {

        return jdbcTemplate.query(GET_ORDER_LIST,
                new BeanPropertyRowMapper<>(Category.class));
    }

    public Order getOrder(int id) {
        return jdbcTemplate.query(GET_ORDER,
                        new BeanPropertyRowMapper<>(Order.class), new Object[]{id})
                .stream().findAny().orElse(null);
    }

    public void insertOrder(Order order) {
        jdbcTemplate.update(INSERT_ORDER,
                order.getUserId(), order.getProductId());
    }

    public void updateOrder(Order order) {
        jdbcTemplate.update(UPDATE_ORDER,
                order.getUserId(), order.getProductId(), order.getId());
    }

    public void deleteCategory(int id) {
        jdbcTemplate.update(DELETE_ORDER, id);
    }
}
