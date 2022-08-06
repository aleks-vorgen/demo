package com.example.demo.dao.order;

import com.example.demo.dao.PostgresFactory;
import com.example.demo.model.Order;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOOrderImpl implements DAOOrder {
    private static final Logger LOGGER = Logger.getLogger(DAOOrderImpl.class);
    private static final String GET_ORDER_LIST =
            "SELECT *" +
            " FROM lab3_ko_orders";

    private static final String GET_ORDER =
            "SELECT *" +
            " FROM lab3_ko_orders" +
            " WHERE id = ?";

    private static final String INSERT_ORDER =
            "INSERT INTO lab3_ko_orders" +
            " VALUES (?, ?, ?)";

    private static final String DELETE_ORDER =
            "DELETE FROM lab3_ko_orders" +
            " WHERE id = ?";

    @Override
    public List<Order> getOrderList() {
        List<Order> orderList = new ArrayList<>();
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(GET_ORDER_LIST)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orderList.add(parseOrder(rs));
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error("Could not get order list.\n Message: " + e.getLocalizedMessage());
        }
        return orderList;
    }

    @Override
    public Order getOrder(int id) {
        Order order = null;
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(GET_ORDER)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                order = parseOrder(rs);
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error("Could not get order.\n Message: " + e.getLocalizedMessage());
        }
        return order;
    }

    @Override
    public boolean insertOrder(Order order) {
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(INSERT_ORDER)) {

            ps.setInt(1, order.getId());
            ps.setInt(2, order.getUserId());
            ps.setInt(3, order.getProductId());

            ps.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Could not insert order.\n Message: " + e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean deleteOrder(int id) {
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(DELETE_ORDER)) {

            ps.setInt(1, id);

            ps.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Could not delete order.\n Message: " + e.getLocalizedMessage());
            return false;
        }
    }

    private Order parseOrder(ResultSet rs) {
        Order order = null;
        try {
            int id = rs.getInt("id");
            int userId = rs.getInt("user_id");
            int productId = rs.getInt("product_id");
            order = new Order(id, userId, productId);
        } catch (SQLException e) {
            LOGGER.error("Could not parse order.\n Message: " + e.getLocalizedMessage());
        }

        return order;
    }
}
