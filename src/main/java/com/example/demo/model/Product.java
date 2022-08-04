package com.example.demo.model;

import org.postgresql.util.PGmoney;

public class Product {
    int id;
    int categoryId;
    String title;
    PGmoney price;
    int amount;
    String description;
    String imgPath;
}
