package com.springstudy.controllers;

import com.springstudy.models.Client;
import com.springstudy.services.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Collection<Client> getClients(@PathVariable String name, @PathVariable Integer pageNumber,
                                         @PathVariable Integer pageSize) {
        System.out.println("getClients");
        try {
            return clientService.getClients(name, pageNumber, pageSize);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return null;
        }
    }
}
