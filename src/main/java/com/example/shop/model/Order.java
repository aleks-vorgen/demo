package com.example.shop.model;

import java.util.Objects;

public class Order {
    int id;
    User user;
    Product product;

    public Order() {
        super();
    }

    public Order(int id, User user, Product product) {
        this.id = id;
        this.user = user;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", product=" + product +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(user, order.user) && Objects.equals(product, order.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, product);
    }
}
