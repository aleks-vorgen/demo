package com.example.demo.dao;

import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDAO {
    private static final String GET_PRODUCT_LIST =
            "SELECT *" +
            " FROM lab3_ko_products";

    private static final String GET_PRODUCT =
            "SELECT *" +
            " FROM lab3_ko_products" +
            " WHERE id = ?";

    private static final String INSERT_PRODUCT =
            "INSERT INTO lab3_ko_products" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_PRODUCT =
            "UPDATE lab3_ko_products" +
            " SET id = ?, category_id = ?, title = ?, price = ?," +
                " amount = ?, description = ?, img_path = ?" +
            " WHERE id = ?";

    private static final String DELETE_PRODUCT =
            "DELETE FROM lab3_ko_products" +
            " WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> getProductList() {

        return jdbcTemplate.query(GET_PRODUCT_LIST,
                new BeanPropertyRowMapper<>(Product.class));
    }

    public Product getProduct(int id) {
        return jdbcTemplate.query(GET_PRODUCT,
                new BeanPropertyRowMapper<>(Product.class), new Object[]{id})
                .stream().findAny().orElse(null);
    }

    public void insertProduct(Product product) {
        jdbcTemplate.update(INSERT_PRODUCT,
                product.getId(), product.getCategoryId(), product.getTitle(),
                product.getPrice(), product.getAmount(), product.getDescription(),
                product.getImgPath());
    }

    public void updateProduct(Product product) {
        jdbcTemplate.update(UPDATE_PRODUCT,
                product.getId(), product.getCategoryId(), product.getTitle(),
                product.getPrice(), product.getAmount(), product.getDescription(),
                product.getImgPath(), product.getId());
    }

    public void deleteProduct(int id) {
        jdbcTemplate.update(DELETE_PRODUCT, id);
    }
}
