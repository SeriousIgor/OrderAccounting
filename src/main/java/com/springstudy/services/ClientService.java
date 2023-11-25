package com.springstudy.services;

import com.springstudy.models.Client;
import com.springstudy.repositories.ClientRepository;
import com.springstudy.utils.ServiceUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("ClientService")
public class ClientService implements iModelService<Client> {
    private final ClientRepository clientRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<Client> getRecord(Integer recordId) throws NotFoundException {
        return this.clientRepository.findById(recordId);
    }

    @Override
    public Collection<Optional<Client>> getRecords(Integer pageNumber, Integer pageSize, String... filters) throws NotFoundException {
        return this.clientRepository
                .findAll(ServiceUtil.getPagination(pageNumber, pageSize))
                .stream()
                .map(Optional::of)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Optional<Client>> getDeletedRecords(Integer pageNumber, Integer pageSize) throws NotFoundException {
        return this.clientRepository
                .findAllByIsDeletedTrue(ServiceUtil.getPagination(pageNumber, pageSize))
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Client> createRecord(String newRecord) throws Exception {
        return Optional.of(this.clientRepository.save(
                ServiceUtil.getParserRecordFromJson(newRecord, Client.class)
            )
        );
    }

    @Override
    public Optional<Client> updateRecord(String updatedRecord) throws Exception {
        return Optional.of(this.clientRepository.save(
                ServiceUtil.getParserRecordFromJson(updatedRecord, Client.class)
            )
        );
    }

    @Override
    public void deleteRecord(Integer recordId, Boolean isSoftDelete) throws Exception {
        if (isSoftDelete == null || !isSoftDelete) {
            this.clientRepository.deleteById(recordId);
        } else {
            Client client = this.clientRepository.getReferenceById(recordId);
            client.setIsDeleted(true);
            this.clientRepository.save(client);
        }
    }
}
