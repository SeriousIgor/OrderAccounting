package com.springstudy.serviceTest;

import com.springstudy.dataFactory.TestDataFactory;
import com.springstudy.models.Client;
import com.springstudy.models.Order;
import com.springstudy.models.User;
import com.springstudy.repositories.ClientRepository;
import com.springstudy.repositories.OrderRepository;
import com.springstudy.repositories.UserRepository;
import com.springstudy.services.ReportService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ReportService reportService;

    @Test
    @Tag("Service_Get_Report_For_Client")
    public void getReportForClient() {
        int clientId = 1;
        Client client = TestDataFactory.createClient("test");
        List<Optional<Order>> optionalOrders =
                TestDataFactory.createOrderList(2, 1, 1)
                    .stream()
                    .map(Optional::of)
                    .toList();

        when(this.clientRepository.getReferenceById(clientId)).thenReturn(client);
        when(this.orderRepository.findAllByClient_Id(clientId)).thenReturn(optionalOrders);

        String response = null;
        Exception exInstance = null;
        try {
            response = this.reportService.getReportForClient(clientId);
        } catch (Exception ex) {
            exInstance = ex;
        }

        Assertions.assertNull(exInstance);
        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.isBlank());
    }

    @Test
    @Tag("Service_Get_Report_For_User")
    public void getReportForUserSuccessMetricsTest() {
        int userId = 1;
        User user = TestDataFactory.createUser("test");
        List<Optional<Order>> optionalOrders =
                TestDataFactory.createOrderList(2, 1, 1)
                        .stream()
                        .map(Optional::of)
                        .toList();

        when(this.userRepository.getReferenceById(userId)).thenReturn(user);
        when(this.orderRepository.findAllByUser_Id(userId)).thenReturn(optionalOrders);

        String response = null;
        Exception exInstance = null;
        try {
            response = this.reportService.getReportForUserSuccessMetrics(userId);
        } catch (Exception ex) {
            exInstance = ex;
        }

        Assertions.assertNull(exInstance);
        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.isBlank());
    }

    @Test
    @Tag("Service_Get_Yearly_Report_For_User")
    public void getYearlyReportForUserTest() {
        int userId = 1;
        User user = TestDataFactory.createUser("test");
        List<Optional<Order>> optionalOrders =
                TestDataFactory.createOrderList(2, 1, 1)
                        .stream()
                        .map(Optional::of)
                        .toList();

        when(this.userRepository.getReferenceById(userId)).thenReturn(user);
        when(this.orderRepository.findAllByUser_Id(userId)).thenReturn(optionalOrders);

        String response = null;
        Exception exInstance = null;
        try {
            response = this.reportService.getYearlyReportForUser(userId);
        } catch (Exception ex) {
            exInstance = ex;
        }

        Assertions.assertNull(exInstance);
        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.isBlank());
    }
}
