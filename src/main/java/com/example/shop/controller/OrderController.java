package com.example.shop.controller;

import com.example.shop.dao.interfaces.OrderDao;
import com.example.shop.dao.interfaces.ProductDao;
import com.example.shop.dao.interfaces.UserDao;
import com.example.shop.model.Order;
import com.example.shop.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderDao orderDao;
    private final ProductDao productDao;
    private final UserDao userDao;

    public OrderController(OrderDao orderDao, ProductDao productDao, UserDao userDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.userDao = userDao;
    }

    @GetMapping("/viewOrder")
    public String viewOrder(Model model, HttpSession session) {

        List<Product> productList = (List<Product>) session.getAttribute("basket");
        if (productList == null)
            productList = new ArrayList<>();

        model.addAttribute("title", "Заказ");
        model.addAttribute("newOrder", new Order());
        model.addAttribute("basket", productList);

        return "viewOrder";
    }

    @GetMapping("/addToBasket/{id}")
    public String addProductToOrderList(@PathVariable int id, HttpServletRequest request) {
        List<Product> productList = (List<Product>) request.getSession().getAttribute("basket");
        if (productList == null) {
            productList = new ArrayList<>();
            request.getSession().setAttribute("basket", productList);
        }

        productList.add(productDao.getProduct(id));

        return "redirect:/products/viewAllProducts";
    }

    @GetMapping("/deleteFromBasket/{id}")
    public String deleteProductFromBasket(@PathVariable int id, HttpServletRequest request) {

        List<Product> productList = (List<Product>) request.getSession().getAttribute("basket");
        productList.remove(productDao.getProduct(id));

        return "redirect:/orders/viewOrder";
    }

    @GetMapping("/saveOrder")
    public String saveOrder(Principal principal, HttpServletRequest request) {
        List<Product> productList = (List<Product>) request.getSession().getAttribute("basket");

        if (productList != null && productList.size() > 0) {
            for (Product product : productList) {
                Order order = new Order();
                order.setUserId(userDao.getUserByUsername(principal.getName()).getId());
                order.setProductId(product.getId());
                orderDao.insertOrder(order);
            }
            productList.clear();
        }

        return "redirect:/orders/viewOrder";
    }
}
