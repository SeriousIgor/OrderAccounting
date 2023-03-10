package com.springstudy.dao;

import com.springstudy.models.Client;

import java.util.Collection;

public interface ClientDao {
    Client getClient(Integer clientId);
    Collection<Client> getClients(int pageNumber, int pageSize);
    Collection<Client> getClients(String name, int pageNumber, int pageSize);
    Collection<Client> getDeletedClients();
    Boolean createClient(Client client);
    Boolean updateClient(Client client);
    Boolean deleteClient(Integer clientId);
}
