package com.example.shop.dao;

import com.example.shop.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentDAO {
    private static final String GET_COMMENT_LIST =
            "SELECT *" +
            " FROM lab3_ko_comments";

    private static final String GET_COMMENT_LIST_FOR_VIEW =
            "SELECT c.id as id, c.title as title, c.comment as comment, c.rating as rating, u.username as username" +
            " FROM lab3_ko_comments c" +
            " LEFT JOIN lab3_ko_users u ON c.user_id = u.id" +
            " WHERE product_id = ?";

    private static final String GET_COMMENT =
            "SELECT *" +
            " FROM lab3_ko_comments" +
            " WHERE id = ?";

    private static final String INSERT_COMMENT =
            "INSERT INTO lab3_ko_comments" +
            " VALUES (default, ?, ?, ?, ?)";

    private static final String UPDATE_COMMENT =
            "UPDATE lab3_ko_comments" +
            " SET user_id = ?, title = ?," +
            " comment = ?, rating = ?" +
            " WHERE id = ?";

    private static final String DELETE_COMMENT =
            "DELETE FROM lab3_ko_comments" +
            " WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Comment> getCommentList() {
        BeanPropertyRowMapper<Comment> prm = new BeanPropertyRowMapper<>(Comment.class);
        prm.setPrimitivesDefaultedForNullValue(true);

        return jdbcTemplate.query(GET_COMMENT_LIST, prm);
    }

    public List<Comment> getCommentListForView(int id) {
        BeanPropertyRowMapper<Comment> prm = new BeanPropertyRowMapper<>(Comment.class);
        prm.setPrimitivesDefaultedForNullValue(true);
        return jdbcTemplate.query(GET_COMMENT_LIST_FOR_VIEW, prm, id);
    }

    public Comment getComment(int id) {
        BeanPropertyRowMapper<Comment> prm = new BeanPropertyRowMapper<>(Comment.class);
        prm.setPrimitivesDefaultedForNullValue(true);

        return jdbcTemplate.query(GET_COMMENT, prm, new Object[]{id})
                .stream().findAny().orElse(null);
    }

    public void insertComment(Comment comment) {
        jdbcTemplate.update(INSERT_COMMENT,
                comment.getUserId(), comment.getTitle(),
                comment.getComment(), comment.getRating());
    }

    public void updateComment(Comment comment) {
        jdbcTemplate.update(UPDATE_COMMENT,
                comment.getUserId(), comment.getTitle(),
                comment.getComment(), comment.getRating(), comment.getId());
    }

    public void deleteComment(int id) {
        jdbcTemplate.update(DELETE_COMMENT, id);
    }
}
