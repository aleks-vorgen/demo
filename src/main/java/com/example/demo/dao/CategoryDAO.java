package com.example.demo.dao;

import com.example.demo.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDAO {
    private static final String GET_CATEGORY_LIST =
            "SELECT *" +
            " FROM lab3_ko_categories";

    private static final String GET_CATEGORY_LIST_FOR_SELECT =
            "SELECT id, title" +
            " FROM lab3_ko_categories" +
            " ORDER BY parent_category_id";

    private static final String GET_CATEGORY =
            "SELECT *" +
            " FROM lab3_ko_categories" +
            " WHERE id = ?";

    private static final String INSERT_CATEGORY =
            "INSERT INTO lab3_ko_categories" +
            " VALUES (default, ?, ?)";

    private static final String UPDATE_CATEGORY =
            "UPDATE lab3_ko_categories" +
            " SET parent_category_id = ?, title = ?" +
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
        BeanPropertyRowMapper<Category> prm = new BeanPropertyRowMapper<>(Category.class);
        prm.setPrimitivesDefaultedForNullValue(true);

        return jdbcTemplate.query(GET_CATEGORY_LIST, prm);
    }

    public List<Category> getCategoryListForSelect() {
        BeanPropertyRowMapper<Category> prm = new BeanPropertyRowMapper<>(Category.class);
        prm.setPrimitivesDefaultedForNullValue(true);

        return jdbcTemplate.query(GET_CATEGORY_LIST_FOR_SELECT, prm);
    }

    public Category getCategory(int id) {
        BeanPropertyRowMapper<Category> prm = new BeanPropertyRowMapper<>(Category.class);
        prm.setPrimitivesDefaultedForNullValue(true);

        return jdbcTemplate.query(GET_CATEGORY, prm, new Object[]{id})
                .stream().findAny().orElse(null);
    }

    public void insertCategory(Category category) {
        jdbcTemplate.update(INSERT_CATEGORY,
                category.getParentCategoryId(), category.getTitle());
    }

    public void updateCategory(Category category) {
        jdbcTemplate.update(UPDATE_CATEGORY,
                category.getParentCategoryId(), category.getTitle(), category.getId());
    }

    public void deleteCategory(int id) {
        jdbcTemplate.update(DELETE_CATEGORY, id);
    }
}
