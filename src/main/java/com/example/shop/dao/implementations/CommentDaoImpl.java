package com.example.shop.dao.implementations;

import com.example.shop.dao.interfaces.CommentDao;
import com.example.shop.dao.mapper.CommentMapper;
import com.example.shop.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CommentDaoImpl implements CommentDao {
    private static final String GET_COMMENT_LIST =
            "SELECT c.*, u.id as u_id, p.id as p_id, u.username, p.title as p_title" +
            " FROM lab3_ko_comments c" +
            " JOIN lab3_ko_users u ON c.user_id = u.id" +
            " JOIN lab3_ko_products p ON c.product_id = p.id";

    private static final String GET_COMMENT_LIST_BY_PRODUCT_ID =
            GET_COMMENT_LIST + " WHERE p.id = ?";

    private static final String GET_COMMENT =
            GET_COMMENT_LIST + " WHERE c.id = ?";

    private static final String INSERT_COMMENT =
            "INSERT INTO lab3_ko_comments" +
            " VALUES (default, ?, ?, ?, ?, ?)";

    private static final String UPDATE_COMMENT =
            "UPDATE lab3_ko_comments" +
            " SET user_id = ?, title = ?," +
            " comment = ?, rating = ?, product_id = ?" +
            " WHERE id = ?";

    private static final String DELETE_COMMENT =
            "DELETE FROM lab3_ko_comments" +
            " WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Comment> getCommentList() {
        return jdbcTemplate.query(GET_COMMENT_LIST, new CommentMapper());
    }

    @Override
    public List<Comment> getCommentListByProductId(int id) {
        return jdbcTemplate.query(GET_COMMENT_LIST_BY_PRODUCT_ID, new CommentMapper(), id);
    }

    @Override
    public Comment getComment(int id) {
        return jdbcTemplate.query(GET_COMMENT, new CommentMapper(), new Object[]{id})
                .stream().findAny().orElse(null);
    }

    @Override
    public void insertComment(Comment comment) {
        jdbcTemplate.update(INSERT_COMMENT,
                comment.getUserId(), comment.getTitle(),
                comment.getComment(), comment.getRating(), comment.getProductId());
    }

    @Override
    public void updateComment(Comment comment) {
        jdbcTemplate.update(UPDATE_COMMENT,
                comment.getUserId(), comment.getTitle(),
                comment.getComment(), comment.getRating(), comment.getProductId(),
                comment.getId());
    }

    @Override
    public void deleteComment(int id) {
        jdbcTemplate.update(DELETE_COMMENT, id);
    }
}
