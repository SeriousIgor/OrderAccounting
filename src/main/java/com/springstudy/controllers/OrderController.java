package com.springstudy.controllers;

import com.springstudy.models.Order;
import com.springstudy.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(value = "/order/")
public class OrderController extends ModelController<Order>{

    @Autowired
    public OrderController(OrderService service) {
        super(service);
    }
}
