package com.example.shop.dao.implementations;

import com.example.shop.dao.interfaces.OrderDao;
import com.example.shop.dao.mapper.OrderMapper;
import com.example.shop.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDaoImpl implements OrderDao {
    private static final String GET_ORDER_LIST =
            "SELECT o.*, u.id as u_id, u.username, u.email, p.id as p_id, p.title, p.price, p.img_path" +
            " FROM lab3_ko_orders o" +
            " JOIN lab3_ko_users u ON o.user_id = u.id" +
            " JOIN lab3_ko_products p ON o.product_id = p.id";

    private static final String GET_ORDER_LIST_BY_USERNAME =
            GET_ORDER_LIST + " WHERE u.username = ?";

    private static final String GET_ORDER =
            GET_ORDER_LIST + " WHERE o.id = ?";

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
    public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Order> getOrderList() {

        return jdbcTemplate.query(GET_ORDER_LIST,
                new OrderMapper());
    }

    @Override
    public List<Order> getOrderList(String username) {
        return jdbcTemplate.query(GET_ORDER_LIST_BY_USERNAME,
                new OrderMapper(), username);
    }

    @Override
    public Order getOrder(int id) {
        return jdbcTemplate.query(GET_ORDER,
                        new BeanPropertyRowMapper<>(Order.class), new Object[]{id})
                .stream().findAny().orElse(null);
    }

    @Override
    public void insertOrder(Order order) {
        jdbcTemplate.update(INSERT_ORDER,
                order.getUserId(), order.getProductId());
    }

    @Override
    public void updateOrder(Order order) {
        jdbcTemplate.update(UPDATE_ORDER,
                order.getUserId(), order.getProductId(), order.getId());
    }

    @Override
    public void deleteOrder(int id) {
        jdbcTemplate.update(DELETE_ORDER, id);
    }
}
