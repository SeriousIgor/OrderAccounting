package com.springstudy.dao;

import com.springstudy.exceptions.EmptyDatabaseException;
import com.springstudy.models.Client;
import javassist.NotFoundException;

import java.util.Collection;

public interface ClientDao {
    Client getClient(Integer clientId) throws NotFoundException;
    Collection<Client> getClients(int offset, int limit) throws NotFoundException;
    Collection<Client> getClients(String name, int offset, int limit) throws NotFoundException;
    Collection<Client> getDeletedClients(int offset, int limit) throws NotFoundException;
    Boolean createClient(Client client) throws EmptyDatabaseException;
    Boolean updateClient(Client client) throws EmptyDatabaseException;
    Boolean deleteClientSoft(Integer clientId) throws EmptyDatabaseException;
    Boolean deleteClient(Integer clientId) throws EmptyDatabaseException;

    String GET_CLIENT_BY_ID = "SELECT * FROM Clients WHERE clientId = ?";

    String GET_CLIENTS = "SELECT * FROM Clients OFFSET ? LIMIT ?";

    String GET_CLIENTS_BY_NAME = "SELECT * FROM Clients WHERE fistName LIKE %?% OR lastName LIKE %?% OFFSET ? LIMIT ?";
}
