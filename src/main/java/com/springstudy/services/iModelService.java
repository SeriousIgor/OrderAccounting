package com.springstudy.services;

import javassist.NotFoundException;

import java.util.Collection;

public interface iModelService {
    Object getRecord(Integer recordId) throws NotFoundException;
    Collection<Object> getRecords(Integer pageNumber, Integer pageSize, String... filters) throws NotFoundException;
    Collection<Object> getDeletedRecords(Integer pageNumber, Integer pageSize) throws NotFoundException;
    Boolean createRecord(String newRecord) throws Exception;
    Boolean updateRecord(String updatedRecord) throws Exception;
    Boolean deleteRecord(Integer recordId, Boolean isSoftDelete) throws Exception;
}
