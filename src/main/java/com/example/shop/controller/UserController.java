package com.example.shop.controller;

import com.example.shop.dao.interfaces.UserDao;
import com.example.shop.model.User;
//import com.example.shop.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserDao userDao;
    //private final SecurityService securityService;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
        //this.securityService = securityService;
    }

    @GetMapping("/registration")
    public String viewRegister(Model model) {
        model.addAttribute("title", "Registration");
        model.addAttribute("userForm", new User());

        return "register";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("userForm") User user, Model model) {
        if (userDao.getUser(user.getEmail()) != null || userDao.getUserByUsername(user.getUsername()) != null) {
            model.addAttribute("regError", "Такой пользователь уже существует");
            return "register";
        }
        else {
            user.setPermissions("ROLE_USER");
            user.setActive(true);
            userDao.insertUser(user);
            return "redirect:/login";
        }
    }
}
