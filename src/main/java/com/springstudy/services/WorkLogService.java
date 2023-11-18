package com.springstudy.services;

import com.springstudy.models.User;
import com.springstudy.models.WorkLog;
import com.springstudy.repositories.UserRepository;
import com.springstudy.repositories.WorkLogRepository;
import com.springstudy.utils.ServiceUtils;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkLogService implements iModelService<WorkLog>{

    private final WorkLogRepository workLogRepository;
    private final UserRepository userRepository;

    @Autowired
    public WorkLogService(WorkLogRepository workLogRepository, UserRepository userRepository) {
        this.workLogRepository = workLogRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<WorkLog> getRecord(Integer recordId) throws NotFoundException {
        return this.workLogRepository.findById(recordId);
    }

    @Override
    public Collection<Optional<WorkLog>> getRecords(Integer pageNumber, Integer pageSize, String... filters) throws NotFoundException {
        return this.workLogRepository.findAll(ServiceUtils.getPagination(pageNumber, pageSize))
                .stream()
                .map(Optional::of)
                .collect(Collectors.toList());
    }

    public Collection<Optional<WorkLog>> getRecordsByUserId(Integer userId) {
        return this.workLogRepository.findAllByUser_Id(userId);
    }

    @Override
    public Collection<Optional<WorkLog>> getDeletedRecords(Integer pageNumber, Integer pageSize) throws NotFoundException {
        return null;
    }

    @Override
    public Optional<WorkLog> createRecord(String newRecord) throws Exception {
        WorkLog workLog = ServiceUtils.getParserRecordFromJson(newRecord, WorkLog.class);
        User user = this.userRepository.getReferenceById(workLog.getUser().getId());
        workLog.setUser(user);
        return Optional.of(
                this.workLogRepository.save(workLog)
            );
    }

    @Override
    public Optional<WorkLog> updateRecord(String updatedRecord) throws Exception {
        return Optional.of(
                this.workLogRepository.save(
                        ServiceUtils.getParserRecordFromJson(updatedRecord, WorkLog.class)
                )
        );
    }

    @Override
    public void deleteRecord(Integer recordId, Boolean isSoftDelete) throws Exception {
        this.workLogRepository.deleteById(recordId);
    }
}
