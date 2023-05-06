package com.springstudy.services;

import com.springstudy.dao.UserDao;
import com.springstudy.models.User;
import com.springstudy.utils.ServiceUtils;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service("UserService")
public class UserService implements iModelService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<Object> getRecord(Integer recordId) throws NotFoundException {
        return this.userDao.getUser(recordId).map(user -> user);
    }

    public Optional<Object> getRecord(String name) throws NotFoundException {
        return this.userDao.getUser(name).map(user -> user);
    }

    @Override
    public Collection<Object> getRecords(Integer pageNumber, Integer pageSize, String... filters) throws NotFoundException {
        Map<String, Integer> userPaginationMap = ServiceUtils.getPagination(pageNumber, pageSize);
        return (Collection<Object>) (((Collection<?>)this.userDao.getUsers(userPaginationMap.get("offset"), userPaginationMap.get("limit"))));
    }

    @Override
    public Collection<Object> getDeletedRecords(Integer pageNumber, Integer pageSize) throws NotFoundException {
        Map<String, Integer> userPaginationMap = ServiceUtils.getPagination(pageNumber, pageSize);
        return (Collection<Object>) (((Collection<?>)this.userDao.getDeletedUsers(userPaginationMap.get("offset"), userPaginationMap.get("limit"))));
    }

    @Override
    public Boolean createRecord(String newRecord) throws Exception {
        return this.userDao.createUser(
                (User) ServiceUtils.getParserRecordFromJson(newRecord, User.class)
        );
    }

    @Override
    public Boolean updateRecord(String updatedRecord) throws Exception {
        return this.userDao.updateUser(
                (User) ServiceUtils.getParserRecordFromJson(updatedRecord, User.class)
        );
    }

    @Override
    public Boolean deleteRecord(Integer recordId, Boolean isSoftDelete) throws Exception {
        if (isSoftDelete == null || !isSoftDelete) {
            return this.userDao.deleteUser(recordId);
        } else {
            return this.userDao.deleteUserSoft(recordId);
        }

    }
}
