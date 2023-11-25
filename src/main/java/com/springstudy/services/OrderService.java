package com.springstudy.services;

import com.springstudy.models.Order;
import com.springstudy.repositories.OrderRepository;
import com.springstudy.repositories.ServiceRepository;
import com.springstudy.utils.ServiceUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service("OrderService")
public class OrderService implements iModelService<Order>{

    private final OrderRepository orderRepository;
    private final ServiceRepository serviceRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ServiceRepository serviceRepository) {
        this.orderRepository = orderRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Optional<Order> getRecord(Integer recordId) throws NotFoundException {
        return this.orderRepository.findById(recordId);
    }

    @Override
    public Collection<Optional<Order>> getRecords(Integer pageNumber, Integer pageSize, String... filters) throws NotFoundException {
        return this.orderRepository.findAllByIsDeletedFalse(
                ServiceUtil.getPagination(pageNumber, pageSize)
        );
    }

    @Override
    public Collection<Optional<Order>> getDeletedRecords(Integer pageNumber, Integer pageSize) throws NotFoundException {
        return this.orderRepository.findAllByIsDeletedTrue(
                ServiceUtil.getPagination(pageNumber, pageSize)
        );
    }

    @Override
    public Optional<Order> createRecord(String newRecord) throws Exception {
        return Optional.of(
                this.orderRepository.save(
                        ServiceUtil.getParserRecordFromJson(newRecord, Order.class)
                )
        );
    }

    @Override
    public Optional<Order> updateRecord(String updatedRecord) throws Exception {
        return Optional.of(
                this.orderRepository.save(
                        ServiceUtil.getParserRecordFromJson(updatedRecord, Order.class)
                )
        );
    }

    public Optional<Order> addServiceToOrder(Integer recordId, Integer serviceId) throws Exception {
        Order order = this.orderRepository.getReferenceById(recordId);
        com.springstudy.models.Service service = this.serviceRepository.findById(serviceId).get();
        order.addService(service);
        return Optional.of(
                this.orderRepository.save(order)
        );
    }

    public Optional<Order> removeServiceFromOrder(Integer recordId, Integer serviceId) throws Exception {
        Order order = this.orderRepository.getReferenceById(recordId);
        com.springstudy.models.Service service = this.serviceRepository.getReferenceById(serviceId);
        order.removeService(service);
        return Optional.of(
                this.orderRepository.save(order)
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

    public Collection<Optional<Order>> getRecordsByClientId(Integer clientId) {
        return this.orderRepository.findAllByClient_Id(clientId);
    }
}
