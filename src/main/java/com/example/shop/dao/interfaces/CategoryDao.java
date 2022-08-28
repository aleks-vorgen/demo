package com.example.shop.dao.interfaces;

import com.example.shop.model.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getCategoryList();

    Category getCategory(int id);

    void insertCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(int id);
}
