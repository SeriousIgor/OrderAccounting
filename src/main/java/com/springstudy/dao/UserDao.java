package com.springstudy.dao;

import com.springstudy.exceptions.entities.DatabaseDataUpdateException;
import com.springstudy.models.User;
import javassist.NotFoundException;

import java.util.Collection;

public interface UserDao {
    User getUser(Integer userId) throws NotFoundException;
    User getUser(String email) throws NotFoundException;
    Collection<User> getUsers(int offset, int limit) throws NotFoundException;
    Collection<User> getDeletedUsers(int offset, int limit) throws NotFoundException;
    Boolean createUser(User user) throws DatabaseDataUpdateException;
    Boolean updateUser(User user) throws DatabaseDataUpdateException;
    Boolean deleteUserSoft(Integer userId) throws DatabaseDataUpdateException;
    Boolean deleteUser(Integer userId) throws DatabaseDataUpdateException;

    String GET_USER = "SELECT * FROM Users WHERE userId = ?";
    String GET_USERS = "SELECT * FROM Users WHERE isDeleted = false OFFSET ? LIMIT ?";
    String GET_USER_BY_USERNAME = "SELECT * FROM Users WHERE email = ? OR userName = ?";
    String GET_DELETED_USERS = "SELECT * FROM Users WHERE isDeleted = true OFFSET ? LIMIT ?";
    String CREATE_USER = "INSERT INTO Users (userName, email, password, firstName, lastName) VALUES (?, ?, ?, ?, ?)";
    String UPDATE_USER = "UPDATE Users SET userName = ?, email = ?, password = ?, firstName = ?, lastName = ? WHERE userId = ?";
    String DELETE_USER_SOFT = "UPDATE Users SET isDeleted = true WHERE userId = ?";
    String DELETE_USER = "DELETE FROM Users WHERE userId = ?";
}
