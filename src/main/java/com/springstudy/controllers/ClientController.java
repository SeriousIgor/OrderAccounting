package com.springstudy.controllers;

import com.springstudy.models.Client;
import com.springstudy.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping(value = "/client")
public class ClientController extends ModelController<Client> {

    @Autowired
    public ClientController (ClientService clientService) {
        super(clientService);
    }
}
