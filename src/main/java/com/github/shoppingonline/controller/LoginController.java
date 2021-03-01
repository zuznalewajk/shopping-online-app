package com.github.shoppingonline.controller;

import com.github.shoppingonline.logic.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    private UserService userService;

    public LoginController(UserService service) {
        this.userService = service;

    }
}
