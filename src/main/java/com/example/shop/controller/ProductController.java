package com.example.shop.controller;

import com.example.shop.dao.interfaces.CommentDao;
import com.example.shop.dao.interfaces.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductDao productDao;
    private final CommentDao commentDao;
    @Autowired
    public ProductController(ProductDao productDao, CommentDao commentDao) {
        this.productDao = productDao;
        this.commentDao = commentDao;
    }

    @GetMapping("/viewAllProducts")
    public String viewAllProducts(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("user") == null)
            return "redirect:/users/viewLogin";
        else {
            model.addAttribute("title", "Каталог");
            model.addAttribute("productList", productDao.getProductList());

            return "viewAllProducts";
        }
    }

    @GetMapping(value = "/{id}")
    public String viewProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("title", "Информация про товар");
        model.addAttribute("product", productDao.getProduct(id));
        model.addAttribute("commentList", commentDao.getCommentListByProductId(id));

        return "productInfo";
    }

    @GetMapping(value = "/searchProduct")
    public String searchProduct(@RequestParam("input") String input, HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("user") == null)
            return "redirect:/users/viewLogin";

        model.addAttribute("productList", productDao.getProductListBySearch(input));

        return "viewAllProducts";
    }
}
