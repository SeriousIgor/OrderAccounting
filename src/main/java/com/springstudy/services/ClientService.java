package com.springstudy.services;

import com.springstudy.dao.ClientDao;
import com.springstudy.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ClientService {
    private final ClientDao clientDao;

    @Autowired
    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public Collection<Client> getClients(String name, Integer pageNumber, Integer pageSize) {
        if ((pageNumber == null) || (pageNumber < 1)) {
            pageNumber = 1;
        }
        if ((pageSize == null) || pageSize < 1) {
            pageSize = Integer.MAX_VALUE;
        }
        if ((name == null) || (name.trim().equals(""))){
            return this.clientDao.getClients(pageNumber, pageSize);
        } else {
            return this.clientDao.getClients(name, pageNumber, pageSize);
        }
    }
}
