package com.example.shop.dao.mapper;

import com.example.shop.model.Category;
import com.example.shop.model.Product;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    private final Logger log = Logger.getLogger(ProductMapper.class);
    @Override
    public Product mapRow(ResultSet rs, int rowNum) {
        Product product = null;
        try {
            product = new Product();
            product.setId(rs.getInt("id"));
            product.setCategory(new Category(
                    rs.getInt("c_id"),
                    null, 0,
                    rs.getString("c_title")
            ));
            product.setTitle(rs.getString("title"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setAmount(rs.getInt("amount"));
            product.setDescription(rs.getString("description"));
            product.setImgPath(rs.getString("img_path"));
        } catch (SQLException e) {
            log.error(e.getLocalizedMessage());
        }

        return product;
    }
}
