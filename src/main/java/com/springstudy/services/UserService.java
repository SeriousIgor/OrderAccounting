package com.springstudy.services;

import com.springstudy.dao.UserDao;
import com.springstudy.utils.ServiceUtils;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service("UserService")
public class UserService implements iService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Object getRecord(Integer recordId) throws NotFoundException {
        return (Object) this.userDao.getUser(recordId);
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
        return null;
    }

    @Override
    public Boolean updateRecord(Object updatedRecord) throws Exception {
        return null;
    }

    @Override
    public Boolean deleteRecord(Integer recordId, Boolean isSoftDelete) throws Exception {
        return null;
    }
}
