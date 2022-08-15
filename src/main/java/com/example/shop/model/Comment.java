package com.example.shop.model;

import java.util.Objects;

public class Comment {
    int id;
    int userId;
    String title;
    String comment;
    short rating;

    int productId;

    public Comment() {
        super();
    }

    public Comment(int id, int userId, String title, String comment, short rating, int productId) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.comment = comment;
        this.rating = rating;
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
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", productId=" + productId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return id == comment1.id && userId == comment1.userId && rating == comment1.rating && productId == comment1.productId && Objects.equals(title, comment1.title) && Objects.equals(comment, comment1.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title, comment, rating, productId);
    }
}
