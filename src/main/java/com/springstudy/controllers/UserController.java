package com.springstudy.controllers;

import com.springstudy.services.UserService;
import com.springstudy.utils.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/user")
public class UserController extends MainController {

    @Autowired
    public UserController(UserService userService) {
        super(userService);
    }
}
