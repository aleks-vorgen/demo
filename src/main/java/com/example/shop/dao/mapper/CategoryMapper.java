package com.example.shop.dao.mapper;

import com.example.shop.model.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setParentCategory(new Category(
                rs.getInt("c_id"),
                null,
                rs.getInt("parent_id"),
                rs.getString("c_title")
        ));
        category.setTitle(rs.getString("title"));

        return category;
    }
}
