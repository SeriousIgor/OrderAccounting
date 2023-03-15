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

    String GET_CLIENTS = "SELECT * FROM Clients WHERE isDeleted = 'FALSE' OFFSET ? LIMIT ?";

    String GET_CLIENTS_BY_NAME = "SELECT * FROM Clients WHERE isDeleted = FALSE AND (fistName LIKE %?% OR lastName LIKE %?%) OFFSET ? LIMIT ?";

    String GET_DELETED_CLIENTS = "SELECT * FROM Clients WHERE isDeleted = TRUE OFFSET ? LIMIT ?";

    String CREATE_CLIENT = "INSERT INTO Clients (firstName, lastName, phoneNumber, email) VALUES (?, ?, ?, ?)";

    String UPDATE_CLIENT = "UPDATE Clients SET firstName = ?, lastName = ?, phoneNumber = ?, email = ? WHERE clientId = ?";

    String DELETE_CLIENT_SOFT = "UPDATE Clients SET isDeleted = TRUE WHERE clientId = ?";

    String DELETE_CLIENT = "DELETE FROM Clients WHERE clientId = ?";
}
