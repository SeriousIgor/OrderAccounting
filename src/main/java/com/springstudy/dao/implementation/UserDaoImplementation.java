package com.springstudy.dao.implementation;

import com.springstudy.dao.UserDao;
import com.springstudy.exceptions.entities.DatabaseDataUpdateException;
import com.springstudy.models.User;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Deprecated
@Repository
public class UserDaoImplementation implements UserDao {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger LOG = Logger.getLogger(UserDaoImplementation.class);

    @Autowired
    public UserDaoImplementation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> getUser(Integer userId){
        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(GET_USER, new BeanPropertyRowMapper<>(User.class), userId));
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
//            throw new NotFoundException("User with Id '" + userId + "' not found");
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUser(String username) {
        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(GET_USER_BY_USERNAME, new BeanPropertyRowMapper<>(User.class), username, username));
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
//            throw new NotFoundException("User with username '" + username + "' not found");
        }
        return Optional.empty();
    }

    @Override
    public Collection<Optional<User>> getUsers(int offset, int limit) throws NotFoundException {
        try {
            return collectRecordList(this.jdbcTemplate.query(GET_USERS, new BeanPropertyRowMapper<>(User.class), offset, limit));
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new NotFoundException("Users not found");
        }
    }

    @Override
    public Collection<Optional<User>> getDeletedUsers(int offset, int limit) throws NotFoundException {
        try {
            return collectRecordList(this.jdbcTemplate.query(GET_DELETED_USERS, new BeanPropertyRowMapper<>(User.class), offset, limit));
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new NotFoundException("Deleted Users not found");
        }
    }

    @Override
    public Boolean createUser(User user) throws DatabaseDataUpdateException {
        try {
            return (this.jdbcTemplate.update(CREATE_USER, user.getUsername(), user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName()) == 1);
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new DatabaseDataUpdateException("Create User failed");
        }
    }

    @Override
    public Boolean updateUser(User user) throws DatabaseDataUpdateException {
        try {
            return (this.jdbcTemplate.update(UPDATE_USER, user.getUsername(), user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getId()) == 1);
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new DatabaseDataUpdateException("Update User failed");
        }
    }

    @Override
    public Boolean deleteUserSoft(Integer userId) throws DatabaseDataUpdateException {
        try {
            return (this.jdbcTemplate.update(DELETE_USER_SOFT, userId) == 1);
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new DatabaseDataUpdateException("Soft Delete User failed");
        }
    }

    @Override
    public Boolean deleteUser(Integer userId) throws DatabaseDataUpdateException {
        try {
            return (this.jdbcTemplate.update(DELETE_USER, userId) == 1);
        } catch (DataAccessException ex) {
            LOG.error(ex.getMessage());
            throw new DatabaseDataUpdateException("Delete User failed");
        }
    }

    private Collection<Optional<User>> collectRecordList(Collection<User> recordList) {
        return recordList
                .stream()
                .map(Optional::ofNullable)
                .collect(Collectors.toList());
    }
}
