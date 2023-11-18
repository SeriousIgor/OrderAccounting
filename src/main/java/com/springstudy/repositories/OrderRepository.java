package com.springstudy.repositories;

import com.springstudy.models.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Optional<Order>> findAllByIsDeletedTrue(Pageable pageable);
    List<Optional<Order>> findAllByIsDeletedFalse(Pageable pageable);
    List<Optional<Order>> findAllByClient_Id(Integer clientId);
    List<Optional<Order>> findAllByUser_Id(Integer userId);
}
