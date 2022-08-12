package com.example.demo.controller;

import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductDAO productDAO;
    @Autowired
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping("/viewAllProducts")
    public String viewAllProducts(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("user") == null)
            return "redirect:/users/viewLogin";
        else {
            model.addAttribute("title", "Каталог");
            model.addAttribute("productList", productDAO.getProductList());

            return "viewAllProducts";
        }
    }

    @GetMapping(value = "/{id}")
    public String viewProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("title", "Информация про товар");
        model.addAttribute("product", productDAO.getProduct(id));

        return "productInfo";
    }
}
