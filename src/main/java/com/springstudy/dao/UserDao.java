package com.springstudy.dao;

import com.springstudy.exceptions.DeleteRecordException;
import com.springstudy.exceptions.EmptyDatabaseException;
import com.springstudy.models.User;
import javassist.NotFoundException;

import java.util.Collection;

public interface UserDao {
    User getUser(Integer userId) throws NotFoundException;
    User getUser(String email) throws NotFoundException;
    Collection<User> getUsers(int offset, int limit) throws NotFoundException;
    Collection<User> getDeletedUsers(int offset, int limit) throws NotFoundException;
    Boolean createUser(User user) throws EmptyDatabaseException;
    Boolean updateUser(User user) throws EmptyDatabaseException;
    Boolean deleteUserSoft(Integer userId) throws DeleteRecordException;
    Boolean deleteUser(Integer userId) throws DeleteRecordException;

    String GET_USER = "SELECT * FROM Users WHERE userId = ?";
    String GET_USERS = "SELECT * FROM Users WHERE isDeleted = false OFFSET ? LIMIT ?";
    String GET_USER_BY_EMAIL = "SELECT * FROM Users WHERE email = ?";
    String GET_DELETED_USERS = "SELECT * FROM Users WHERE isDeleted = false OFFSET ? LIMIT ?";
    String CREATE_USER = "INSERT INTO Users (userName, email, firstName, lastName, password) VALUES (?, ?, ?, ?, ?)";
    String UPDATE_USER = "UPDATE Users SET userName = ?, email = ?, firstName = ?, lastName = ?, password = ? WHERE userId = ?";
    String DELETE_USER_SOFT = "UPDATE Users SET isDeleted = true WHERE userId = ?";
    String DELETE_USER = "DELETE FROM Users WHERE userId = ?";
}
