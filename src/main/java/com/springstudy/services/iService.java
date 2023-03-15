package com.springstudy.services;

import com.springstudy.exceptions.EmptyDatabaseException;
import javassist.NotFoundException;

import java.util.Collection;

public interface iService {
    public Object getRecord(Integer recordId) throws NotFoundException;
    public Collection<Object> getRecords(Integer pageNumber, Integer pageSize, String... filters) throws NotFoundException;
    public Collection<Object> getDeletedRecords(Integer pageNumber, Integer pageSize) throws NotFoundException;
    public Boolean createRecord(String newRecord) throws Exception;
    public Boolean updateRecord(Object updatedRecord) throws Exception;
    public Boolean deleteRecord(Integer recordId, Boolean isSoftDelete) throws Exception;
}
