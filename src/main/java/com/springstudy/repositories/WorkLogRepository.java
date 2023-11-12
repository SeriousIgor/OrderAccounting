package com.springstudy.repositories;

import com.springstudy.models.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkLogRepository extends JpaRepository<WorkLog, Integer> {
}
