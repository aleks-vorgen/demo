package com.example.demo.controller;

import com.example.demo.dao.PostgresFactory;
import org.springframework.stereotype.Controller;

@Controller
public abstract class AbstractController {
    protected final PostgresFactory factory = new PostgresFactory();
}
