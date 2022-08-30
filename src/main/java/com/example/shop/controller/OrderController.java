package com.example.shop.controller;

import com.example.shop.dao.interfaces.OrderDao;
import com.example.shop.dao.interfaces.ProductDao;
import com.example.shop.dao.interfaces.UserDao;
import com.example.shop.model.Order;
import com.example.shop.model.Product;
import com.example.shop.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final ArrayList<Product> orderList;
    private final OrderDao orderDao;
    private final ProductDao productDao;
    private final UserDao userDao;

    public OrderController(OrderDao orderDao, ProductDao productDao, UserDao userDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.userDao = userDao;
        orderList = new ArrayList<>();
    }

    @GetMapping("/viewOrder")
    public String viewOrder(Model model) {

        model.addAttribute("title", "Заказ");
        model.addAttribute("newOrder", new Order());
        model.addAttribute("basket", orderList);

        return "viewOrder";
    }

    @GetMapping("/addToBasket/{id}")
    public String addProductToOrderList(@PathVariable int id, Model model) {
        orderList.add(productDao.getProduct(id));
        model.addAttribute("addToBasketSuccess", true);

        return "redirect:/products/viewAllProducts";
    }

    @GetMapping("/deleteFromBasket/{id}")
    public String deleteProductFromBasket(@PathVariable int id, Model model) {
        orderList.remove(productDao.getProduct(id));
        model.addAttribute("deleteFromBasketSuccess", true);

        return "redirect:/orders/viewOrder";
    }

    @GetMapping("/saveOrder")
    public String saveOrder(Model model, Principal principal) {

        for (Product product : orderList) {
            Order order = new Order();
            order.setUserId(userDao.getUserByUsername(principal.getName()).getId());
            order.setProductId(product.getId());
            orderDao.insertOrder(order);
        }
        model.addAttribute("orderSaveSuccess");

        return "redirect:/orders/viewOrder";
    }
}
