package com.springstudy.listeners;

import com.springstudy.models.Order;
import com.springstudy.models.Service;

import java.math.BigDecimal;

public class OrderListener extends AbstractModelListener<Order>{
    @Override
    public void beforePersist(Order order) {
        if (order.getServices() != null) {
            BigDecimal orderPrice = new BigDecimal(0);
            for (Service service : order.getServices()) {
                orderPrice = orderPrice.add(BigDecimal.valueOf(service.getPrice()));
            }
            order.setPrice(orderPrice);
        }
    }
}
