package com.example.demo.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    int id;
    int categoryId;
    String title;
    BigDecimal price;
    int amount;
    String description;
    String imgPath;

    public Product() {
        super();
    }

    public Product(int id, int categoryId, String title, BigDecimal price, int amount, String description, String imgPath) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.price = price;
        this.amount = amount;
        this.description = description;
        this.imgPath = imgPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && categoryId == product.categoryId && amount == product.amount && Objects.equals(title, product.title) && Objects.equals(price, product.price) && Objects.equals(description, product.description) && Objects.equals(imgPath, product.imgPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryId, title, price, amount, description, imgPath);
    }
}
