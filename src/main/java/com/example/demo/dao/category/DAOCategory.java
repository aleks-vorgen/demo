package com.example.demo.dao.category;

import com.example.demo.model.Category;

import java.util.List;

public interface DAOCategory {
    List<Category> getCategoryList();
    Category getCategory(int id);
    boolean insertCategory(Category category);
    boolean updateCategory(Category category);
    boolean deleteCategory(int id);
}
