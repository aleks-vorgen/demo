package com.example.shop.controller;

import com.example.shop.dao.CommentDAO;
import com.example.shop.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductDAO productDAO;
    private final CommentDAO commentDAO;
    @Autowired
    public ProductController(ProductDAO productDAO, CommentDAO commentDAO) {
        this.productDAO = productDAO;
        this.commentDAO = commentDAO;
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
        model.addAttribute("commentList", commentDAO.getCommentListForView(id));
        System.out.println(commentDAO.getCommentListForView(id));

        return "productInfo";
    }
}
