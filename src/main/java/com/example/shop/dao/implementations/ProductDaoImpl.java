package com.example.shop.dao.implementations;

import com.example.shop.dao.interfaces.ProductDao;
import com.example.shop.dao.mapper.ProductMapper;
import com.example.shop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {
    private static final String GET_PRODUCT_LIST =
            "SELECT p.*, c.id as c_id, c.title as c_title" +
            " FROM lab3_ko_products p" +
            " JOIN lab3_ko_categories c ON p.category_id = c.id";

    private static final String GET_PRODUCT_LIST_BY_SEARCH =
            GET_PRODUCT_LIST + " WHERE p.title LIKE ?";

    private static final String GET_PRODUCT =
            GET_PRODUCT_LIST + " WHERE p.id = ?";

    private static final String INSERT_PRODUCT =
            "INSERT INTO lab3_ko_products" +
            " VALUES (default, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_PRODUCT =
            "UPDATE lab3_ko_products" +
            " SET category_id = ?, title = ?, price = ?," +
                " amount = ?, description = ?, img_path = ?" +
            " WHERE id = ?";

    private static final String DELETE_PRODUCT =
            "DELETE FROM lab3_ko_products" +
            " WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getProductList() {

        return jdbcTemplate.query(GET_PRODUCT_LIST,
                new ProductMapper());
    }

    @Override
    public List<Product> getProductListBySearch(String request) {
        return jdbcTemplate.query(GET_PRODUCT_LIST_BY_SEARCH,
                new ProductMapper(), "%" + request + "%");
    }

    @Override
    public Product getProduct(int id) {
        return jdbcTemplate.query(GET_PRODUCT,
                new ProductMapper(), new Object[]{id})
                .stream().findAny().orElse(null);
    }

    @Override
    public void insertProduct(Product product) {
        jdbcTemplate.update(INSERT_PRODUCT,
                product.getCategoryId(), product.getTitle(),
                product.getPrice(), product.getAmount(), product.getDescription(),
                product.getImgPath());
    }

    @Override
    public void updateProduct(Product product) {
        jdbcTemplate.update(UPDATE_PRODUCT,
                product.getCategoryId(), product.getTitle(),
                product.getPrice(), product.getAmount(), product.getDescription(),
                product.getImgPath(), product.getId());
    }

    @Override
    public void deleteProduct(int id) {
        jdbcTemplate.update(DELETE_PRODUCT, id);
    }
}
