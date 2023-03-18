package com.springstudy.controllers;

import com.springstudy.services.iModelService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

public class ModelController {
    private static final Logger LOG = Logger.getLogger(ModelController.class.getName());
    protected final iModelService service;

    public ModelController(iModelService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Object getRecord(@PathVariable Integer id) {
        try {
            return this.service.getRecord(id);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @GetMapping("/all")
    public Collection<Object> getRecords(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                         @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                         @RequestParam(value = "filters", required = false) String... filters) {
        try {
            return this.service.getRecords(pageNumber, pageSize, filters);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return null;
        }
    }

    @GetMapping("/deleted")
    public Collection<Object> getDeletedRecords(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        try {
            return this.service.getDeletedRecords(pageNumber, pageSize);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return null;
        }
    }

    @PostMapping("/new")
    public Boolean createRecord(@RequestBody String newRecord) {
        try {
            return this.service.createRecord(newRecord);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return false;
        }
    }

    @PutMapping("/update")
    public Boolean updateRecord(@RequestBody String newRecord) {
        try {
            return this.service.updateRecord(newRecord);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return false;
        }
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteRecord(@PathVariable Integer id, @RequestParam(value = "soft", required = false) Boolean isSoftDelete) {
        try {
            return this.service.deleteRecord(id, isSoftDelete);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return false;
        }
    }
}
