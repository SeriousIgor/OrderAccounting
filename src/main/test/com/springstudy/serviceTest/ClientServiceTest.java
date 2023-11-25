package com.springstudy.serviceTest;

import com.springstudy.dataFactory.TestDataFactory;
import com.springstudy.models.Client;
import com.springstudy.repositories.ClientRepository;
import com.springstudy.services.ClientService;
import com.springstudy.utils.ServiceUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    @Tag("Service_Get_Client")
    public void getRecordTest() {
        // Preparing test data
        Integer clientId = 1;
        Client testClient = TestDataFactory.createClient("John");
        // Preparing test mock for method call
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(testClient));
        // Preparing variables for assert
        Optional<Client> result = Optional.empty();
        Exception exInstance = null;
        // Providing test call
        try {
            result = this.clientService.getRecord(clientId);
        } catch (Exception ex) {
            exInstance = ex;
        }
        // Asserting test results
        Assertions.assertNull(exInstance);
        Assertions.assertNotNull(result.orElse(null));
    }

    @Test
    @Tag("Service_Get_All_Clients")
    public void getRecordsTest() {
        List<Client> clientCollection = (List<Client>) TestDataFactory.createClientList(2);
        Page<Client> clientPage = new PageImpl<>(clientCollection);

        when(clientRepository.findAll(Mockito.any(Pageable.class))).thenReturn(clientPage);

        Collection<Optional<Client>> clients = null;
        Exception exInstance = null;
        try {
            clients = this.clientService.getRecords(0, 10);
        } catch (Exception ex) {
            exInstance = ex;
        }

        Assertions.assertNull(exInstance);
        Assertions.assertNotNull(clients);
        Assertions.assertEquals(2, clients.size());
    }

    @Test
    @Tag("Service_Get_Deleted_Clients")
    public void getDeletedRecordsTest() {
        List<Client> clientCollection = (List<Client>) TestDataFactory.createClientList(2);
        List<Optional<Client>> optionalList = clientCollection
                .stream()
                .map(Optional::of)
                .toList();

        when(clientRepository.findAllByIsDeletedTrue(ServiceUtil.getPagination(0, 10))).thenReturn(optionalList);

        Collection<Optional<Client>> clients = null;
        Exception exInstance = null;
        try {
            clients = this.clientService.getDeletedRecords(0, 10);
        } catch (Exception ex) {
            exInstance = ex;
        }

        Assertions.assertNull(exInstance);
        Assertions.assertNotNull(clients);
        Assertions.assertEquals(2, clients.size());
    }

    @Test
    @Tag("Service_Create_Client")
    public void createClientTest() {
        Client client = TestDataFactory.createClient("Ihor");
        client.setId(1);
        String clientJson = "{\"id\":1,\"firstName\":\"Cherida\",\"lastName\":\"Laza\",\"email\":\"clazar0@elpais.com\",\"isDeleted\":true,\"phoneNumber\":\"512-147-1650\"}";
        when(this.clientRepository.save(Mockito.any(Client.class))).thenReturn(client);

        Optional<Client> optionalClient = Optional.empty();
        Exception exInstance = null;
        try {
            optionalClient = this.clientService.createRecord(clientJson);
        } catch (Exception ex) {
            exInstance = ex;
        }

        Assertions.assertNull(exInstance);
        Assertions.assertTrue(optionalClient.isPresent());
        Assertions.assertFalse(optionalClient.isEmpty());
        Assertions.assertEquals(1, optionalClient.get().getId());
    }

    @Test
    @Tag("Service_Update_Client")
    public void updateClientTest() {
        Client client = TestDataFactory.createClient("Ihor");
        client.setId(1);
        String clientJson = "{\"id\":1,\"firstName\":\"Cherida\",\"lastName\":\"Laza\",\"email\":\"clazar0@elpais.com\",\"isDeleted\":true,\"phoneNumber\":\"512-147-1650\"}";
        when(this.clientRepository.save(Mockito.any(Client.class))).thenReturn(client);

        Optional<Client> optionalClient = Optional.empty();
        Exception exInstance = null;
        try {
            optionalClient = this.clientService.updateRecord(clientJson);
        } catch (Exception ex) {
            exInstance = ex;
        }

        Assertions.assertNull(exInstance);
        Assertions.assertTrue(optionalClient.isPresent());
        Assertions.assertFalse(optionalClient.isEmpty());
        Assertions.assertEquals(1, optionalClient.get().getId());
    }

    @Test
    @Tag("Service_Delete_Client_Soft")
    public void softDeleteClientTest() {
        Client client = TestDataFactory.createClient("Test");
        client.setId(1);

        when(this.clientRepository.getReferenceById(1)).thenReturn(client);

        Exception exInstance = null;
        try {
            this.clientService.deleteRecord(client.getId(), true);
        } catch (Exception ex) {
            exInstance = ex;
        }

        Assertions.assertNull(exInstance);
        Assertions.assertTrue(client.getIsDeleted());
    }

    @Test
    @Tag("Service_Delete_Client")
    public void deleteClientTest() {
        Client client = TestDataFactory.createClient("Test");
        client.setId(1);

        Exception exInstance = null;
        try {
            this.clientService.deleteRecord(client.getId(), false);
        } catch (Exception ex) {
            exInstance = ex;
        }

        Assertions.assertNull(exInstance);
    }
}
