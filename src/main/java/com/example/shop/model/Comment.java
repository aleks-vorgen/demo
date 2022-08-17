package com.example.shop.model;

import java.util.Objects;

public class Comment {
    int id;
    User user;
    int userId;
    String title;
    String comment;
    short rating;
    Product product;
    int productId;

    public Comment() {
        super();
    }

    public Comment(int id, User user, int userId, String title, String comment, short rating, Product product, int productId) {
        this.id = id;
        this.user = user;
        this.userId = userId;
        this.title = title;
        this.comment = comment;
        this.rating = rating;
        this.product = product;
        this.productId = productId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", product=" + product +
                ", productId=" + productId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return id == comment1.id && userId == comment1.userId && rating == comment1.rating && productId == comment1.productId && Objects.equals(user, comment1.user) && Objects.equals(title, comment1.title) && Objects.equals(comment, comment1.comment) && Objects.equals(product, comment1.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, userId, title, comment, rating, product, productId);
    }
}
