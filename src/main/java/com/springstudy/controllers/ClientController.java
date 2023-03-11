package com.springstudy.controllers;

import com.springstudy.enums.DBEntities;
import com.springstudy.utils.ControllerUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Validated
@RequestMapping(value = "/client/")
public class ClientController extends ControllerUtil {
//    private static final Logger LOG = Logger.getLogger(ClientController.class.getName());
//    private final ControllerUtil controllerUtil;

    @Autowired
    public ClientController () {
//        this.controllerUtil = new ControllerUtil("Client");
        super(DBEntities.Client.name());
    }

//    @GetMapping("/{id}")
//    public Object getClient(@PathVariable Integer id) {
//        System.out.println("getClient");
//        return this.controllerUtil.getRecord(id);
//    }
//    @GetMapping("/clients")
//    public Collection<Object> getClients(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
//                                         @RequestParam(value = "pageSize", required = false) Integer pageSize) {
//        System.out.println("getClients");
//        return this.controllerUtil.getRecords(pageNumber, pageSize, name);
//    }
//
//    @GetMapping("/deleted")
//    public Collection<Object> getDeletedClients(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
//                                                @RequestParam(value = "pageSize", required = false) Integer pageSize) {
//        System.out.println("getDeletedClients");
//        return this.controllerUtil.getDeletedRecords(pageNumber, pageSize);
//    }
}
