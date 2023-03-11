package com.springstudy.mappers;

import com.springstudy.models.Client;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer clientId = rs.getInt("clientId");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        String phoneNumber = rs.getString("phoneNumber");
        String email = rs.getString("email");
        Boolean isDeleted = (rs.getInt("isDeleted") == 1);
        return new Client(clientId, firstName, lastName, phoneNumber, email, isDeleted);
    }
}
