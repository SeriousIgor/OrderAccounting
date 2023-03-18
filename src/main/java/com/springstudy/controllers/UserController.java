package com.springstudy.controllers;

import com.springstudy.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/user")
public class UserController extends ModelController {

    private UserService userService;
    private Logger LOG = Logger.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @GetMapping("/getByUsername")
    public Object getRecord(@RequestParam("username") String username) {
        try {
            return this.userService.getRecord(username);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}
