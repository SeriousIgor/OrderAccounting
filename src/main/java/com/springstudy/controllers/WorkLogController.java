package com.springstudy.controllers;

import com.springstudy.models.WorkLog;
import com.springstudy.services.WorkLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/worklog")
public class WorkLogController extends ModelController<WorkLog>{
    public WorkLogController(WorkLogService workLogService) {
        super(workLogService);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Collection<Optional<WorkLog>>> getWorkLogByUserId(
            @PathVariable Integer userId
    ) {
        WorkLogService workLogService = (WorkLogService) this.service;
        return ResponseEntity.ok(
            workLogService.getRecordsByUserId(userId)
        );
    }
}
