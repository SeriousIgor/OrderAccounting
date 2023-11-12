package com.springstudy.repositories;

import com.springstudy.models.Client;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Optional<Client>> findAllByIsDeletedTrue(Pageable pageable);
}
