package com.example.demo.dao.product;

import com.example.demo.model.Product;

import java.util.List;

public interface DAOProduct {
    List<Product> getProductList();
    Product getProduct(int id);
    boolean insertProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(int id);
}
