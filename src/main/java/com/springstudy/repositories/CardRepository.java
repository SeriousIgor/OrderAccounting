package com.springstudy.repositories;

import com.springstudy.models.Card;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Optional<Card>> findAllByIsDeletedFalse(Pageable pageable);
    List<Optional<Card>> findAllByIsDeletedTrue(Pageable pageable);

    List<Optional<Card>> findAllByClient_Id(Integer clientId);

}
