package com.springstudy.services;

import com.springstudy.models.Order;
import com.springstudy.repositories.OrderRepository;
import com.springstudy.utils.ServiceUtils;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OrderService implements iModelService<Order>{

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> getRecord(Integer recordId) throws NotFoundException {
        return this.orderRepository.findById(recordId);
    }

    @Override
    public Collection<Optional<Order>> getRecords(Integer pageNumber, Integer pageSize, String... filters) throws NotFoundException {
        return this.orderRepository.findAllByIsDeletedFalse(
                ServiceUtils.getPagination(pageNumber, pageSize)
        );
    }

    @Override
    public Collection<Optional<Order>> getDeletedRecords(Integer pageNumber, Integer pageSize) throws NotFoundException {
        return this.orderRepository.findAllByIsDeletedTrue(
                ServiceUtils.getPagination(pageNumber, pageSize)
        );
    }

    @Override
    public Optional<Order> createRecord(String newRecord) throws Exception {
        return Optional.of(
                this.orderRepository.save(
                        ServiceUtils.getParserRecordFromJson(newRecord, Order.class)
                )
        );
    }

    @Override
    public Optional<Order> updateRecord(String updatedRecord) throws Exception {
        return Optional.of(
                this.orderRepository.save(
                        ServiceUtils.getParserRecordFromJson(updatedRecord, Order.class)
                )
        );
    }

    @Override
    public void deleteRecord(Integer recordId, Boolean isSoftDelete) throws Exception {
        if (isSoftDelete != null && isSoftDelete) {
            Order order = this.orderRepository.getReferenceById(recordId);
            order.setIsDeleted(true);
            this.orderRepository.save(order);
        } else {
            this.orderRepository.deleteById(recordId);
        }
    }
}