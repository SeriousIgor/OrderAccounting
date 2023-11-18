package com.springstudy.repositories;

import com.springstudy.models.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkLogRepository extends JpaRepository<WorkLog, Integer> {
    List<Optional<WorkLog>> findAllByUser_Id(Integer userId);
}
