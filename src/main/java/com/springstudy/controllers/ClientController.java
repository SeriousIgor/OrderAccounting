package com.springstudy.controllers;

import com.springstudy.models.Client;
import com.springstudy.services.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Validated
@RequestMapping(value = "/client/")
public class ClientController {
    private static final Logger LOG = Logger.getLogger(ClientController.class.getName());
    private final ClientService clientService;

    @Autowired
    public ClientController (ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public Collection<Client> getClients(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                         @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        System.out.println("getClients");
        try {
            return clientService.getClients(name, pageNumber, pageSize);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return null;
        }
    }
}
