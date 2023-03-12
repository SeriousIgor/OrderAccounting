package com.springstudy.utils;

import com.springstudy.services.iService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

public class ControllerUtil {
    private static final Logger LOG = Logger.getLogger(ControllerUtil.class.getName());
    protected final iService service;

    public ControllerUtil(iService service) {
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

    @GetMapping("/new")
    public Boolean createRecord(@RequestBody Object newRecord) {
        try {
            return this.service.createRecord(newRecord);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return false;
        }
    }

    @GetMapping("/update")
    public Boolean updateRecord(@RequestBody Object newRecord) {
        try {
            return this.service.updateRecord(newRecord);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return false;
        }
    }

    @GetMapping("/delete/{id}")
    public Boolean deleteRecord(@PathVariable Integer id, @RequestParam(value = "soft", required = false) Boolean isSoftDelete) {
        try {
            return this.service.deleteRecord(id, isSoftDelete);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return false;
        }
    }

    private iService getService(String recordName) {
        String serviceName = "com.springstudy.services." + recordName + "Service";
        try {
            System.out.println("serviceName " + serviceName);
            return (iService) Class.forName(serviceName).getConstructor().newInstance();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
}
