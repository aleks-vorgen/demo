package com.example.demo.model;

import java.util.Objects;

public class Order {
    int id;
    int userId;
    int orderId;
    int productId;

    public Order() {
        super();
    }

    public Order(int id, int userId, int orderId, int productId) {
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderId=" + orderId +
                ", productId=" + productId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && userId == order.userId && orderId == order.orderId && productId == order.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, orderId, productId);
    }
}
