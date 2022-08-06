package com.example.demo.dao.order;

import com.example.demo.dao.DAOAccess;
import com.example.demo.model.Order;

import java.util.List;

public interface DAOOrder extends DAOAccess {
    List<Order> getOrderList();
    Order getOrder(int id);
    boolean insertOrder(Order order);
    boolean deleteOrder(int id);
}
