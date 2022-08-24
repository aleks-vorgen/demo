package com.example.shop.controller;

import com.example.shop.dao.OrderDAO;
import com.example.shop.dao.ProductDAO;
import com.example.shop.model.Order;
import com.example.shop.model.Product;
import com.example.shop.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final ArrayList<Product> orderList;
    private final OrderDAO orderDAO;
    private final ProductDAO productDAO;

    public OrderController(OrderDAO orderDAO, ProductDAO productDAO) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
        orderList = new ArrayList<>();
    }

    @GetMapping("/viewOrder")
    public String viewOrder(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("user") == null)
            return "redirect:/users/viewLogin";

        model.addAttribute("title", "Заказ");
        model.addAttribute("newOrder", new Order());
        model.addAttribute("user", request.getSession().getAttribute("user"));
        model.addAttribute("basket", orderList);

        return "viewOrder";
    }

    @GetMapping("/addToBasket/{id}")
    public String addProductToOrderList(@PathVariable int id, Model model) {
        orderList.add(productDAO.getProduct(id));
        model.addAttribute("addToBasketSuccess", true);

        return "redirect:/products/viewAllProducts";
    }

    @GetMapping("/deleteFromBasket/{id}")
    public String deleteProductFromBasket(@PathVariable int id, Model model) {
        orderList.remove(productDAO.getProduct(id));
        model.addAttribute("deleteFromBasketSuccess", true);

        return "redirect:/orders/viewOrder";
    }

    @GetMapping("/saveOrder")
    public String saveOrder(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("user") == null)
            return "redirect:/users/viewLogin";

        User user = (User) request.getSession().getAttribute("user");
        for (Product product : orderList) {
            Order order = new Order();
            order.setUserId(user.getId());
            order.setProductId(product.getId());
            orderDAO.insertOrder(order);
        }
        model.addAttribute("orderSaveSuccess");

        return "redirect:/orders/viewOrder";
    }
}
