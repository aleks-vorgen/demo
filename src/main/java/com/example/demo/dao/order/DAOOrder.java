package com.example.demo.dao.order;

import com.example.demo.model.Order;

import java.util.List;

public interface DAOOrder {
    List<Order> getOrderList();
    Order getOrder(int id);
    boolean insertOrder(Order order);
    boolean deleteOrder(int id);
}
