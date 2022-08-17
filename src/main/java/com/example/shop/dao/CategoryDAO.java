package com.example.shop.dao;

import com.example.shop.dao.mapper.CategoryMapper;
import com.example.shop.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDAO {

    private static final String GET_CATEGORY_LIST =
            "SELECT parent.*, child.id as c_id, child.title as c_title" +
            " FROM lab3_ko_categories parent" +
            " LEFT OUTER JOIN lab3_ko_categories child ON parent.parent_id = child.id";

    private static final String GET_CATEGORY =
            GET_CATEGORY_LIST + " WHERE parent.id = ?";

    private static final String INSERT_CATEGORY =
            "INSERT INTO lab3_ko_categories" +
            " VALUES (default, ?, ?)";

    private static final String UPDATE_CATEGORY =
            "UPDATE lab3_ko_categories" +
            " SET parent_id = ?, title = ?" +
            " WHERE id = ?";

    private static final String DELETE_CATEGORY =
            "DELETE FROM lab3_ko_categories" +
                    " WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Category> getCategoryList() {

        return jdbcTemplate.query(GET_CATEGORY_LIST, new CategoryMapper());
    }

    public Category getCategory(int id) {

        return jdbcTemplate.query(GET_CATEGORY, new CategoryMapper(), new Object[]{id})
                .stream().findAny().orElse(null);
    }

    public void insertCategory(Category category) {
        jdbcTemplate.update(INSERT_CATEGORY,
                category.getParentCategoryId() == 0 ? null : category.getParentCategoryId(),
                category.getTitle());
    }

    public void updateCategory(Category category) {
        jdbcTemplate.update(UPDATE_CATEGORY,
                category.getParentCategoryId() == 0 ? null : category.getParentCategoryId(),
                category.getTitle(), category.getId());
    }

    public void deleteCategory(int id) {
        jdbcTemplate.update(DELETE_CATEGORY, id);
    }
}
