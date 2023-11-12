package com.springstudy.controllers;

import com.springstudy.models.Service;
import com.springstudy.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(value = "/service")
public class ServiceController extends ModelController<Service> {

    @Autowired
    public ServiceController(ServiceService service) {
        super(service);
    }
}
