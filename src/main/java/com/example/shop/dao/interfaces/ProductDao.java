package com.example.shop.dao.interfaces;

import com.example.shop.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProductList();

    List<Product> getProductListBySearch(String request);

    Product getProduct(int id);

    void insertProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(int id);
}
