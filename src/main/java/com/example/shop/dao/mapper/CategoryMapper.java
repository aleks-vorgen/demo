package com.example.shop.dao.mapper;

import com.example.shop.model.Category;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {
    private final Logger log = Logger.getLogger(CategoryMapper.class);
    @Override
    public Category mapRow(ResultSet rs, int rowNum) {
        Category category = null;
        try {
            category = new Category();
            category.setId(rs.getInt("id"));
            category.setParentCategory(new Category(
                    rs.getInt("c_id"),
                    null,
                    rs.getInt("parent_id"),
                    rs.getString("c_title")
            ));
            category.setTitle(rs.getString("title"));
        } catch (SQLException e) {
            log.error(e.getLocalizedMessage());
        }
        return category;
    }
}
