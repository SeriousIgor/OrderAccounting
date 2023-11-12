package com.springstudy.services;

import javassist.NotFoundException;

import java.util.Collection;
import java.util.Optional;

public interface iModelService<T> {
    Optional<T> getRecord(Integer recordId) throws NotFoundException;
    Collection<Optional<T>> getRecords(Integer pageNumber, Integer pageSize, String... filters) throws NotFoundException;
    Collection<Optional<T>> getDeletedRecords(Integer pageNumber, Integer pageSize) throws NotFoundException;
    Optional<T> createRecord(String newRecord) throws Exception;
    Optional<T> updateRecord(String updatedRecord) throws Exception;
    void deleteRecord(Integer recordId, Boolean isSoftDelete) throws Exception;
}
