package com.springstudy.controllers;

import com.springstudy.services.ClientService;
import com.springstudy.utils.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping(value = "/client")
public class ClientController extends ControllerUtil {

    @Autowired
    public ClientController (ClientService clientService) {
        super(clientService);
    }
}
