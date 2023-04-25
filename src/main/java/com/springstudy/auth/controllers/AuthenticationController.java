package com.springstudy.auth.controllers;

import com.springstudy.auth.enitites.AuthenticationRequest;
import com.springstudy.auth.enitites.AuthenticationResponse;
import com.springstudy.auth.enitites.RegisterRequest;
import com.springstudy.auth.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthorizationService authService;

    @Autowired
    public AuthenticationController(AuthorizationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (
            @RequestBody RegisterRequest request
    ) throws Exception {
        return new ResponseEntity<>(this.authService.register(request), HttpStatus.CREATED);
    }

    //Todo: implement Exception handler to return normal error message
    @PostMapping("/authorize")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) throws Exception  {
        return new ResponseEntity<>(this.authService.authenticate(request), HttpStatus.OK);
    }
}
