package com.springstudy.controllers;

import com.springstudy.services.iModelService;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

public class ModelController {
    private static final Logger LOG = Logger.getLogger(ModelController.class.getName());
    protected final iModelService service;

    public ModelController(iModelService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Object>> getRecord(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(this.service.getRecord(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Object>> getRecords(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                         @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                         @RequestParam(value = "filters", required = false) String... filters) throws NotFoundException {
            return ResponseEntity.ok(this.service.getRecords(pageNumber, pageSize, filters));

    }

    @GetMapping("/deleted")
    public ResponseEntity<Collection<Object>> getDeletedRecords(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                @RequestParam(value = "pageSize", required = false) Integer pageSize) throws NotFoundException {
            return ResponseEntity.ok(this.service.getDeletedRecords(pageNumber, pageSize));
    }

    @PostMapping("/new")
    public ResponseEntity<Boolean> createRecord(@RequestBody String newRecord) throws Exception {
            return ResponseEntity.ok(this.service.createRecord(newRecord));
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateRecord(@RequestBody String newRecord) throws Exception {
        return ResponseEntity.ok(this.service.updateRecord(newRecord));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteRecord(@PathVariable Integer id, @RequestParam(value = "soft", required = false) Boolean isSoftDelete) throws Exception {
        return ResponseEntity.ok(this.service.deleteRecord(id, isSoftDelete));
    }
}
