package com.example.shop.model;

import java.util.Objects;

public class Category {
    int id;
    Category parentCategory;
    String title;

    public Category() {
        super();
    }

    public Category(int id, Category parentCategory, String title) {
        this.id = id;
        this.parentCategory = parentCategory;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", parentCategory=" + parentCategory +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id && Objects.equals(parentCategory, category.parentCategory) && Objects.equals(title, category.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentCategory, title);
    }
}
