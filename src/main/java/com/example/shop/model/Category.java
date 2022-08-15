package com.example.shop.model;

import java.util.Objects;

public class Category {
    int id;
    int parentCategoryId;
    String title;

    public Category() {
        super();
    }

    public Category(int id, int parentCategoryId, String title) {
        this.id = id;
        this.parentCategoryId = parentCategoryId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(int parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
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
                ", parentCategoryId=" + parentCategoryId +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id && parentCategoryId == category.parentCategoryId && Objects.equals(title, category.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentCategoryId, title);
    }
}
