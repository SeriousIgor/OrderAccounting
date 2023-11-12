package com.springstudy.services;

import com.springstudy.models.User;
import com.springstudy.repositories.UserRepository;
import com.springstudy.utils.ServiceUtils;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("UserService")
public class UserService implements iModelService<User> {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getRecord(Integer recordId) {
        return this.userRepository.findById(recordId).map(user -> user);
    }

    public Optional<User> getRecord(String name) {
        return this.userRepository.findByUsername(name).map(user -> user);
    }

    @Override
    public Collection<Optional<User>> getRecords(Integer pageNumber, Integer pageSize, String... filters) throws NotFoundException {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return this.userRepository.findAll(pageable)
                .stream()
                .map(Optional::of)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Optional<User>> getDeletedRecords(Integer pageNumber, Integer pageSize) throws NotFoundException {
        Pageable pageable = ServiceUtils.getPagination(pageNumber, pageSize);
        return this.userRepository.findAllByIsDeletedTrue(pageable)
                .stream()
                .map(user -> Optional.of(user.get()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> createRecord(String newRecord) throws Exception {
        return Optional.of(this.userRepository.save(
                ServiceUtils.getParserRecordFromJson(newRecord, User.class)
        ));
    }

    @Override
    public Optional<User> updateRecord(String updatedRecord) throws Exception {
        User userFromJson = ServiceUtils.getParserRecordFromJson(updatedRecord, User.class);
        return Optional.of(this.userRepository.save(userFromJson));
    }

    @Override
    public void deleteRecord(Integer recordId, Boolean isSoftDelete) throws Exception {
        if (isSoftDelete == null || !isSoftDelete) {
            this.userRepository.deleteById(recordId);
        } else {
            User userToSoftDelete = this.userRepository.getReferenceById(recordId);
            userToSoftDelete.setIsDeleted(true);
            this.userRepository.save(userToSoftDelete);
        }

    }
}
