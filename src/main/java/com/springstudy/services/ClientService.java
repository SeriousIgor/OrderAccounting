package com.springstudy.services;

import com.springstudy.dao.ClientDao;
import com.springstudy.exceptions.EmptyDatabaseException;
import com.springstudy.models.Client;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ClientService implements iService {
    private final ClientDao clientDao;

    @Autowired
    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

//    public Client getClient(Integer clientId) {
//        return this.clientDao.getClient(clientId);
//    }
//
//    public Collection<Client> getClients(String name, Integer pageNumber, Integer pageSize) {
//        if ((pageNumber == null) || (pageNumber < 1)) {
//            pageNumber = 1;
//        }
//        if ((pageSize == null) || pageSize < 1) {
//            pageSize = Integer.MAX_VALUE;
//        }
//        if ((name == null) || (name.trim().equals(""))){
//            return this.clientDao.getClients(pageNumber, pageSize);
//        } else {
//            return this.clientDao.getClients(name, pageNumber, pageSize);
//        }
//    }
//
//    public Collection<Client> getDeletedClients(Integer pageNumber, Integer pageSize) {
//        if ((pageNumber == null) || (pageNumber < 1)) {
//            pageNumber = 1;
//        }
//        if ((pageSize == null) || pageSize < 1) {
//            pageSize = Integer.MAX_VALUE;
//        }
//        return this.clientDao.getDeletedClients(pageNumber, pageSize);
//    }
//
//    public Boolean createClient(Client newClient) {
//        return this.clientDao.createClient(newClient);
//    }
//
//    public Boolean updateClient(Client updatedClient) {
//        return this.clientDao.updateClient(updatedClient);
//    }
//
//    public Boolean deleteClient(Integer clientId, Boolean isSoftDelete) {
//        if (isSoftDelete) {
//            return this.clientDao.deleteClientSoft(clientId);
//        } else {
//            return this.clientDao.deleteClient(clientId);
//        }
//    }

    @Override
    public Object getRecord(Integer recordId) throws NotFoundException {
        return (Object) this.clientDao.getClient(recordId);
    }

    @Override
    public Collection<Object> getRecords(Integer pageNumber, Integer pageSize, String... filters) throws NotFoundException {
        String name;
        Integer limit;
        Integer offset;
        if (filters != null && filters.length > 0) {
            name = filters[0];
        } else {
            name = null;
        }
        if ((pageNumber == null) || (pageNumber < 1)) {
            pageNumber = 0;
        }
        if ((pageSize == null) || pageSize < 1) {
            offset = 0;
            limit = Integer.MAX_VALUE;
        } else {
            offset = (pageNumber - 1) * pageSize;
            limit = pageNumber * pageSize;
        }
        if ((name == null) || (name.trim().equals(""))){
            return (Collection<Object>) (((Collection<?>)this.clientDao.getClients(offset, limit)));
        } else {
            return (Collection<Object>) (((Collection<?>)this.clientDao.getClients(name,offset, limit)));
        }
    }

    @Override
    public Collection<Object> getDeletedRecords(Integer pageNumber, Integer pageSize) throws NotFoundException {
        Integer limit;
        Integer offset;
        if ((pageNumber == null) || (pageNumber < 1)) {
            pageNumber = 1;
        }
        if ((pageSize == null) || pageSize < 1) {
            offset = 0;
            limit = Integer.MAX_VALUE;
        } else {
            offset = (pageNumber - 1) * pageSize;
            limit = pageNumber * pageSize;
        }
        return (Collection<Object>) (((Collection<?>)this.clientDao.getDeletedClients(offset, limit)));
    }

    @Override
    public Boolean createRecord(Object newRecord) throws EmptyDatabaseException {
        return this.clientDao.createClient((Client) newRecord);
    }

    @Override
    public Boolean updateRecord(Object updatedRecord) throws EmptyDatabaseException {
        return this.clientDao.updateClient((Client) updatedRecord);
    }

    @Override
    public Boolean deleteRecord(Integer recordId, Boolean isSoftDelete) throws EmptyDatabaseException {
        if (isSoftDelete) {
            return this.clientDao.deleteClientSoft(recordId);
        } else {
            return this.clientDao.deleteClient(recordId);
        }
    }
}
