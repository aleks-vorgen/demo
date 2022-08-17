package com.example.shop.dao.mapper;

import com.example.shop.model.Category;
import com.example.shop.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setCategory(new Category(
                rs.getInt("c_id"),
                new Category(), 0,
                rs.getString("c_title")
        ));
        product.setTitle(rs.getString("title"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setAmount(rs.getInt("amount"));
        product.setDescription(rs.getString("description"));
        product.setImgPath(rs.getString("img_path"));

        return product;
    }
}
