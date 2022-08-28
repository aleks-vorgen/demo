package com.example.shop.dao.interfaces;

import com.example.shop.model.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> getCommentList();

    List<Comment> getCommentListByProductId(int id);

    Comment getComment(int id);

    void insertComment(Comment comment);

    void updateComment(Comment comment);

    void deleteComment(int id);
}
