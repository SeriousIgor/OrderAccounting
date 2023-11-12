package com.springstudy.controllers;

import com.springstudy.models.User;
import com.springstudy.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Validated
@RequestMapping("/user")
public class UserController extends ModelController<User> {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @GetMapping("/getByUsername")
    public ResponseEntity<Object> getRecord(@RequestParam("username") String username) throws NotFoundException {
        return ResponseEntity.ok(this.userService.getRecord(username));
    }
}
