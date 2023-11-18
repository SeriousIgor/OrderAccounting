package com.springstudy.services;

import com.springstudy.repositories.ServiceRepository;
import com.springstudy.utils.ServiceUtils;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("ServiceService")
public class ServiceService implements iModelService<com.springstudy.models.Service>{
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }
    @Override
    public Optional<com.springstudy.models.Service> getRecord(Integer recordId) throws NotFoundException {
        return this.serviceRepository.findById(recordId);
    }

    @Override
    public Collection<Optional<com.springstudy.models.Service>> getRecords(Integer pageNumber, Integer pageSize, String... filters) throws NotFoundException {
        Pageable pageable = ServiceUtils.getPagination(pageNumber, pageSize);
        return this.serviceRepository.findAll(pageable)
                .stream()
                .map(Optional::of)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Optional<com.springstudy.models.Service>> getDeletedRecords(Integer pageNumber, Integer pageSize) throws NotFoundException {
        Pageable pageable = ServiceUtils.getPagination(pageNumber, pageSize);
        return this.serviceRepository.findAllByIsDeletedTrue(pageable)
                .stream()
                .map(service -> Optional.of(service.get()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<com.springstudy.models.Service> createRecord(String newRecord) throws Exception {
        return Optional.of(this.serviceRepository.save(
                ServiceUtils.getParserRecordFromJson(newRecord, com.springstudy.models.Service.class)
            )
        );
    }

    @Override
    public Optional<com.springstudy.models.Service> updateRecord(String updatedRecord) throws Exception {
        com.springstudy.models.Service service = ServiceUtils.getParserRecordFromJson(updatedRecord, com.springstudy.models.Service.class);
        return Optional.of(this.serviceRepository.save(service));
    }

    @Override
    public void deleteRecord(Integer recordId, Boolean isSoftDelete) throws Exception {
        if (isSoftDelete == null || !isSoftDelete) {
            this.serviceRepository.deleteById(recordId);
        } else {
            com.springstudy.models.Service service = this.serviceRepository.getReferenceById(recordId);
            service.setIsDeleted(true);
            this.serviceRepository.save(service);
        }
    }
}
