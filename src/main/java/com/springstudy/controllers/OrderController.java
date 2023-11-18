package com.springstudy.controllers;

import com.springstudy.models.Order;
import com.springstudy.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/order")
public class OrderController extends ModelController<Order>{

    @Autowired
    public OrderController(OrderService service) {
        super(service);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Collection<Optional<Order>>> getOrdersByClientId(
            @PathVariable Integer clientId
    ) {
        OrderService orderService = (OrderService) this.service;
        return ResponseEntity.ok(
                orderService.getRecordsByClientId(clientId)
        );
    }

    @PutMapping("/{orderId}/addService/{serviceId}")
    public ResponseEntity<Optional<Order>> addServiceToOrder(
            @PathVariable Integer orderId,
            @PathVariable Integer serviceId
            ) throws Exception {
        OrderService orderService = (OrderService) this.service;
        return ResponseEntity.ok(
                orderService.addServiceToOrder(orderId, serviceId)
        );
    }

    @PutMapping("/{orderId}/removeService/{serviceId}")
    public ResponseEntity<Optional<Order>> removeServiceToOrder(
            @PathVariable Integer orderId,
            @PathVariable Integer serviceId
    ) throws Exception {
        OrderService orderService = (OrderService) this.service;
        return ResponseEntity.ok(
                orderService.removeServiceFromOrder(orderId, serviceId)
        );
    }
}
