package com.springstudy.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springstudy.enums.PaymentMethod;
import com.springstudy.models.Client;
import com.springstudy.models.Order;
import com.springstudy.models.User;
import com.springstudy.repositories.ClientRepository;
import com.springstudy.repositories.OrderRepository;
import com.springstudy.repositories.UserRepository;
import com.springstudy.utils.MetricsCalculationUtils;
import com.springstudy.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ReportService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReportService(OrderRepository orderRepository, ClientRepository clientRepository, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    private Map<String, Object> getMetricParamMapForEntity(Object modelInstance, Collection<Optional<Order>> orders) throws JsonProcessingException {
        // Metrics variable preparation
        int newOrderNumber = 0;
        int inProgressOrderNumber = 0;
        int canceledOrderNumber = 0;
        int closedOrderNumber = 0;
        int deletedOrderNumber = 0;
        BigDecimal totalOrderSum = new BigDecimal(0);
        Map<PaymentMethod, Integer> paymentMethodCounterMap = new HashMap<>();

        for (Optional<Order> optionalOrder : orders) {
            if (optionalOrder.isEmpty()) {
                continue;
            }
            Order order = optionalOrder.get();
            if (order.getIsDeleted()) {
                deletedOrderNumber++;
            } else {
                switch (order.getOrderStatus()) {
                    case NEW: {
                        newOrderNumber++;
                        break;
                    }
                    case IN_PROGRESS: {
                        inProgressOrderNumber++;
                        break;
                    }
                    case CANCELED: {
                        canceledOrderNumber++;
                    }
                    case CLOSED: {
                        closedOrderNumber++;
                    }
                }
                totalOrderSum = totalOrderSum.add(order.getPrice());

                PaymentMethod orderPaymentMethod = order.getPaymentMethod();
                if (!paymentMethodCounterMap.containsKey(orderPaymentMethod)) {
                    paymentMethodCounterMap.put(orderPaymentMethod, 1);
                } else {
                    paymentMethodCounterMap.put(
                            orderPaymentMethod,
                            paymentMethodCounterMap.get(orderPaymentMethod) + 1
                    );
                }
            }
        }
        PaymentMethod mostUsedMethod = paymentMethodCounterMap
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        Map<String, Object> jsonParamMap = new HashMap<>();
//        jsonParamMap.put("clientInfo", ServiceUtils.getJsonFromObject(modelInstance));
        jsonParamMap.put("totalOrderNumber", orders.size());
        jsonParamMap.put("totalOrderSum", totalOrderSum);
        jsonParamMap.put("newOrderNumber", newOrderNumber);
        jsonParamMap.put("inProgressOrderNumber", inProgressOrderNumber);
        jsonParamMap.put("canceledOrderNumber", canceledOrderNumber);
        jsonParamMap.put("closedOrderNumber", closedOrderNumber);
        jsonParamMap.put("deletedOrderNumber", deletedOrderNumber);
        jsonParamMap.put("mostUsedMethod", mostUsedMethod);

        return jsonParamMap;
    }

    public String getReportForClient(Integer clientId) throws JsonProcessingException {
        Client client = this.clientRepository.getReferenceById(clientId);
        Collection<Optional<Order>> orders = this.orderRepository.findAllByClient_Id(clientId);

        Map<String, Object> clientMetricsMap = getMetricParamMapForEntity(client, orders);
        return MetricsCalculationUtils.getReportJSON(clientMetricsMap);
    }

    public String getReportForUserSuccessMetrics(Integer userId) throws JsonProcessingException {
        User user = this.userRepository.getReferenceById(userId);
        Collection<Optional<Order>> orders = this.orderRepository.findAllByUser_Id(userId);
        Map<String, Object> userMetricsMap = getMetricParamMapForEntity(user, orders);
        return MetricsCalculationUtils.getReportJSON(userMetricsMap);
    }
}
