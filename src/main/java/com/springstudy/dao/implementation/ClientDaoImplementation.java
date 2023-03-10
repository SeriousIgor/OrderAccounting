package com.springstudy.dao.implementation;

import com.springstudy.dao.ClientDao;
import com.springstudy.models.Client;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public Client getClient(Integer clientId) {
        return null;
    }

    @Override
    public Collection<Client> getClients(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public Collection<Client> getClients(String name, int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public Collection<Client> getDeletedClients() {
        return null;
    }

    @Override
    public Boolean createClient(Client client) {
        return null;
    }

    @Override
    public Boolean updateClient(Client client) {
        return null;
    }

    @Override
    public Boolean deleteClient(Integer clientId) {
        return null;
    }
}
