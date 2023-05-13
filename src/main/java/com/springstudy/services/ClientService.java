package com.springstudy.services;

import com.springstudy.dao.ClientDao;
import com.springstudy.models.Client;
import com.springstudy.utils.ServiceUtils;
import javassist.NotFoundException;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("ClientService")
public class ClientService implements iModelService {
    private final ClientDao clientDao;

    @Autowired
    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public Optional<Object> getRecord(Integer recordId) throws NotFoundException {
        return this.clientDao.getClient(recordId).map(record -> record);
    }

    @Override
    public Collection<Optional<Object>> getRecords(Integer pageNumber, Integer pageSize, String... filters) throws NotFoundException {
        Map<String, Integer> clientPaginationMap = ServiceUtils.getPagination(pageNumber, pageSize);
        Integer offset = clientPaginationMap.get("offset");
        Integer limit = clientPaginationMap.get("limit");
        Collection<Optional<Client>> clientCollection;
        if (ArrayUtils.isEmpty(filters) || (ArrayUtils.get(filters, 0).trim().equals(""))){
            clientCollection = this.clientDao.getClients(offset, limit);
        } else {
            clientCollection = this.clientDao.getClients(ArrayUtils.get(filters, 0), offset, limit);
        }

        return clientCollection.stream().map(client -> Optional.of((Object) client.get())).collect(Collectors.toList());
    }

    @Override
    public Collection<Optional<Object>> getDeletedRecords(Integer pageNumber, Integer pageSize) throws NotFoundException {
        Map<String, Integer> clientPaginationMap = ServiceUtils.getPagination(pageNumber, pageSize);
        return this.clientDao.getDeletedClients(clientPaginationMap.get("offset"), clientPaginationMap.get("limit"))
                .stream().map(client -> Optional.of((Object) client.get())).collect(Collectors.toList());
    }

    @Override
    public Boolean createRecord(String newRecord) throws Exception {
        return this.clientDao.createClient(
                (Client) ServiceUtils.getParserRecordFromJson(newRecord, Client.class)
        );
    }

    @Override
    public Boolean updateRecord(String updatedRecord) throws Exception {
        return this.clientDao.updateClient(
                (Client) ServiceUtils.getParserRecordFromJson(updatedRecord, Client.class)
        );
    }

    @Override
    public Boolean deleteRecord(Integer recordId, Boolean isSoftDelete) throws Exception {
        if (isSoftDelete == null || !isSoftDelete) {
            return this.clientDao.deleteClient(recordId);
        } else {
            return this.clientDao.deleteClientSoft(recordId);
        }
    }
}
