package com.example.shop.controller;

import com.example.shop.dao.interfaces.*;
import com.example.shop.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final CategoryDao categoryDao;
    private final CommentDao commentDao;
    private final OrderDao orderDao;
    private final ProductDao productDao;
    private final UserDao userDao;

    public AdminController(CategoryDao categoryDao, CommentDao commentDao, OrderDao orderDao, ProductDao productDao, UserDao userDao) {
        this.categoryDao = categoryDao;
        this.commentDao = commentDao;
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.userDao = userDao;
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

        model.addAttribute("categoryList", categoryDao.getCategoryList());
        model.addAttribute("newCategory", new Category());

        model.addAttribute("commentList", commentDao.getCommentList());
        model.addAttribute("newComment", new Comment());

        model.addAttribute("orderList", orderDao.getOrderList());
        model.addAttribute("newOrder", new Order());

        model.addAttribute("productList", productDao.getProductList());
        model.addAttribute("newProduct", new Product());

        model.addAttribute("userList", userDao.getUserList());
        model.addAttribute("newUser", new User());

        return "AdminPanel";
    }

    /*----------Products----------*/
    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("newProduct") Product product,
                             HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        productDao.insertProduct(new Product(product.getId(), null, product.getCategoryId(), product.getTitle(),
        product.getPrice(), product.getAmount(), product.getDescription(), product.getImgPath()));

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/viewProductEdit/{id}")
    public String viewEditProduct(@PathVariable int id, HttpServletRequest request, Model model) {
        if (isNoPermission(request))
            return "redirect:/";

        model.addAttribute("title", "Редактирование продукта");

        model.addAttribute("product", productDao.getProduct(id));
        model.addAttribute("categoryList", categoryDao.getCategoryList());

        return "productEdit";
    }

    @PostMapping("/editProduct")
    public String editProduct(@ModelAttribute("newProduct") Product product, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";


        productDao.updateProduct(product);

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        productDao.deleteProduct(id);

        return "redirect:/admin/viewAdminPanel";
    }

    /*----------Categories----------*/
    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute("newCategory") Category category,
                             HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        categoryDao.insertCategory(new Category(category.getId(), null,
                category.getParentCategoryId(), category.getTitle()));

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/viewCategoryEdit/{id}")
    public String viewEditCategory(@PathVariable int id, HttpServletRequest request, Model model) {
        if (isNoPermission(request))
            return "redirect:/";

        model.addAttribute("title", "Редактирование категории");

        model.addAttribute("category", categoryDao.getCategory(id));
        model.addAttribute("categoryList", categoryDao.getCategoryList());

        return "categoryEdit";
    }

    @PostMapping("/editCategory")
    public String editCategory(@ModelAttribute("newCategory") Category category, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        categoryDao.updateCategory(category);

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        categoryDao.deleteCategory(id);

        return "redirect:/admin/viewAdminPanel";
    }

    /*----------Users----------*/
    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("newUser") User user,
                              HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        userDao.insertUser(new User(user.getId(), user.getUsername(),
                user.getEmail(), user.getPassword(), user.isPermissions()));

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/viewUserEdit/{id}")
    public String viewEditUser(@PathVariable int id, HttpServletRequest request, Model model) {
        if (isNoPermission(request))
            return "redirect:/";

        model.addAttribute("title", "Редактирование пользователя");

        model.addAttribute("user", userDao.getUser(id));

        return "userEdit";
    }

    @PostMapping("/editUser")
    public String editUser(@ModelAttribute("newUser") User user, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        userDao.updateUser(user);

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        userDao.deleteUser(id);

        return "redirect:/admin/viewAdminPanel";
    }

    /*----------Orders----------*/
    @PostMapping("/addOrder")
    public String addOrder(@ModelAttribute("newOrder") Order order,
                          HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        orderDao.insertOrder(new Order(order.getId(),
                null, order.getUserId(),
                null, order.getProductId()));

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/viewOrderEdit/{id}")
    public String viewEditOrder(@PathVariable int id, HttpServletRequest request, Model model) {
        if (isNoPermission(request))
            return "redirect:/";

        model.addAttribute("title", "Редактирование заказа");

        model.addAttribute("order", orderDao.getOrder(id));
        model.addAttribute("userList", userDao.getUserList());
        model.addAttribute("productList", productDao.getProductList());

        return "orderEdit";
    }

    @PostMapping("/editOrder")
    public String editOrder(@ModelAttribute("newOrder") Order order, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        orderDao.updateOrder(new Order(order.getId(),
                null, order.getUserId(),
                null, order.getProductId()));

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable int id, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        orderDao.deleteOrder(id);

        return "redirect:/admin/viewAdminPanel";
    }

    /*----------Comments----------*/
    @PostMapping("/addComment")
    public String addComment(@ModelAttribute("newComment") Comment comment,
                           HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        commentDao.insertComment(new Comment(comment.getId(), null, comment.getUserId(), comment.getTitle(),
                comment.getComment(), comment.getRating(), null, comment.getProductId()));

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/viewCommentEdit/{id}")
    public String viewEditComment(@PathVariable int id, HttpServletRequest request, Model model) {
        if (isNoPermission(request))
            return "redirect:/";

        model.addAttribute("title", "Редактирование комментария");

        model.addAttribute("comment", commentDao.getComment(id));
        model.addAttribute("userList", userDao.getUserList());
        model.addAttribute("productList", productDao.getProductList());

        return "commentEdit";
    }

    @PostMapping("/editComment")
    public String editComment(@ModelAttribute("newComment") Comment comment, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        commentDao.updateComment(new Comment(comment.getId(),
                null, comment.getUserId(),
                comment.getTitle(), comment.getComment(), comment.getRating(),
                null, comment.getProductId()));

        return "redirect:/admin/viewAdminPanel";
    }

    @GetMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable int id, HttpServletRequest request) {
        if (isNoPermission(request))
            return "redirect:/";

        commentDao.deleteComment(id);

        return "redirect:/admin/viewAdminPanel";
    }
}
