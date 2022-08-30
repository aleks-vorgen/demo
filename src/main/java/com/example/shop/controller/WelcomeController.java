package com.example.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String welcome(Model model, Principal principal) {
        model.addAttribute("title", "Главная");
            model.addAttribute("username", principal == null ? "guest" : principal.getName());
        return "welcome";
    }
}
