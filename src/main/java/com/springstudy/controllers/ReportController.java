package com.springstudy.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springstudy.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<String> getOrdersForClient(
            @PathVariable Integer clientId
    ) throws JsonProcessingException {
        return ResponseEntity.ok(
                this.reportService.getReportForClient(clientId)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<String> getOrdersForUser(
            @PathVariable Integer userId
    ) throws JsonProcessingException {
        return ResponseEntity.ok(
                this.reportService.getReportForUserSuccessMetrics(userId)
        );
    }

    @GetMapping("/user/{userId}/yearMetrics")
    public ResponseEntity<String> getYearMetricsForUser(
            @PathVariable Integer userId
    ) throws JsonProcessingException {
        return ResponseEntity.ok(
                this.reportService.getYearlyReportForUser(userId)
        );
    }
}
