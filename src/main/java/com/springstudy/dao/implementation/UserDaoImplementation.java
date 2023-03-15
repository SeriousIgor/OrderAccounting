package com.springstudy.dao.implementation;

import com.springstudy.dao.UserDao;
import com.springstudy.exceptions.DeleteRecordException;
import com.springstudy.exceptions.EmptyDatabaseException;
import com.springstudy.models.User;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class UserDaoImplementation implements UserDao {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger LOG = Logger.getLogger(UserDaoImplementation.class);

    @Autowired
    public UserDaoImplementation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUser(Integer userId) throws NotFoundException {
        try {
            return this.jdbcTemplate.queryForObject(GET_USER, new BeanPropertyRowMapper<>(User.class), userId);
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new NotFoundException("User with Id '" + userId + "' not found");
        }
    }

    @Override
    public User getUser(String email) throws NotFoundException {
        try {
            return this.jdbcTemplate.queryForObject(GET_USER_BY_EMAIL, new BeanPropertyRowMapper<>(User.class), email);
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new NotFoundException("User with email '" + email + "' not found");
        }
    }

    @Override
    public Collection<User> getUsers(int offset, int limit) throws NotFoundException {
        try {
            return this.jdbcTemplate.query(GET_USERS, new BeanPropertyRowMapper<>(User.class), offset, limit);
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new NotFoundException("Users not found");
        }
    }

    @Override
    public Collection<User> getDeletedUsers(int offset, int limit) throws NotFoundException {
        try {
            return this.jdbcTemplate.query(GET_DELETED_USERS, new BeanPropertyRowMapper<>(User.class), offset, limit);
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new NotFoundException("Deleted Users not found");
        }
    }

    @Override
    public Boolean createUser(User user) throws EmptyDatabaseException {
        try {
            return (this.jdbcTemplate.update(CREATE_USER, user.getUserName(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getLastName()) == 1);
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new EmptyDatabaseException("Create User failed");
        }
    }

    @Override
    public Boolean updateUser(User user) throws EmptyDatabaseException {
        try {
            return (this.jdbcTemplate.update(UPDATE_USER, user.getUserName(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getLastName(), user.getUserId()) == 1);
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new EmptyDatabaseException("Update User failed");
        }
    }

    @Override
    public Boolean deleteUserSoft(Integer userId) throws DeleteRecordException {
        try {
            return (this.jdbcTemplate.update(DELETE_USER_SOFT, userId) == 1);
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new DeleteRecordException("Soft Delete User failed");
        }
    }

    @Override
    public Boolean deleteUser(Integer userId) throws DeleteRecordException {
        try {
            return (this.jdbcTemplate.update(DELETE_USER, userId) == 1);
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new DeleteRecordException("Delete User failed");
        }
    }
}
