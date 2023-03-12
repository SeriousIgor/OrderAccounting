package com.springstudy.dao.implementation;

import com.springstudy.dao.ClientDao;
import com.springstudy.exceptions.EmptyDatabaseException;
import com.springstudy.mappers.ClientMapper;
import com.springstudy.models.Client;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javassist.NotFoundException;

import java.util.Collection;

@Repository
public class ClientDaoImplementation implements ClientDao {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger LOG = Logger.getLogger(ClientDaoImplementation.class);

    @Autowired
    public ClientDaoImplementation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Client getClient(Integer clientId) throws NotFoundException {
        try {
            return jdbcTemplate.queryForObject(GET_CLIENT_BY_ID, new ClientMapper(), clientId);
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new NotFoundException("Client with Id '" + clientId + "' not found");
        }
    }

    @Override
    public Collection<Client> getClients(int offset, int limit) throws NotFoundException {
        try {
            return jdbcTemplate.query(GET_CLIENTS, new ClientMapper(), offset, limit);
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new NotFoundException("Clients not found");
        }
    }

    @Override
    public Collection<Client> getClients(String name, int offset, int limit) throws NotFoundException {
        try {
            return jdbcTemplate.query(GET_CLIENTS_BY_NAME, new ClientMapper(), name, offset, limit);
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new NotFoundException("Clients not found");
        }
    }

    @Override
    public Collection<Client> getDeletedClients(int offset, int limit) throws NotFoundException {
        try {
            return jdbcTemplate.query(GET_DELETED_CLIENTS, new ClientMapper(), offset, limit);
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new NotFoundException("Clients not found");
        }
    }

    @Override
    public Boolean createClient(Client client) throws EmptyDatabaseException {
        try {
            return (jdbcTemplate.update(CREATE_CLIENT, client.getFirstName(), client.getLastName(), client.getPhoneNumber(), client.getEmail())) == 1;
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new EmptyDatabaseException("Client create failed");
        }
    }

    @Override
    public Boolean updateClient(Client client) throws EmptyDatabaseException {
        try {
            return (jdbcTemplate.update(UPDATE_CLIENT, client.getFirstName(), client.getLastName(), client.getPhoneNumber(), client.getEmail(), client.getClientId())) == 1;
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new EmptyDatabaseException("Client update exception");
        }
    }

    @Override
    public Boolean deleteClientSoft(Integer clientId) throws EmptyDatabaseException {
        try {
            return (jdbcTemplate.update(DELETE_CLIENT_SOFT, clientId)) == 1;
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new EmptyDatabaseException("Client update exception");
        }
    }

    @Override
    public Boolean deleteClient(Integer clientId) throws EmptyDatabaseException {
        try {
            return (jdbcTemplate.update(DELETE_CLIENT, clientId)) == 1;
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new EmptyDatabaseException("Client delete exception");
        }
    }
}
