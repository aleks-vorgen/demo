/*
package com.example.demo.dao.category;

import com.example.demo.model.Category;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCategoryImpl implements DAOCategory {
    private static final Logger LOGGER = Logger.getLogger(DAOCategoryImpl.class);
    private static final String GET_CATEGORY_LIST =
            "SELECT *" +
            " FROM lab3_ko_categories";

    private static final String GET_CATEGORY =
            "SELECT *" +
            " FROM lab3_ko_categories" +
            " WHERE id = ?";

    private static final String INSERT_CATEGORY =
            "INSERT INTO lab3_ko_categories" +
            " VALUES (?, ?, ?)";

    private static final String UPDATE_CATEGORY =
            "UPDATE lab3_ko_categories" +
            " SET id = ?, parent_category_id = ?, title = ?" +
            " WHERE id = ?";

    private static final String DELETE_CATEGORY =
            "DELETE FROM lab3_ko_categories" +
                    " WHERE id = ?";
    
    @Override
    public List<Category> getCategoryList() {
        List<Category> categoryList = new ArrayList<>();
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(GET_CATEGORY_LIST)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categoryList.add(parseCategory(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("Could not get category list.\n Message: " + e.getLocalizedMessage());
        }

        return categoryList;
    }

    @Override
    public Category getCategory(int id) {
        Category category = null;
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(GET_CATEGORY)){

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                category = parseCategory(rs);
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error("Could not get category.\n Message: " + e.getLocalizedMessage());
        }

        return category;
    }

    @Override
    public boolean insertCategory(Category category) {
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(INSERT_CATEGORY)) {

            ps.setInt(1, category.getId());
            ps.setInt(2, category.getParentCategoryId());
            ps.setString(3, category.getTitle());

            ps.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Could not insert category.\n Message: " + e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean updateCategory(Category category) {
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(UPDATE_CATEGORY)) {

            ps.setInt(1, category.getId());
            ps.setInt(2, category.getParentCategoryId());
            ps.setString(3, category.getTitle());
            ps.setInt(4, category.getId());

            ps.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Could not update category.\n Message: " + e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean deleteCategory(int id) {
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(DELETE_CATEGORY)) {

            ps.setInt(1, id);

            ps.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Could not delete category.\n Message: " + e.getLocalizedMessage());
            return false;
        }
    }

    private Category parseCategory(ResultSet rs) {
        Category category = null;
        try {
            int id = rs.getInt("id");
            int parentId = rs.getInt("parent_category_id");
            String title = rs.getString("title");

            category = new Category(id, parentId, title);
        } catch (SQLException e) {
            LOGGER.error("Could not parse category.\n Message: " + e.getLocalizedMessage());
        }

        return category;
    }
}
*/
