package com.springstudy.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springstudy.enums.PaymentMethod;
import com.springstudy.models.Client;
import com.springstudy.models.Order;
import com.springstudy.models.User;
import com.springstudy.repositories.ClientRepository;
import com.springstudy.repositories.OrderRepository;
import com.springstudy.repositories.UserRepository;
import com.springstudy.utils.MetricsCalculationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service("ReportService")
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

    private Map<String, Object> getMetricParamMapForEntity(Collection<Optional<Order>> orders) throws JsonProcessingException {
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

        Map<String, Object> clientMetricsMap = getMetricParamMapForEntity(orders);
        return MetricsCalculationUtil.getReportJSON(clientMetricsMap);
    }

    public String getReportForUserSuccessMetrics(Integer userId) throws JsonProcessingException {
        User user = this.userRepository.getReferenceById(userId);
        Collection<Optional<Order>> orders = this.orderRepository.findAllByUser_Id(userId);
        Map<String, Object> userMetricsMap = getMetricParamMapForEntity(orders);
        return MetricsCalculationUtil.getReportJSON(userMetricsMap);
    }

    public String getYearlyReportForUser(Integer userId) throws JsonProcessingException {
        User user = this.userRepository.getReferenceById(userId);
        Collection<Optional<Order>> orders = this.orderRepository.findAllByUser_Id(userId);
        Map<Integer, Collection<Optional<Order>>> yearToOrderMap = new HashMap<>();
        for (Optional<Order> optionalOrder : orders) {
            if (optionalOrder.isPresent()) {
                Integer orderYear = optionalOrder.get().getOrderDate().getYear();
                if (yearToOrderMap.containsKey(orderYear)) {
                    yearToOrderMap.get(orderYear).add(optionalOrder);
                } else {
                    Collection<Optional<Order>> yearRelatedOrders = new ArrayList<>();
                    yearRelatedOrders.add(optionalOrder);
                    yearToOrderMap.put(orderYear, yearRelatedOrders);
                }
            }
        }
        Map<String, Object> userYearlyMetricsMap = getMetricParamMapForEntity(orders);
        Collection<String> userMetricsStringCollection = new ArrayList<>();
        for (Integer year : yearToOrderMap.keySet()) {
            userMetricsStringCollection.add(
                    MetricsCalculationUtil.getReportJSON(
                            getMetricParamMapForEntity(yearToOrderMap.get(year))
                    )
            );
        }
        userYearlyMetricsMap.put("yearlyOrderMetrics", userMetricsStringCollection);
        return MetricsCalculationUtil.getReportJSON(userYearlyMetricsMap);
    }
}
