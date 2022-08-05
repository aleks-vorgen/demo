package com.example.demo.model;

import java.util.Objects;

public class Comment {
    int id;
    int userId;
    String title;
    String comment;
    short rating;

    public Comment() {
        super();
    }

    public Comment(int id, int userId, String title, String comment, short rating) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.comment = comment;
        this.rating = rating;
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return id == comment1.id && userId == comment1.userId && rating == comment1.rating && Objects.equals(title, comment1.title) && Objects.equals(comment, comment1.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title, comment, rating);
    }
}
