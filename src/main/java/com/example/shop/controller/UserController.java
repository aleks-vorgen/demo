package com.example.shop.controller;

import com.example.shop.dao.UserDAO;
import com.example.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/viewLogin")
    public ModelAndView viewLogin(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("title", "Login");

        return new ModelAndView("login", "command", user);
    }

    @GetMapping("/viewRegister")
    public ModelAndView viewRegister(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("title", "Registration");

        return new ModelAndView("register", "command", user);
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, HttpServletRequest request) {
        User loggedUser = userDAO.getUser(user.getEmail());
        if (loggedUser == null || !loggedUser.getPassword().equals(user.getPassword())) {
            request.getSession().setAttribute("logError", true);
            return "redirect:/users/viewLogin";
        }
        else {
            request.getSession().removeAttribute("logError");
            request.getSession().setAttribute("user", loggedUser);
            return "redirect:/";
        }
    }

    @PostMapping("/register")
    public String create(@ModelAttribute("user") User user, HttpServletRequest request) {
        if (userDAO.getUser(user.getEmail()) != null) {
            request.getSession().setAttribute("regError", true);
            return "redirect:/users/viewRegister";
        }
        else {
            userDAO.insertUser(user);
            request.getSession().removeAttribute("regError");
            request.getSession().setAttribute("user", userDAO.getUser(user.getEmail()));
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
