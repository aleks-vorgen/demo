package com.example.demo.controller;

import com.example.demo.dao.*;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CategoryDAO categoryDAO;
    private final CommentDAO commentDAO;
    private final OrderDAO orderDAO;
    private final ProductDAO productDAO;
    private final UserDAO userDAO;

    public AdminController(CategoryDAO categoryDAO, CommentDAO commentDAO, OrderDAO orderDAO, ProductDAO productDAO, UserDAO userDAO) {
        this.categoryDAO = categoryDAO;
        this.commentDAO = commentDAO;
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
        this.userDAO = userDAO;
    }

    private boolean isNoPermission(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user == null || !user.isPermissions();
    }

    @GetMapping("/viewAdminPanel")
    public String viewAdminPanel(HttpServletRequest request, Model model) {
        if (isNoPermission(request))
            return "redirect:/";

        model.addAttribute("title", "Список продуктов");

        model.addAttribute("categoryList", categoryDAO.getCategoryList());
        model.addAttribute("categoryListForSelect", categoryDAO.getCategoryListForSelect());
        model.addAttribute("newCategory", new Category());

        model.addAttribute("commentList", commentDAO.getCommentList());
        model.addAttribute("newComment", new Comment());

        model.addAttribute("orderList", orderDAO.getOrderList());
        model.addAttribute("newOrder", new Order());


        model.addAttribute("productList", productDAO.getProductList());
        model.addAttribute("newProduct", new Product());

        model.addAttribute("userList", userDAO.getUserList());
        model.addAttribute("newUser", new User());

        return "AdminPanel";
    }


    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("newProduct") Product product, HttpServletRequest request, Model model) {
        if (isNoPermission(request))
            return "redirect:/";

        productDAO.insertProduct(product);

        model.addAttribute("successAdd", productDAO.getProduct(product.getId()));
        return "redirect:/admin/viewAdminPanel";
    }

    @PostMapping("/editProduct")
    public String editProduct(@ModelAttribute("newProduct") Product product, HttpServletRequest request, Model model) {
        if (isNoPermission(request))
            return "redirect:/";

        productDAO.updateProduct(product);

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id, HttpServletRequest request, Model model) {
        if (isNoPermission(request))
            return "redirect:/";

        productDAO.deleteProduct(id);

        model.addAttribute("successDelete", true);
        return "redirect:/admin/viewAdminPanel";
    }
}
