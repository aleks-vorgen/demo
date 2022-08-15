package com.example.shop.model;

import java.util.Objects;

public class Comment {
    int id;
    User user;
    String title;
    String comment;
    short rating;
    Product product;

    public Comment() {
        super();
    }

    public Comment(int id, User user, String title, String comment, short rating, Product product) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.comment = comment;
        this.rating = rating;
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", product=" + product +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return id == comment1.id && rating == comment1.rating && Objects.equals(user, comment1.user) && Objects.equals(title, comment1.title) && Objects.equals(comment, comment1.comment) && Objects.equals(product, comment1.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, title, comment, rating, product);
    }
}
