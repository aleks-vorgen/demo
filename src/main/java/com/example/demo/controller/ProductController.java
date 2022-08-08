package com.example.demo.controller;

import com.example.demo.dao.product.DAOProduct;
import com.example.demo.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController extends AbstractController {
    private final DAOProduct dao = factory.getProductDAO();

    @RequestMapping(value = "/viewAllProducts", method = RequestMethod.GET)
    public String viewAllProducts(Model model) {
        List<Product> productList = dao.getProductList();
        model.addAttribute("productList", productList);

        return "viewAllProducts";
    }
}
