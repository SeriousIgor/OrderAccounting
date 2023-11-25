package com.springstudy.controllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springstudy.controllers.ReportController;
import com.springstudy.services.ReportService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportControllerTest {
    @Mock
    private ReportService reportService;

    @InjectMocks
    private ReportController reportController;

    @Test
    @Tag("Controller_Get_Orders_For_Client_Report")
    public void getOrdersForClientTest() {
        int clientId = 1;
        String jsonReport = "{\"deletedOrderNumber\":0,\"totalOrderSum\":200.00,\"inProgressOrderNumber\":0,\"newOrderNumber\":2,\"canceledOrderNumber\":0,\"closedOrderNumber\":0,\"totalOrderNumber\":2,\"mostUsedMethod\":null}";
        try {
            when(this.reportService.getReportForClient(clientId)).thenReturn(jsonReport);
        } catch (JsonProcessingException e) {
            Assertions.fail("Client or Orders not found");
        }

        ResponseEntity<String> response = null;
        Exception exInstance = null;
        try {
            response = this.reportController.getOrdersForClient(clientId);
        } catch (Exception ex) {
            exInstance = ex;
        }

        Assertions.assertNull(exInstance);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertFalse(response.getBody().isEmpty());
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());

        Double totalOrderSum = null;
        Integer deletedOrderNumber = null;
        Integer inProgressOrderNumber = null;
        Integer newOrderNumber = null;
        Integer canceledOrderNumber = null;
        try {
            Map<String, Object> responseBodyMap = new ObjectMapper().readValue(response.getBody(), Map.class);
            totalOrderSum = (double) responseBodyMap.get("totalOrderSum");
            deletedOrderNumber = (int) responseBodyMap.get("deletedOrderNumber");
            inProgressOrderNumber = (int) responseBodyMap.get("inProgressOrderNumber");
            newOrderNumber = (int) responseBodyMap.get("newOrderNumber");
            canceledOrderNumber = (int) responseBodyMap.get("canceledOrderNumber");
        } catch (JsonProcessingException e) {
            Assertions.fail("Error parsing response body");
        }

        Assertions.assertNotNull(totalOrderSum);
        Assertions.assertNotNull(deletedOrderNumber);
        Assertions.assertNotNull(inProgressOrderNumber);
        Assertions.assertNotNull(newOrderNumber);
        Assertions.assertNotNull(canceledOrderNumber);
        Assertions.assertEquals(0, deletedOrderNumber);
        Assertions.assertEquals(200.0, totalOrderSum);
        Assertions.assertEquals(0, inProgressOrderNumber);
        Assertions.assertEquals(2, newOrderNumber);
        Assertions.assertEquals(0, canceledOrderNumber);
    }

    @Test
    @Tag("Controller_Get_Orders_For_User_Report")
    public void getOrdersForUserTest() {
        int userId = 1;
        String jsonReport = "{\"deletedOrderNumber\":0,\"totalOrderSum\":200.00,\"inProgressOrderNumber\":0,\"newOrderNumber\":2,\"canceledOrderNumber\":0,\"closedOrderNumber\":0,\"totalOrderNumber\":2,\"mostUsedMethod\":null}";
        try {
            when(this.reportService.getReportForUserSuccessMetrics(userId)).thenReturn(jsonReport);
        } catch (JsonProcessingException e) {
            Assertions.fail("Client or Orders not found");
        }

        ResponseEntity<String> response = null;
        Exception exInstance = null;
        try {
            response = this.reportController.getOrdersForUser(userId);
        } catch (Exception ex) {
            exInstance = ex;
        }

        Assertions.assertNull(exInstance);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertFalse(response.getBody().isEmpty());
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());

        Double totalOrderSum = null;
        Integer deletedOrderNumber = null;
        Integer inProgressOrderNumber = null;
        Integer newOrderNumber = null;
        Integer canceledOrderNumber = null;
        try {
            Map<String, Object> responseBodyMap = new ObjectMapper().readValue(response.getBody(), Map.class);
            totalOrderSum = (double) responseBodyMap.get("totalOrderSum");
            deletedOrderNumber = (int) responseBodyMap.get("deletedOrderNumber");
            inProgressOrderNumber = (int) responseBodyMap.get("inProgressOrderNumber");
            newOrderNumber = (int) responseBodyMap.get("newOrderNumber");
            canceledOrderNumber = (int) responseBodyMap.get("canceledOrderNumber");
        } catch (JsonProcessingException e) {
            Assertions.fail("Error parsing response body");
        }

        Assertions.assertNotNull(totalOrderSum);
        Assertions.assertNotNull(deletedOrderNumber);
        Assertions.assertNotNull(inProgressOrderNumber);
        Assertions.assertNotNull(newOrderNumber);
        Assertions.assertNotNull(canceledOrderNumber);
        Assertions.assertEquals(0, deletedOrderNumber);
        Assertions.assertEquals(200.0, totalOrderSum);
        Assertions.assertEquals(0, inProgressOrderNumber);
        Assertions.assertEquals(2, newOrderNumber);
        Assertions.assertEquals(0, canceledOrderNumber);
    }

    @Test
    @Tag("Controller_Get_Yearly_Orders_For_User_Report")
    public void getYearlyReportForUserTest() {
        int userId = 1;
        String jsonReport = "{\"deletedOrderNumber\":0,\"totalOrderSum\":200.00,\"inProgressOrderNumber\":0,\"newOrderNumber\":2,\"canceledOrderNumber\":0,\"closedOrderNumber\":0,\"totalOrderNumber\":2,\"mostUsedMethod\":null}";
        try {
            when(this.reportService.getYearlyReportForUser(userId)).thenReturn(jsonReport);
        } catch (JsonProcessingException e) {
            Assertions.fail("Client or Orders not found");
        }

        ResponseEntity<String> response = null;
        Exception exInstance = null;
        try {
            response = this.reportController.getYearMetricsForUser(userId);
        } catch (Exception ex) {
            exInstance = ex;
        }

        Assertions.assertNull(exInstance);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertFalse(response.getBody().isEmpty());
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());

        Double totalOrderSum = null;
        Integer deletedOrderNumber = null;
        Integer inProgressOrderNumber = null;
        Integer newOrderNumber = null;
        Integer canceledOrderNumber = null;
        try {
            Map<String, Object> responseBodyMap = new ObjectMapper().readValue(response.getBody(), Map.class);
            totalOrderSum = (double) responseBodyMap.get("totalOrderSum");
            deletedOrderNumber = (int) responseBodyMap.get("deletedOrderNumber");
            inProgressOrderNumber = (int) responseBodyMap.get("inProgressOrderNumber");
            newOrderNumber = (int) responseBodyMap.get("newOrderNumber");
            canceledOrderNumber = (int) responseBodyMap.get("canceledOrderNumber");
        } catch (JsonProcessingException e) {
            Assertions.fail("Error parsing response body");
        }

        Assertions.assertNotNull(totalOrderSum);
        Assertions.assertNotNull(deletedOrderNumber);
        Assertions.assertNotNull(inProgressOrderNumber);
        Assertions.assertNotNull(newOrderNumber);
        Assertions.assertNotNull(canceledOrderNumber);
        Assertions.assertEquals(0, deletedOrderNumber);
        Assertions.assertEquals(200.0, totalOrderSum);
        Assertions.assertEquals(0, inProgressOrderNumber);
        Assertions.assertEquals(2, newOrderNumber);
        Assertions.assertEquals(0, canceledOrderNumber);
    }
}
