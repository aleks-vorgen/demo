package com.example.shop.dao.mapper;

import com.example.shop.model.Category;
import com.example.shop.model.Comment;
import com.example.shop.model.Product;
import com.example.shop.model.User;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<Comment> {

    private final Logger log = Logger.getLogger(CommentMapper.class);
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) {
        Comment comment = null;
        try {
            comment = new Comment();
            comment.setId(rs.getInt("id"));
            comment.setUser(new User(
                    rs.getInt("u_id"),
                    rs.getString("username"),
                    "hidden",
                    "hidden",
                    "hidden"
            ));
            comment.setTitle(rs.getString("title"));
            comment.setComment(rs.getString("comment"));
            comment.setRating(rs.getShort("rating"));
            comment.setProduct(new Product(
                    rs.getInt("p_id"),
                    new Category(), 0, rs.getString("p_title"),
                    BigDecimal.ZERO, 0, "unnecessary", "unnecessary"
            ));
        } catch (SQLException e) {
            log.error(e.getLocalizedMessage());
        }

        return comment;
    }
}
