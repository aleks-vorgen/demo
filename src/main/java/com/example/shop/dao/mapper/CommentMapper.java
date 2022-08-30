package com.example.shop.dao.mapper;

import com.example.shop.model.Category;
import com.example.shop.model.Comment;
import com.example.shop.model.Product;
import com.example.shop.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
    Comment comment = new Comment();
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

        return comment;
    }
}
