package com.example.shop.controller;

import com.example.shop.dao.*;
import com.example.shop.model.*;
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

        model.addAttribute("title", "Админ панель");

        model.addAttribute("categoryList", categoryDAO.getCategoryList());
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

    /*----------Products----------*/
    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("newProduct") Product product,
                             HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        productDAO.insertProduct(new Product(product.getId(), null, product.getCategoryId(), product.getTitle(),
        product.getPrice(), product.getAmount(), product.getDescription(), product.getImgPath()));

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/viewProductEdit/{id}")
    public String viewEditProduct(@PathVariable int id, HttpServletRequest request, Model model) {
        if (isNoPermission(request))
            return "redirect:/";

        model.addAttribute("title", "Редактирование продукта");

        model.addAttribute("product", productDAO.getProduct(id));
        model.addAttribute("categoryList", categoryDAO.getCategoryList());

        return "productEdit";
    }

    @PostMapping("/editProduct")
    public String editProduct(@ModelAttribute("newProduct") Product product, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";


        productDAO.updateProduct(product);

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        productDAO.deleteProduct(id);

        return "redirect:/admin/viewAdminPanel";
    }

    /*----------Categories----------*/
    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute("newCategory") Category category,
                             HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        categoryDAO.insertCategory(new Category(category.getId(), null,
                category.getParentCategoryId(), category.getTitle()));

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/viewCategoryEdit/{id}")
    public String viewEditCategory(@PathVariable int id, HttpServletRequest request, Model model) {
        if (isNoPermission(request))
            return "redirect:/";

        model.addAttribute("title", "Редактирование категории");

        model.addAttribute("category", categoryDAO.getCategory(id));
        model.addAttribute("categoryList", categoryDAO.getCategoryList());

        return "categoryEdit";
    }

    @PostMapping("/editCategory")
    public String editCategory(@ModelAttribute("newCategory") Category category, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        categoryDAO.updateCategory(category);

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        categoryDAO.deleteCategory(id);

        return "redirect:/admin/viewAdminPanel";
    }

    /*----------Users----------*/
    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("newUser") User user,
                              HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        userDAO.insertUser(new User(user.getId(), user.getUsername(),
                user.getEmail(), user.getPassword(), user.isPermissions()));

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/viewUserEdit/{id}")
    public String viewEditUser(@PathVariable int id, HttpServletRequest request, Model model) {
        if (isNoPermission(request))
            return "redirect:/";

        model.addAttribute("title", "Редактирование пользователя");

        model.addAttribute("user", userDAO.getUser(id));

        return "userEdit";
    }

    @PostMapping("/editUser")
    public String editUser(@ModelAttribute("newUser") User user, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        userDAO.updateUser(user);

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        userDAO.deleteUser(id);

        return "redirect:/admin/viewAdminPanel";
    }

    /*----------Orders----------*/
    @PostMapping("/addOrder")
    public String addOrder(@ModelAttribute("newOrder") Order order,
                          HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        orderDAO.insertOrder(new Order(order.getId(),
                null, order.getUserId(),
                null, order.getProductId()));

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/viewOrderEdit/{id}")
    public String viewEditOrder(@PathVariable int id, HttpServletRequest request, Model model) {
        if (isNoPermission(request))
            return "redirect:/";

        model.addAttribute("title", "Редактирование заказа");

        model.addAttribute("order", orderDAO.getOrder(id));
        model.addAttribute("userList", userDAO.getUserList());
        model.addAttribute("productList", productDAO.getProductList());

        return "orderEdit";
    }

    @PostMapping("/editOrder")
    public String editOrder(@ModelAttribute("newOrder") Order order, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        orderDAO.updateOrder(new Order(order.getId(),
                null, order.getUserId(),
                null, order.getProductId()));

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable int id, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        orderDAO.deleteOrder(id);

        return "redirect:/admin/viewAdminPanel";
    }

    /*----------Comments----------*/
    @PostMapping("/addComment")
    public String addComment(@ModelAttribute("newComment") Comment comment,
                           HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        commentDAO.insertComment(new Comment(comment.getId(), null, comment.getUserId(), comment.getTitle(),
                comment.getComment(), comment.getRating(), null, comment.getProductId()));

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/viewCommentEdit/{id}")
    public String viewEditComment(@PathVariable int id, HttpServletRequest request, Model model) {
        if (isNoPermission(request))
            return "redirect:/";

        model.addAttribute("title", "Редактирование комментария");

        model.addAttribute("comment", commentDAO.getComment(id));
        model.addAttribute("userList", userDAO.getUserList());
        model.addAttribute("productList", productDAO.getProductList());

        return "commentEdit";
    }

    @PostMapping("/editComment")
    public String editComment(@ModelAttribute("newComment") Comment comment, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        commentDAO.updateComment(new Comment(comment.getId(),
                null, comment.getUserId(),
                comment.getTitle(), comment.getComment(), comment.getRating(),
                null, comment.getProductId()));

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable int id, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        commentDAO.deleteComment(id);

        return "redirect:/admin/viewAdminPanel";
    }
}
