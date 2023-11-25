package com.springstudy.controllerTest;

import com.springstudy.controllers.ClientController;
import com.springstudy.dataFactory.TestDataFactory;
import com.springstudy.models.Client;
import com.springstudy.services.ClientService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @Mock
    ClientService clientService;

    @InjectMocks
    ClientController clientController;

    @Test
    @Tag("Controller_Get_Client")
    public void getClientTest() {
        int clientId = 1;
        Optional<Client> clientOptional = Optional.of(TestDataFactory.createClient("test"));

        try {
            when(this.clientService.getRecord(clientId)).thenReturn(clientOptional);
        } catch (NotFoundException e) {
            Assertions.fail("Error getting record from ClientService");
        }

        ResponseEntity<Optional<Client>> response = null;
        Exception exInstance = null;

        try {
            response = this.clientController.getRecord(1);
        } catch (Exception ex) {
            exInstance = ex;
        }

        Assertions.assertNull(exInstance);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    @Test
    @Tag("Controller_Get_All_Clients")
    public void getRecordsTest() {
        Collection<Client> clientCollection = TestDataFactory.createClientList(2);
        Collection<Optional<Client>> optionalCollection = clientCollection
                .stream()
                .map(Optional::of)
                .toList();
        try {
            when(this.clientService.getRecords(Mockito.anyInt(), Mockito.anyInt())).thenReturn(optionalCollection);
        } catch (NotFoundException e) {
            Assertions.fail("Error getting record from ClientService");
        }

        ResponseEntity<Collection<Optional<Client>>> response = null;
        Exception exInstance = null;
        try {
            response = this.clientController.getRecords(1, 1);
        } catch (Exception ex) {
            exInstance = ex;
        }

        Assertions.assertNull(exInstance);
        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.getBody().isEmpty());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    @Test
    @Tag("Controller_Get_Deletd_Clients")
    public void getDeletedRecordsTest() {
        Collection<Client> clientCollection = TestDataFactory.createClientList(2);
        Collection<Optional<Client>> optionalCollection = clientCollection
                .stream()
                .map(Optional::of)
                .toList();
        try {
            when(this.clientService.getDeletedRecords(Mockito.anyInt(), Mockito.anyInt())).thenReturn(optionalCollection);
        } catch (NotFoundException e) {
            Assertions.fail("Error getting record from ClientService");
        }

        ResponseEntity<Collection<Optional<Client>>> response = null;
        Exception exInstance = null;
        try {
            response = this.clientController.getDeletedRecords(1, 1);
        } catch (Exception ex) {
            exInstance = ex;
        }

        Assertions.assertNull(exInstance);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertFalse(response.getBody().isEmpty());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    @Test
    @Tag("Controller_Create_Client")
    public void createClientTest() {
        Optional<Client> clientOptional = Optional.of(TestDataFactory.createClient("Ihor"));
        clientOptional.get().setId(1);
        String clientJson = "{\"firstName\":\"Cherida\",\"lastName\":\"Laza\",\"email\":\"clazar0@elpais.com\",\"isDeleted\":true,\"phoneNumber\":\"512-147-1650\"}";

        try {
            when(this.clientService.createRecord(clientJson)).thenReturn(clientOptional);
        } catch (Exception e) {
            Assertions.fail("Error getting record from ClientService");
        }

        ResponseEntity<Optional<Client>> response = null;
        Exception exInstance = null;
        try {
            response = this.clientController.createRecord(clientJson);
        } catch (Exception ex) {
            exInstance = ex;
        }

        Assertions.assertNull(exInstance);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertTrue(response.getBody().isPresent());
        Assertions.assertEquals(1, response.getBody().get().getId());
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    @Test
    @Tag("Controller_Update_Client")
    public void updateClientTest() {
        Optional<Client> clientOptional = Optional.of(TestDataFactory.createClient("Ihor"));
        clientOptional.get().setId(1);
        String clientJson = "{\"id\":1,\"firstName\":\"Cherida\",\"lastName\":\"Laza\",\"email\":\"clazar0@elpais.com\",\"isDeleted\":true,\"phoneNumber\":\"512-147-1650\"}";
        try {
            when(this.clientService.updateRecord(clientJson)).thenReturn(clientOptional);
        } catch (Exception e) {
            Assertions.fail("Error getting record from ClientService");
        }

        ResponseEntity<Optional<Client>> response = null;
        Exception exInstance = null;
        try {
            response = this.clientController.updateRecord(clientJson);
        } catch (Exception ex) {
            exInstance = ex;
        }

        Assertions.assertNull(exInstance);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertTrue(response.getBody().isPresent());
        Assertions.assertEquals(1, response.getBody().get().getId());
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    @Test
    @Tag("Controller_Delete_Client_Soft")
    public void softDeleteClientTest() {

        Exception exInstance = null;
        try {
            this.clientController.deleteRecord(1, true);
        } catch (Exception ex) {
            exInstance = ex;
        }

        Assertions.assertNull(exInstance);
    }

    @Test
    @Tag("Controller_Delete_Client")
    public void deleteClientTest() {

        Exception exInstance = null;
        try {
            this.clientController.deleteRecord(1, false);
        } catch (Exception ex) {
            exInstance = ex;
        }

        Assertions.assertNull(exInstance);
    }
}
