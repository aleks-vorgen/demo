package com.example.demo.dao.product;

import com.example.demo.dao.PostgresFactory;
import com.example.demo.model.Product;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOProductImpl implements DAOProduct {
    private static final Logger LOGGER = Logger.getLogger(DAOProductImpl.class);
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

    @Override
    public List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(GET_PRODUCT_LIST)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                productList.add(parseProduct(rs));
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error("Could not get product list.\n Message: " + e.getLocalizedMessage());
        }

        return productList;
    }

    @Override
    public Product getProduct(int id) {
        Product product = null;
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(GET_PRODUCT)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product = parseProduct(rs);
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error("Could not get product.\n Message: " + e.getLocalizedMessage());
        }

        return product;
    }

    @Override
    public boolean insertProduct(Product product) {
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(INSERT_PRODUCT)) {

            setPS(ps, product);

            ps.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Could not insert product.\n Message: " + e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(UPDATE_PRODUCT)) {

            setPS(ps, product);
            ps.setInt(8, product.getId());

            ps.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Could not update product.\n Message: " + e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean deleteProduct(int id) {
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(DELETE_PRODUCT)) {
            ps.setInt(1, id);

            ps.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Could not delete product.\n Message: " + e.getLocalizedMessage());
            return false;
        }
    }

    private void setPS(PreparedStatement ps, Product product) throws SQLException {
        ps.setInt(1, product.getId());
        ps.setInt(2, product.getCategoryId());
        ps.setString(3, product.getTitle());
        ps.setBigDecimal(4, product.getPrice());
        ps.setInt(5, product.getAmount());
        ps.setString(6, product.getDescription());
        ps.setString(7, product.getImgPath());
    }

    private Product parseProduct(ResultSet rs) {
        Product product = null;
        try {
            int id = rs.getInt("id");
            int categoryId = rs.getInt("category_id");
            String title = rs.getString("title");
            BigDecimal price = rs.getBigDecimal("price");
            int amount = rs.getInt("amount");
            String description = rs.getString("description");
            String imgPath = rs.getString("img_path");
            product = new Product(id, categoryId, title, price, amount, description, imgPath);
        } catch (SQLException e) {
            LOGGER.error("Could not parse product.\n Message: " + e.getLocalizedMessage());
        }

        return product;
    }
}
